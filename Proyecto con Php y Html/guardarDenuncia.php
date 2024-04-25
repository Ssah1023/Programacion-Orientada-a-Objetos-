<?php
// conexion a la base de datos
$host = 'localhost';
$port = '5432';
$dbname = 'expedientes_db';
$user = 'postgres';
$password = '123';

$conn = new PDO("pgsql:host=$host;port=$port;dbname=$dbname;user=$user;password=$password");

// Obtener los datos del formulario
$nombreDenunciante = $_POST['nombreDenunciante'];
$direccionDenunciante = $_POST['direccionDenunciante'];
$edadDenunciante = $_POST['edadDenunciante'];
$sexoDenunciante = $_POST['sexoDenunciante'];
$nombreDenunciado = $_POST['nombreDenunciado'];
$direccionDenunciado = $_POST['direccionDenunciado']; 
$edadDenunciado = $_POST['edadDenunciado'];
$sexoDenunciado = $_POST['sexoDenunciado'];
$tipoDelito = $_POST['tipoDelito'];

$sql = "INSERT INTO expedientes (nombre_denunciante, direccion_denunciante, edad_denunciante, sexo_denunciante, nombre_denunciado, direccion_denunciado, edad_denunciado, sexo_denunciado, tipo_delito)
        VALUES (:nombreDenunciante, :direccionDenunciante, :edadDenunciante, :sexoDenunciante, :nombreDenunciado, :direccionDenunciado, :edadDenunciado, :sexoDenunciado, :tipoDelito)";
$stmt = $conn->prepare($sql);

$stmt->execute([
    ':nombreDenunciante' => $nombreDenunciante,
    ':direccionDenunciante' => $direccionDenunciante,
    ':edadDenunciante' => $edadDenunciante,
    ':sexoDenunciante' => $sexoDenunciante,
    ':nombreDenunciado' => $nombreDenunciado,
    ':direccionDenunciado' => $direccionDenunciado,
    ':edadDenunciado' => $edadDenunciado,
    ':sexoDenunciado' => $sexoDenunciado,
    ':tipoDelito' => $tipoDelito
]);

// Redirigir al usuario de vuelta al formulario
header('Location: formulario.html');
?>
