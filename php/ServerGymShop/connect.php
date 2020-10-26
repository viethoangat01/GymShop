<?php 
	$host="localhost";
	$username="root";
	$password="";
	$database="gymshop";
	$conn=mysqli_connect($host,$username,$password,$database);
	mysqli_query($conn,"SET NAMES 'utf8'");
	// if($conn){
	// 	echo "Connect successfully<br>";
	// }else{
	// 	echo "Connect failed<br>";
	// }

?>