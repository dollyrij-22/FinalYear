<?php 
error_reporting(0);
require "init.php";

$username = $_POST["username"];
$password = $_POST["password"];


$sql = "SELECT * FROM `login` WHERE `username` = '".$username."' AND `password` ='".$password."';";

$result = mysqli_query($con, $sql)or die(mysql_error());

$response = array();

while($row = mysqli_fetch_array($result)){
	$response = array("name"=>$row[0],"designation"=>$row[1],"specialization"=>$row[2],"phone"=>$row[3],"email"=>$row[4],"department"=>$row[5],"username"=>$row[6],"password"=>$row[7]);
}
echo json_encode(array("user_data"=>$response));
?>