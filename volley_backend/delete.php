<?php

$connection = mysqli_connect("localhost","root","","developeru_bd");
    
     $id = $_POST["id"];
     
     $sql = "DELETE FROM usuario WHERE id='$id'";
     
     $result = mysqli_query($connection,$sql);
     
     if($result){
         echo "Data Deleted";
        
     }
     else{
         echo "Failed";
     }
     mysqli_close($connection);
     


?>