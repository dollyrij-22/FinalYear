<?php 
error_reporting(0);
require "init.php";

$username = $_POST["username"];
$password = $_POST["password"];


$sql = "SELECT * FROM login WHERE username = '$username' AND password = '$password' ";

$result = mysql_query($con, $sql);

$response = array();

while($row = mysql_fetch_array($result)){
	$response = array("designation"=>$row[2],"name"=>$row[1],"password"=>$row[8],"email"=>$row[5],"department"=>$row[6],"specialization"=>$row[3],"phone"=>$row[4],"username"=>$row[7]);
}
echo json_encode(array("user_data"=>$response));
?>