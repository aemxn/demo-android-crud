<?php

/*
 * Following code will create a new product row
 * All product details are read from HTTP Post Request
 */

// array for JSON response
$response = array();
include_once "db_connect.php";
$db = new DB_Connect();
$db->connect();

// check for required fields
if ($_POST) {

    $comment = $_POST['comment'];

    // mysql inserting a new row
    $result = mysql_query("INSERT INTO testtable(comments) VALUES('".$comment."')")
                or die (mysql_error());

    // check if row inserted or not
    if ($result) {
        // successfully inserted into database
        $response["success"] = 1;
        $response["message"] = "Product successfully created.";

        // echoing JSON response
        echo json_encode($response);
    } else {
        // failed to insert row
        $response["success"] = 0;
        $response["message"] = "Oops! An error occurred.";

        // echoing JSON response
        echo json_encode($response);
    }
} else {
    // required field is missing
    $response["success"] = 0;
    $response["message"] = "Required field(s) is missing";

    // echoing JSON response
    echo json_encode($response);
    $db->close();
}
?>