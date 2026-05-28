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

$sql="
SELECT
id,
name,
email,
mobile,
about,
role,
account_holder,
account_number
FROM users
WHERE id=?
";

$stmt=$conn->prepare($sql);

$stmt->execute([$id]);

$user=$stmt->fetch(PDO::FETCH_ASSOC);

if($user){

echo json_encode([

"status"=>"success",

"user"=>$user

]);

}
else{

echo json_encode([

"status"=>"error",

"message"=>"User not found"

]);

}

}
catch(Exception $e){

echo json_encode([

"status"=>"error",

"message"=>"Internal server error"

]);

}
?>