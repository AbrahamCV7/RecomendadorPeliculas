<?php
    include_once 'database.php';

?>

<script type="text/javascript">
    function redirigir(){
        location.href="localhost/POS/selecciones.php"
        console.log('envia algo');
    }
</script>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-COompatible" content="ie=edge">

        <link rel="stylesheet" href="registrar.css">

        
        <title>Registro</title>
    </head>
    <body>
    <div class="box">
              Ingrese sus datos para registrar
            <form action="http://localhost:8080/PelculaRecomendador/webresources/Recomendador"  method="POST" >
   <label>Usuario</label><br/>
                <input type="text" name="usuario" required><br/><br/>
                <label>Nombre</label><br/>
                <input type="text" name="nombre" required><br/><br/>
                <label>Contraseña</label><br/>
                <input type="password" name="password" required><br/><br/>
                <label>Edad</label><br/>
                <input type="number" name="edad" required><br/><br/>
                <label>Genero</label><br/>
                <input type="text" name="genero" required><br/><br/><br/>
                <label>Correo</label><br/>
                <input type="text" name="correo" required><br/><br/><br/>
                <input type="submit"  value="Registrar" class="enviar" >
                <a href="login.php">Regresar pagina anterior</a>

            </form>
        </div>
    </body>
</html>    