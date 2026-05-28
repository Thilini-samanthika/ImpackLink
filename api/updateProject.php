<?php
header("Content-Type: application/json");
require_once "config.php";

$data = json_decode(file_get_contents("php://input"), true);

$sql = "UPDATE projects SET title=?, description=?, budget=?, status=? WHERE id=?";

$stmt = $conn->prepare($sql);

$stmt->execute([
$data['title'],
$data['description'],
$data['budget'],
$data['status'],
$data['id']
]);

echo json_encode(["status"=>"success","message"=>"Updated"]);
?>
