<?php
error_reporting(0);
require "init.php";

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$image = $_POST['image'];
		$username = $_POST['username'];
		
		$sql = "SELECT username FROM login WHERE username = '$username'";
		
		$res = mysqli_query($con,$sql);
				
		
		while($row = mysqli_fetch_array($res)){
				$username = $row['username'];
		}
		
		$path = "$username.png";
		
		$actualpath = "http://attendance-dr22libraryapp.rhcloud.com/$path";
		
		$sqlm = "INSERT INTO image (image,username,`date`,`time`) VALUES ('$actualpath','$username',CURDATE(), "00:00:00")";
		
		if(mysqli_query($con,$sqlm)){
			file_put_contents($path,base64_decode($image));
			echo "Successfully Uploaded";
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}
	?>