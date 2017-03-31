<?php
error_reporting(0);
require "init.php";

	if($_SERVER['REQUEST_METHOD']=='POST'){	
         date_default_timezone_set('Asia/Kolkata');
         $tm =  date('H:i:s');	
		
		$image = $_POST['image'];
		
		$username = $_POST['username'];
		$latitude = $_POST['latitude'];
		$longitude = $_POST['longitude'];
		
		$sql ="SELECT username FROM login WHERE username = '$username'";
		
		$res = mysqli_query($con,$sql);
		
		
		while($row = mysqli_fetch_array($res)){
				$username = $row['username'];
		}
		
		$path = "$username.png";
		
		$actualpath = "http://attendance-dr22libraryapp.rhcloud.com/$path";
		
		$sqlm = "INSERT INTO image (image,username,`date`,intime,latitude,longitude) VALUES ('$actualpath','$username',CURDATE(),'$tm',$latitude,$longitude)";
		
		if(mysqli_query($con,$sqlm)){
			file_put_contents($path,base64_decode($image));
			echo "Successfully Uploaded";
		}		
		mysqli_close($con);
	}else{
		echo "Error";
	}
	?>