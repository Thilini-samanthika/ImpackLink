<?php
require_once "config.php";

$users = $conn->query("SELECT COUNT(*) as total FROM users")->fetch();
$projects = $conn->query("SELECT COUNT(*) as total FROM projects")->fetch();

echo json_encode([
"status"=>"success",
"users"=>$users['total'],
"projects"=>$projects['total']
]);
?>