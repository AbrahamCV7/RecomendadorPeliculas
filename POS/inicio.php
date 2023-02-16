<?php
    include_once 'database.php';

    session_start();

    $db = new Database();

    if(isset($_GET['cerrar_sesion'])){
        session_unset();
        session_destroy();
    }

    //Si intenta regresar al login cuando ya hay una sesion lo manda a "seleccion.php para no iniciar otra"
    if(!isset($_SESSION['id'])){
        header('location: login.php'); //inicio aun no esta
    }
    

    $query = $db->connect()->prepare('SELECT * FROM listaactor WHERE User_id_User = "'.$_SESSION['id'].'"');
    $query->execute();
    $row = $query-> fetchAll(PDO::FETCH_NUM);
    
  


    $query2 = $db->connect()->prepare('SELECT * FROM listadirector WHERE User_id_User = :idU');
    $query2->execute(['idU' => $_SESSION['id']]);
    $row2 = $query2-> fetchAll(PDO::FETCH_NUM);

    $query3 = $db->connect()->prepare('SELECT * FROM listagenero WHERE User_id_User = :idU');
    $query3->execute(['idU' => $_SESSION['id']]);
    $row3 = $query3-> fetchAll(PDO::FETCH_NUM);
?>

<!DOCTYPE html>
<!--
Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Html.html to edit this template
-->
<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <!-- CSS only -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-rbsA2VBKQhggwzxH7pPCaAqO46MgnOM80zW1RWuH61DGLwZJEdK2Kadq2F9CUG65" crossorigin="anonymous">
    </head>
    <body style="background-color:#212529;">
        <div class="container">
            <h1 >Recomendador de peliculas</h1>
            
                <nav class="navbar navbar-light bg-light d-flex justify-content-around">
                    <div class="container-fluid">
                      <a class="navbar-brand">Bienvenido <?php echo $_SESSION['user']; ?></a>
                        <form class="d-flex" action="http://localhost:8080/PeliculaBuscador/BuscadorPeliculaServlet">
                            <input id="pelicula" class="form-control me-1" type="search" placeholder="Pelicula a buscar" aria-label="Search" name="titulo" required>
                            <button  class="btn btn-outline-success " type="submit" >buscar</button>
                            <a href="inicio.php?cerrar_sesion=1" class="btn btn-danger  me-3">Cerrar sesion</a>
                      </form>
                    </div>
                  </nav>
            
            
        </div >
        <div name="recomendaciones">
            <form action="http://localhost:8080/PeliculaBuscador/DetallesPeliculaServlet">
                <h1><span class="badge bg-secondary">Basadas en tus actores favoritos</span></h1>
                <div class="row row-cols-1 row-cols-md-4 g-4">
                    <?php //Por actor
                   
                    foreach($row as $valor){ //Los actores que le gustan al usuario
                        
                        $queryPeli = $db->connect()->prepare('SELECT * from pelicula inner join peliculaactor on pelicula.idImdb= peliculaactor.idPelicula inner join actor on peliculaactor.idActor=actor.idActor where actor.idActor=:idAc');
                        $queryPeli->execute(['idAc' => $valor[2]]);
                        $rowPeli = $queryPeli-> fetchAll(PDO::FETCH_NUM);
                        $cont = 0;
                        foreach($rowPeli as $valorPeli){ //Pelis con ese actor
                           
                    ?>
                            <div class="col">
                                <div class="card border-danger mb-3"style="width: 25rem;">
                                    <img src="<?php echo $valorPeli[3]; ?>" class="card-img-top" alt="...">
                                    <div class="card-body" style="background-color:#566573;"  >
                                        <h5 class="card-title"><?php echo $valorPeli[1]; ?></h5>
                                        <button type="submit" value="<?php echo $valorPeli[2]; ?>" name="pelicula" class="btn btn-warning">Ver Detalles</button>
                                    </div>
                                </div>
                            </div>    
                        <?php
                        $cont++;
                        if ($cont > 8) {
                            break;
                            }
                        }
                    }
                    ?>
                </div>

                <h1><span class="badge bg-secondary">Basadas en tus Directores favoritos</span></h1>
                <div class="row row-cols-1 row-cols-md-4 g-4">
                <?php //Por director
                    foreach($row2 as $valor2){ //Los actores que le gustan al usuario
                        $queryPeli = $db->connect()->prepare('SELECT * from pelicula inner join peliculadirector on pelicula.idImdb= peliculadirector.idPDPelicula inner join director on peliculadirector.idPDDirector=director.IdDirector where director.idDirector=:idDi');
                        $queryPeli->execute(['idDi' => $valor2[2]]);
                        $rowPeli = $queryPeli-> fetchAll(PDO::FETCH_NUM);
                        $cont=0;
                        foreach($rowPeli as $valorPeli){ //Pelis con el director
                    ?>
                            <div class="col">
                                <div class="card border-danger" style="width: 25rem;">
                                    <img src="<?php echo $valorPeli[3]; ?>" class="card-img-top" alt="...">
                                    <div class="card-body" style="background-color:#566573;" >
                                        <h5 class="card-title"><?php echo $valorPeli[1]; ?></h5>
                                        <button type="submit" value="<?php echo $valorPeli[2]; ?>" name="pelicula" class="btn btn-warning">Ver Detalles</button>
                                    </div>
                                </div>
                            </div>   

                        <?php
                         $cont++;
                        if ($cont > 8) {
                            break;
                            }
                        
                        }
                    }
                    ?>
                </div>
                <br>
                

                <h1><span class="badge bg-secondary">Basadas en tus generos favoritos</span></h1>
                <div class="row row-cols-1 row-cols-md-4 g-4">
                <?php //Por genero
                    $cont = 0;
                    foreach($row3 as $valor3){ //Los generos que le gustan al usuario
                        $queryPeli = $db->connect()->prepare('SELECT * from pelicula inner join peliculagenero on pelicula.idImdb= peliculagenero.idPGPelicula inner join genero on peliculagenero.idPGGenero=genero.idGenero where genero.idGenero=:idGe');
                        $queryPeli->execute(['idGe' => $valor3[2]]);
                        $rowPeli = $queryPeli-> fetchAll(PDO::FETCH_NUM);
                     
                        foreach($rowPeli as $valorPeli){ //Pelis con el genero
                    ?>
                            <div class="col">
                                <div class="card border-danger" style="width: 25rem;">
                                    <img src="<?php echo $valorPeli[3]; ?>" class="card-img-top" alt="...">
                                    <div class="card-body" style="background-color:#566573;" >
                                        <h5 class="card-title"><?php echo $valorPeli[1]; ?></h5>
                                        <button type="submit" value="<?php echo $valorPeli[2]; ?>" name="pelicula" class="btn btn-warning">Ver Detalles</button>
                                    </div>
                                </div>
                            </div>    
                        <?php
                         $cont++;
                         if ($cont > 8) {
                            break;
                         }
                        }
                    }
                    ?>
                </div>
            </form>
        </div>       
    </body>
</html>