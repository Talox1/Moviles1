<?php

    $connection = mysqli_connect("localhost","root","","developeru_bd");

    $nombre = $_POST["nombre"];
    $apellido = $_POST["apellido"];
    $email = $_POST["email"];
    $contraseña = $_POST["contraseña"];
    
    $sql = "INSERT INTO usuario(nombre,apellido,email,contraseña) VALUES ('$nombre','$apellido','$email','$contraseña')";
    
    $result = mysqli_query($connection,$sql);
    
    if($result){
        echo "Data Inserted";
    }
    else{
        echo "Failed";
    }
    mysqli_close($connection);
?>