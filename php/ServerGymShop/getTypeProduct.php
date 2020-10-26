<?php
	include "connect.php";
	class typeProduct{
		function typeProduct($id,$name,$image){
			$this->id=$id;
			$this->name=$name;
			$this->image=$image;
		}
	}
	$query="SELECT * FROM producttype";
	$data=mysqli_query($conn,$query);
	$arrayType=array();
	while ( $row=mysqli_fetch_assoc($data)) {
		# code...
		array_push($arrayType, new typeProduct(
				$row['typeId'],
				$row['typeName'],
				$row['typeImage']
		));
	};
	echo json_encode($arrayType);
?>