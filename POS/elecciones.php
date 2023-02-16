<?php
    include_once 'database.php';

    session_start();

    $db = new Database();

    $query = $db->connect()->prepare('SELECT * FROM actor ORDER BY rand() LIMIT 10');
    $query->execute();
    $row = $query-> fetchAll(PDO::FETCH_NUM);

    $query2 = $db->connect()->prepare('SELECT * FROM director ORDER BY rand() LIMIT 10');
    $query2->execute();
    $row2 = $query2-> fetchAll(PDO::FETCH_NUM);

    $query3 = $db->connect()->prepare('SELECT * FROM genero ORDER BY rand() LIMIT 10');
    $query3->execute();
    $row3 = $query3-> fetchAll(PDO::FETCH_NUM);

?>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <meta http-equiv="X-UA-COompatible" content="ie=edge">
 
        <link rel="stylesheet" href="elecciones.css">  
        <title>Registro</title>
    </head>
    <body>
        <div class="box">
            Ingrese sus datos para registrar
            <form action="http://localhost:8080/PelculaRecomendador/webresources/Gustos" method="POST">
                <label>Seleccione los actores que le gusta</label><br/>
                <select name="listaActores[]" multiple="multiple" class="selecciona">
                    <?php
                        foreach($row as $valor){
                    ?>
                    <option value="<?php echo $valor[0]; ?>"><?php echo $valor[1]; ?></option>
                    <?php
                        }
                    ?>
                </select>
                <br><br>
                <label>Seleccione los directores que le gusta</label><br/>
                <select name="listaDirectores[]" multiple="multiple" class="selecciona">
                    <?php
                        foreach($row2 as $valor){
                    ?>
                    <option value="<?php echo $valor[0]; ?>"><?php echo $valor[1]; ?></option>
                    <?php
                        }
                    ?>
                </select>
                <br><br>
                <label>Seleccione los generos que le gusta</label><br/>
                <select name="listaGeneros[]" multiple="multiple" class="selecciona">
                    <?php
                        foreach($row3 as $valor){
                    ?>
                    <option value="<?php echo $valor[0]; ?>"><?php echo $valor[1]; ?></option>
                    <?php
                        }
                    ?>
                </select>
                <br><br>
                <input type="submit" value="Registrar" class="enviar">
            </form>
        </div>
    </body>
</html>  