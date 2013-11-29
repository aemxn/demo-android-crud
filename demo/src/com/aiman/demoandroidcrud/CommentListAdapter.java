package com.aiman.demoandroidcrud;

import java.util.ArrayList;
import java.util.HashMap;

import com.aiman.salamdunia.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CommentListAdapter extends BaseAdapter {
	Context context;
	LayoutInflater inflater;
	private final ArrayList<HashMap<String, String>> urls;
	HashMap<String, String> resultp = new HashMap<String, String>();

	public CommentListAdapter(Context context,
			ArrayList<HashMap<String, String>> arraylist) {
		this.context = context;
		urls = arraylist;
	}

	@Override
	public int getCount() {
		return urls.size();
	}

	@Override
	public String getItem(int position) {
		return null;
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView uid;
		TextView comment;
		
		inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		
		View view = inflater.inflate(R.layout.thirdview_listview_content, parent, false);
		
		resultp = urls.get(position);
		
		uid = (TextView) view.findViewById(R.id.txtUid);
		comment = (TextView) view.findViewById(R.id.txtComment);
		
		uid.setText(resultp.get(ThirdView.TAG_UID));
		comment.setText(resultp.get(ThirdView.TAG_COMMENT));
		
		// TODO Auto-generated method stub
		return view;
	}
}
