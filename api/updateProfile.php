<?php

header("Content-Type: application/json");

require_once "config.php";

$data=json_decode(
file_get_contents("php://input"),
true
);

if(
!$data ||
empty($data['id'])
){

echo json_encode([
"status"=>"error",
"message"=>"User ID required"
]);

exit;
}

try{

$id=$data['id'];

$name=trim(
$data['name'] ?? ""
);

$mobile=trim(
$data['mobile'] ?? ""
);

$about=trim(
$data['about'] ?? ""
);

$account_holder=trim(
$data['account_holder'] ?? ""
);

$account_number=trim(
$data['account_number'] ?? ""
);

$sql="
UPDATE users
SET

name=?,
mobile=?,
about=?,
account_holder=?,
account_number=?

WHERE id=?
";

$stmt=$conn->prepare($sql);

$stmt->execute([

$name,
$mobile,
$about,
$account_holder,
$account_number,
$id

]);

echo json_encode([

"status"=>"success",

"message"=>"Profile updated"

]);

}
catch(Exception $e){

echo json_encode([

"status"=>"error",

"message"=>"Internal server error"

]);

}
?>