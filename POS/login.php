<?php
    include_once 'database.php';

    session_start();

    $db = new Database();

    if(isset($_GET['cerrar_sesion'])){
        session_unset();
        session_destroy();
    }

    //Si intenta regresar al login cuando ya hay una sesion lo manda a "seleccion.php para no iniciar otra"
    if(isset($_SESSION['id'])){
        header('location: inicio.php');
    }

    //Revisa los datos resividos en formulario para loguearse
    if(isset($_POST['usuario']) && isset($_POST['password'])){
        
        $usuario = $_POST['usuario'];
        $password = $_POST['password'];

        //Consulta al profesor que ingreso
        $query = $db->connect()->prepare('SELECT * FROM user WHERE Usuario = :usu AND Pass = :pass');
        $query->execute(['usu' => $usuario, 'pass' => $password]);
        $row = $query-> fetch(PDO::FETCH_NUM);

        if($row == true){ //Valida la existencia del usuario y si es correcto el numero economico

            $_SESSION['id'] = $row[0];
            $_SESSION['user'] = $row[1];

            header('location:inicio.php');
        }else{
            //No existe el usuario con ese numero economico
            echo "<script>alert('El nombre o numero economico no coinciden')</script>";
        }
    }
?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-COompatible" content="ie=edge">

        <link rel="stylesheet" href="login.css">
        <title>Login</title>
    </head>
    <body>
        <div class="box">
            Ingrese sus datos para ingresar
            <form action="#" method="POST" id="formulario">
                <label>Usuario</label><br/>
                <input type="text" name="usuario"><br/><br/>
                <label>Password</label><br/>
                <input type="text" name="password"><br/><br/><br/>
                <input type="submit" value="Iniciar sesión" class="enviar">
                <a href="registrar.php">Registrar cuenta nueva</a>
            </form>
        </div>
    </body>
</html>    