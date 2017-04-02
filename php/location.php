<?php
	require_once('init.php');
	
	$sql = "select latitude, longitude from image";
	
	$res = mysqli_query($con,$sql);
	
	$result = array();
	
	while($row = mysqli_fetch_array($res)){
		array_push($result,array('latitude'=>$row['latitude'],
			'longitude'=>$row['longitude']
			
		));
	}
	
	echo json_encode(array("data"=>$result));
	
	mysqli_close($con);
	?>