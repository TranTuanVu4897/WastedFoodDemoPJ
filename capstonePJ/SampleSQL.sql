    SELECT `production`.`id`,`sellerid`,`price`,`quantity`,`selled_quantity`,`saledate` FROM `production` JOIN
    (
    SELECT id
    FROM ( SELECT id, ( ( ( acos( sin(( $lat * pi() / 180)) * sin(( `lat` * pi() / 180)) + cos(( $lat* pi() /180 )) * cos(( `lat` * pi() / 180)) * cos((( $lng - `lng`) * pi()/180))) ) * 180/pi() ) * 60 * 1.1515 * 1.609344 ) as distance
        FROM `seller` ) markers
    WHERE distance <= $distance)
    currents 
    ON currents.id = `production`.`sellerid`;