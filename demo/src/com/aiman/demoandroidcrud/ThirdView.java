package com.aiman.demoandroidcrud;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.aiman.salamdunia.R;

import android.app.ListActivity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class ThirdView extends ListActivity {
	// Progress Dialog
	private ProgressDialog pDialog;
	private ProgressDialog mDialog;

	// Building Parameters
	List<NameValuePair> params = new ArrayList<NameValuePair>();

	// instantiate JSONParser object
	JSONParser jsonParser = new JSONParser();
	InputStream inputStream;
	String responseString;

	private Button submit;
	private EditText inputComment;

	private static final String URL_SUBMIT_COMMENT = "http://192.168.1.157/test_comment/submitcomment.php";
	private static final String URL_COMMENT = "http://192.168.1.157/test_comment/viewcomment.php";

	public static final String TAG_UID = "uid";
	public static final String TAG_COMMENT = "comment";
	private static final String TAG_COMMENTS = "comments";
	private static final String TAG_SUCCESS = "success";

	JSONArray comments = null;

	ArrayList<HashMap<String, String>> commentList;

	ListView lv;

	CommentListAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.thirdview);

		// locate
		inputComment = (EditText) findViewById(R.id.editText);
		// locate Submit button in thirdview.xml layout
		submit = (Button) findViewById(R.id.btnSubmit);

		commentList = new ArrayList<HashMap<String, String>>();

		new LoadComments().execute();

		// Listen for button to be clickednavi
		submit.setOnClickListener(new OnClickListener() {
			// upon clicked do this
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				try {
					executeSubmission();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * HTTP Upload in AsyncTask for the fucking win
	 * 
	 * @throws Exception
	 */
	public void executeSubmission() throws Exception {

		// Submit comment on AsyncTask
		class SubmitComment extends AsyncTask<String, String, String> {
			String comment;

			@Override
			protected void onPreExecute() {
				super.onPreExecute();
				comment = inputComment.getText().toString();

				pDialog = new ProgressDialog(ThirdView.this);
				pDialog.setMessage("Submitting comment...");
				pDialog.setIndeterminate(false);
				pDialog.setCancelable(true);
				pDialog.show();
			}

			/**
			 * Sending comment String to server using JSON
			 * */
			protected String doInBackground(String... args) {

				params.add(new BasicNameValuePair("comment", comment));

				try {
					HttpClient httpclient = new DefaultHttpClient();
					/*
					 * HttpPost(parameter): Server URI
					 */
					HttpPost httppost = new HttpPost(URL_SUBMIT_COMMENT);
					httppost.setEntity(new UrlEncodedFormEntity(params));
					HttpResponse response = httpclient.execute(httppost);
					responseString = convertResponseToString(response);
				} catch (Exception e) {
					e.printStackTrace();
				}

				return responseString;
			}

			/**
			 * After completing background task Dismiss the progress dialog
			 * **/
			protected void onPostExecute(String result) {
				super.onPostExecute(result);

				pDialog.dismiss();

				Toast.makeText(ThirdView.this, "Comment Submitted!",
						Toast.LENGTH_LONG).show();

				new LoadComments().execute();
			}

			public String convertResponseToString(HttpResponse response)
					throws IllegalStateException, IOException {

				String res = "";
				StringBuffer buffer = new StringBuffer();
				inputStream = response.getEntity().getContent();
				// getting content length
				int contentLength = (int) response.getEntity()
						.getContentLength();

				//Log.d("Rain", "heavy as fuck");
				if (contentLength < 0) {
				} else {
					byte[] data = new byte[512];
					int len = 0;
					try {
						while (-1 != (len = inputStream.read(data))) {
							// converting to string and appending to
							// stringbuffer
							buffer.append(new String(data, 0, len));
						}
					} catch (IOException e) {
						e.printStackTrace();
					}
					try {
						inputStream.close(); // closing the stream
					} catch (IOException e) {
						e.printStackTrace();
					}
					res = buffer.toString(); // converting stringbuffer to
												// string
				}
				return res;
			}
		}

		SubmitComment exec = new SubmitComment();
		exec.execute();
	}

	private class LoadComments extends AsyncTask<String, String, String> {
		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			mDialog = new ProgressDialog(ThirdView.this);
			mDialog.setMessage("Loading Comments...");
			mDialog.setIndeterminate(false);
			mDialog.setCancelable(false);
			mDialog.show();
		}

		@Override
		protected String doInBackground(String... args) {
			List<NameValuePair> params = new ArrayList<NameValuePair>();

			JSONObject json = jsonParser.makeHttpRequest(URL_COMMENT, "GET",
					params);
			
			// TODO: http://androidadapternotifiydatasetchanged.blogspot.com/
			commentList.clear();
			
			try {
				int success = json.getInt(TAG_SUCCESS);

				if (success == 1) {
					comments = json.getJSONArray(TAG_COMMENTS);

					for (int i = 0; i < comments.length(); i++) {
						JSONObject c = comments.getJSONObject(i);

						String uid = c.getString(TAG_UID);
						String comment = c.getString(TAG_COMMENT);

						HashMap<String, String> map = new HashMap<String, String>();

						map.put(TAG_UID, uid);
						map.put(TAG_COMMENT, comment);

						Log.d("Comments > ", map.toString());
						
						commentList.add(map);
					}
				}
			} catch (JSONException e) {
				e.printStackTrace();
			}

			return null;
		}

		@Override
		protected void onPostExecute(String file_url) {
			Log.d("onpostexecute > ", commentList.size()+"");
			
			lv = getListView();
			adapter = new CommentListAdapter(ThirdView.this, commentList);
			adapter.notifyDataSetChanged();
			lv.setAdapter(adapter);
			
			mDialog.dismiss();
		}
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.third_view, menu);
		return true;
	}

}
