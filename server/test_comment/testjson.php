
<?php
define('DB_USER', "root"); // db user
define('DB_PASSWORD', ""); // db password (mention your db password here)
define('DB_DATABASE', "webshopper"); // database name
define('DB_SERVER', "localhost"); // db server

// Connecting to mysql database
$con = mysql_connect(DB_SERVER, DB_USER, DB_PASSWORD) or die(mysql_error());

// Selecing database
$db = mysql_select_db(DB_DATABASE) or die(mysql_error()) or die(mysql_error());

//$itemId = $_POST["item_id"];
$itemId = '5';

$result = mysql_query("SELECT
                        comments.`comment` AS comments,
                        comments.from_user_id AS uid,
                        comments.to_item_id AS item_id
                    FROM
                        comments
                    INNER JOIN items ON comments.to_item_id = items.id
                    INNER JOIN users ON comments.from_user_id = users.uid
                    WHERE comments.to_item_id = '$itemId'")
                    or die(mysql_error());

$response_arr["comments"] = array();
$comments_arr = array();

$no_of_rows = mysql_num_rows($result);
if ($no_of_rows > 0) {
	while ($row = mysql_fetch_assoc($result)){
	    $comments_arr = array();
	    $comments_arr["user_id"] = $row["uid"];
	    $comments_arr["comment"] = $row["comments"];
	    //if ($row["item_id"] == $itemId){
	    $response_arr["item_id"] = $row["item_id"];
	    array_push($response_arr["comments"], $comments_arr);
	    //}
	}

} else {
	echo 'false';
}

echo json_encode($response_arr);
echo "<hr/>";
var_dump($response_arr);


?>
