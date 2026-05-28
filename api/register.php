<?php
header("Content-Type: application/json");
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Methods: POST, GET, OPTIONS");
header("Access-Control-Allow-Headers: Content-Type");
require_once "config.php";

$data = json_decode(file_get_contents("php://input"), true);

if (!$data || empty($data['name']) || empty($data['email']) || empty($data['password'])) {
    echo json_encode(["status" => "error", "message" => "Name, email and password are required"]);
    exit;
}

try {
    $name = trim($data['name']);
    $email = trim($data['email']);
    $password = password_hash($data['password'], PASSWORD_DEFAULT);
    $mobile = $data['mobile'] ?? null;
    $about = $data['about'] ?? null;
    $role = $data['role'] ?? "NGO";
    $account_holder = $data['account_holder'] ?? null;
    $account_number = $data['account_number'] ?? null;

    $sql = "INSERT INTO users (name, email, password, mobile, about, role, account_holder, account_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
    $stmt->execute([$name, $email, $password, $mobile, $about, $role, $account_holder, $account_number]);

    echo json_encode([
        "status" => "success",
        "message" => "Registration successful",
        "user" => [
            "id" => (int)$conn->lastInsertId(),
            "name" => $name,
            "email" => $email,
            "mobile" => $mobile,
            "about" => $about,
            "role" => $role,
            "account_holder" => $account_holder,
            "account_number" => $account_number
        ]
    ]);
} catch (PDOException $e) {
    if ($e->getCode() == 23000) {
        echo json_encode(["status" => "error", "message" => "Email already exists"]);
    } else {
        echo json_encode(["status" => "error", "message" => "Database error: " . $e->getMessage()]);
    }
}
?>
