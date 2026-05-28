<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
require_once "config.php";

$rawInput = file_get_contents("php://input");
$data = json_decode($rawInput, true);

if (!$data) {
    echo json_encode(["status" => "error", "message" => "Invalid JSON input: " . $rawInput]);
    exit;
}

if (!isset($data['user_id']) || $data['user_id'] == 0) {
    echo json_encode(["status" => "error", "message" => "User ID required or zero. Received: " . json_encode($data)]);
    exit;
}

try {
    $sql = "INSERT INTO projects (user_id, title, description, budget, status, required_volunteers)
            VALUES (?, ?, ?, ?, ?, ?)";

    $stmt = $conn->prepare($sql);

    $stmt->execute([
        $data['user_id'],
        $data['title'],
        $data['description'],
        $data['budget'],
        $data['status'],
        $data['required_volunteers'] ?? 0
    ]);

    echo json_encode([
        "status" => "success",
        "message" => "Project created"
    ]);

} catch (Exception $e) {
    echo json_encode(["status" => "error", "message" => "DB Error: " . $e->getMessage()]);
}
?>