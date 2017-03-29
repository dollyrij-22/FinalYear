<?php

	if($_SERVER['REQUEST_METHOD']=='POST'){
		
		$image = $_POST['image'];
		
		require_once('init.php');
		
		$sql ="SELECT id FROM picture ORDER BY id ASC";
		
		$res = mysqli_query($con,$sql);
		
		$id = 0;
		
		while($row = mysqli_fetch_array($res)){
				$id = $row['id'];
		}
		
		$path = "$id.png";
		
		$actualpath = "http://attendance-dr22libraryapp.rhcloud.com/$path";
		
		$sql = "INSERT INTO picture (image) VALUES ('$actualpath')";
		
		if(mysqli_query($con,$sql)){
			file_put_contents($path,base64_decode($image));
			echo "Successfully Uploaded";
		}
		
		mysqli_close($con);
	}else{
		echo "Error";
	}
	?>