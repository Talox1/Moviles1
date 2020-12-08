<?php

$connection = mysqli_connect("localhost","root","","developeru_bd");
    
     $id = $_POST["id"];
     $nombre = $_POST["nombre"];
     $email = $_POST["email"];
     $apellido = $_POST["apellido"];
     $contraseña = $_POST["contraseña"];
     
     $sql = "UPDATE usuario SET  nombre = '$nombre', email = '$email', apellido = '$apellido', contraseña = '$contraseña' WHERE id = '$id' ";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Updated";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     
        
?>