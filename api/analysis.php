<?php
require_once "config.php";

$data = $conn->query("
SELECT status, COUNT(*) as count
FROM projects
GROUP BY status
")->fetchAll(PDO::FETCH_ASSOC);

echo json_encode([
"status"=>"success",
"data"=>$data
]);
?>