<?php
require_once "config.php";

$data = json_decode(file_get_contents("php://input"), true);

$stmt = $conn->prepare("DELETE FROM projects WHERE id=?");
$stmt->execute([$data['id']]);

echo json_encode(["status"=>"success"]);
?>
