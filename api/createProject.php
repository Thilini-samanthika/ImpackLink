<?php
header("Content-Type: application/json");
require_once "config.php";

$data = json_decode(file_get_contents("php://input"), true);

if(!$data || empty($data['user_id'])){
    echo json_encode(["status"=>"error","message"=>"User ID required"]);
    exit;
}

try {

$sql = "INSERT INTO projects (user_id,title,description,budget,status)
VALUES (?,?,?,?,?)";

$stmt = $conn->prepare($sql);

$stmt->execute([
$data['user_id'],
$data['title'],
$data['description'],
$data['budget'],
$data['status']
]);

echo json_encode([
"status"=>"success",
"message"=>"Project created"
]);

} catch(Exception $e){
echo json_encode(["status"=>"error","message"=>"Server error"]);
}
?>