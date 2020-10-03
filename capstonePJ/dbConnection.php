<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "capstonedemo";

$connect = mysqli_connect($servername, $username, $password, $dbname);
mysqli_query($connect, "SET NAMES 'utf8'");

//Check connection
if ($connect->connect_error) {
    die("Connection failed: " . $connect->connect_error);
}
