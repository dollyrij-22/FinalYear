<?php
 error_reporting(0);
require "init.php";
 if($_SERVER['REQUEST_METHOD']=='POST'){
 
 $image = $_POST['image'];
 
 $sql = "INSERT INTO picture (image) VALUES (?)";
 
 $stmt = mysqli_prepare($con,$sql);
 
 mysqli_stmt_bind_param($stmt,"s",$image);
 mysqli_stmt_execute($stmt);
 
 $check = mysqli_stmt_affected_rows($stmt);
 
 if($check == 1){
 echo "Image Uploaded Successfully";
 }else{
 echo "Error Uploading Image";
 }
 mysqli_close($con);
 }else{
 echo "Error";
 }