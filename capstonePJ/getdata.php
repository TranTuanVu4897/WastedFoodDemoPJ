<?php
require "dbConnection.php";

//sample place
$lat = 7.08594109039762;
$lng = 286.95225338731285;


//sample distance
$distance = 150; //$_REQUEST['distance']

//escape special characters in variable
$lat = mysqli_real_escape_string($connect, $lat);
$lng = mysqli_real_escape_string($connect, $lng);
$distance = mysqli_real_escape_string($connect, $distance);

$query = <<<EOF
    SELECT `production`.`id`,`sellerid`,`price`,`quantity`,`selled_quantity`,`saledate` FROM `production` JOIN
    (
    SELECT id
    FROM ( SELECT id, ( ( ( acos( sin(( $lat * pi() / 180)) * sin(( `lat` * pi() / 180)) + cos(( $lat* pi() /180 )) * cos(( `lat` * pi() / 180)) * cos((( $lng - `lng`) * pi()/180))) ) * 180/pi() ) * 60 * 1.1515 * 1.609344 ) as distance
        FROM `seller` ) markers
    WHERE distance <= $distance)
    currents 
    ON currents.id = `production`.`sellerid`;
EOF;

$result = $connect->query($query);

//Test
// if($result->num_rows >0){
//     while($row = $result->fetch_assoc()){
//         echo $row["id"] . "  $" . $row["price"] . "<br>";
//     }
// }

//Class product
class Production{
    function Production($id, $sellerid, $price, $quantity, $selled_quantity, $saledate){
        $this->Id = $id;
        $this->SellerId = $sellerid;
        $this->Price = $price;
        $this->Quantity = $quantity;
        $this->Selled_quantity = $selled_quantity;
        $this->SaleDate = $saledate;
    }
}

$listProcution = array();

while($row = mysqli_fetch_assoc($result)){
    array_push($listProcution, new Production($row['id'],$row['sellerid'],$row['price'],$row['quantity'],$row['selled_quantity'],$row['saledate']));
}

echo json_encode($listProcution);