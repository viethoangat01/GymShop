<?php
	include "connect.php";
	class product{
		function product($id,$name,$price,$image,$weight,$color,$taste,$desc,$typeId){
			$this->id=$id;
			$this->name=$name;
			$this->price=$price;
			$this->image=$image;
			$this->weight=$weight;
			$this->color=$color;
			$this->taste=$taste;
			$this->desc=$desc;
			$this->typeId=$typeId;
		}
	}
	$query="SELECT * FROM product ORDER BY productId DESC LIMIT 6";
	$data=mysqli_query($conn,$query);
	$arrayProduct=array();
	while ( $row=mysqli_fetch_assoc($data)) {
		# code...
		array_push($arrayProduct, new product(
				$row['productId'],
				$row['productName'],
				$row['productPrice'],
				$row['productImage'],
				$row['productWeight'],
				$row['productColor'],
				$row['productTaste'],
				$row['productDesc'],
				$row['typeId']
		));
	};
	echo json_encode($arrayProduct);
?>