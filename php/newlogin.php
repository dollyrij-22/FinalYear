<?php 
error_reporting(0);
define('HOST','127.11.5.130');
 define('USER','admin97C25cx');
 define('PASS','t8WTLp52Xiwh');
 define('DB','attendance');
 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect to database!!!');

$username = $_POST["username"];
$password = $_POST["password"];


$sql = "SELECT * FROM `login` WHERE username='$username' AND password='$password' ";

$result = mysqli_query($con, $sql);

$response = array();

while($row = mysqli_fetch_array($result)){
	$response = array("name"=>$row[0],"username"=>$row[6],"password"=>$row[7]);
}
echo json_encode(array("user_data"=>$response));
mysqli_close($con);
?>