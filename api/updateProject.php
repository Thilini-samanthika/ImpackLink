<?php
header("Content-Type: application/json");
require_once "config.php";

$data = json_decode(file_get_contents("php://input"), true);

if(empty($data['id'])){
    echo json_encode(["status"=>"error","message"=>"Project ID required"]);
    exit;
}

$sql = "UPDATE projects SET title=?, description=?, budget=?, status=?, required_volunteers=? WHERE id=?";

$stmt = $conn->prepare($sql);

$stmt->execute([
    $data['title'],
    $data['description'],
    $data['budget'],
    $data['status'],
    $data['required_volunteers'] ?? 0,
    $data['id']
]);

echo json_encode(["status"=>"success","message"=>"Updated"]);
?>