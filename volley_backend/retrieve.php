<?php 

	$connection = mysqli_connect("localhost","root","","developeru_bd");
	
	$result = array();
	$result['data'] = array();
	$select= "SELECT *from usuario";
	$responce = mysqli_query($connection,$select);
	
	while($row = mysqli_fetch_array($responce))
		{
			$index['id']      = $row['0'];
			$index['nombre']    = $row['1'];
			$index['apellido']   = $row['2'];
			$index['email'] = $row['3'];
			$index['contraseña'] = $row['4'];
			
			array_push($result['data'], $index);
		}
			
			$result["success"]="1";
			echo json_encode($result);
			mysqli_close($connection);

 ?>