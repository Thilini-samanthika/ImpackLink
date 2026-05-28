<?php
header("Content-Type: application/json");
require_once "config.php";

$user_id = $_GET['user_id'];

$sql = "SELECT * FROM projects WHERE user_id=? ORDER BY id DESC";
$stmt = $conn->prepare($sql);
$stmt->execute([$user_id]);

$data = $stmt->fetchAll(PDO::FETCH_ASSOC);

echo json_encode([
"status"=>"success",
"data"=>$data
]);
?>