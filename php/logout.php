<?php
error_reporting(0);
require "init.php";
if($_SERVER['REQUEST_METHOD']=='POST'){
        date_default_timezone_set('Asia/Kolkata');
        $tm =  date('H:i:s');	
        $username = $_POST['username'];
        $department = $_POST['department'];
		$sql = "INSERT INTO logout (username,department,`date`,outtime) VALUES ('$username','$department',CURDATE(),'$tm')"; 
		if(mysqli_query($con, $sql))
		{
	        echo 'Logged out successfully';
        }
		mysqli_close($con);
}
else
{
	echo "Error";
}
?>