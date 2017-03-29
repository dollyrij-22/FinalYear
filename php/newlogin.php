<?php 
error_reporting(0);
define('HOST','127.11.5.130');
 define('USER','admin97C25cx');
 define('PASS','t8WTLp52Xiwh');
 define('DB','attendance');
 
 $con = mysqli_connect(HOST,USER,PASS,DB) or die('Unable to Connect to database!!!');

$username = $_POST["username"];
$password = $_POST["password"];


$sql = "SELECT * FROM `login` WHERE `username` = '".$username."' AND `password` ='".$password."';";

$result = mysqli_query($con, $sql)or die(mysql_error());

$row = mysqli_fetch_array($result);
if(isset($row)){
echo 'success';
}else{
echo 'failure';
}
mysqli_close($con);
?>