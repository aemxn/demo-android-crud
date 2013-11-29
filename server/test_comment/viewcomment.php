
<?php

/*
 * Following code will list all the products
 */

// array for JSON response
$response = array();

include_once "db_connect.php";
$db = new DB_Connect();
$db->connect();

// get all products from products table
$result = mysql_query("SELECT * FROM testtable") or die(mysql_error());

// check for empty result
if (mysql_num_rows($result) > 0) {
    // looping through all results
    // products node
    $response["comments"] = array();

    while ($row = mysql_fetch_array($result)) {
        // temp user array
        $product = array();
        $product["uid"] = $row["id"];
        $product["comment"] = $row["comments"];

        // push single product into final response array
        array_push($response["comments"], $product);
    }
    // success
    $response["success"] = 1;

    // echoing JSON response
    echo json_encode($response);
} else {
    // no products found
    $response["success"] = 0;
    $response["message"] = "No comments found";

    // echo no users JSON
    echo json_encode($response);
}
?>