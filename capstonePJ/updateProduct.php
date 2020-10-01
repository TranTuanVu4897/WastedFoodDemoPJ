<?php
require "dbConnection.php";

$id = $_POST['idProduct'];
$selled_quantity = $_POST['purchase'];

$query = "UPDATE production SET selled_quantity = '$selled_quantity'
            WHERE id = '$id'";

if(mysqli_query($connect,$query)){
    echo "success";
}else{
    echo "fail";
}