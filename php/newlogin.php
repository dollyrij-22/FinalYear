<?php 
error_reporting(0);
require "init.php";

$username = $_POST["username"];
$password = $_POST["password"];

//$name = "sdf";
//$password = "sdf";

$sql = "SELECT * FROM `login` WHERE `username`='".$username."' AND `password`='".$password."';";

$result = mysqli_query($con, $sql);

$response = array();

while($row = mysqli_fetch_array($result)){
	$response = array("designation"=>$row[1],"name"=>$row[0],"password"=>$row[7],"email"=>$row[4],"department"=>$row[5],"specialization"=>$row[2],"phone"=>$row[3],"username"=>$row[6]);
}

echo json_encode(array("user_data"=>$response));

?>