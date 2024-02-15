<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Statement"%>
<%@ page import="java.sql.ResultSet"%>
<%@ page import="java.sql.DriverManager"%>
<%@ page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<!-- Bootstrap -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css"
	integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7"
	crossorigin="anonymous">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css"
	integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r"
	crossorigin="anonymous">
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js"
	integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS"
	crossorigin="anonymous"></script>

<title>empleado</title>
</head>
<style>
* {
	box-sizing: border-box;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
}

body {
	font-family: Helvetica;
	-webkit-font-smoothing: antialiased;
	background: rgba(71, 147, 227, 1);
}

h2 {
	text-align: center;
	font-size: 18px;
	text-transform: uppercase;
	letter-spacing: 1px;
	color: white;
	padding: 30px 0;
}

/* Table Styles */
.table-wrapper {
	margin: 10px 70px 70px;
	box-shadow: 0px 35px 50px rgba(0, 0, 0, 0.2);
}

.fl-table {
	border-radius: 5px;
	font-size: 12px;
	font-weight: normal;
	border: none;
	border-collapse: collapse;
	width: 100%;
	max-width: 100%;
	white-space: nowrap;
	background-color: white;
}

.fl-table td, .fl-table th {
	text-align: center;
	padding: 8px;
}

.fl-table td {
	border-right: 1px solid #f8f8f8;
	font-size: 12px;
}

.fl-table thead th {
	color: #ffffff;
	background: #4FC3A1;
}

.fl-table thead th:nth-child(odd) {
	color: #ffffff;
	background: #324960;
}

.fl-table tr:nth-child(even) {
	background: #F8F8F8;
}

/* Responsive */
@media ( max-width : 767px) {
	.fl-table {
		display: block;
		width: 100%;
	}
	.table-wrapper:before {
		content: "Scroll horizontally >";
		display: block;
		text-align: right;
		font-size: 11px;
		color: white;
		padding: 0 0 10px;
	}
	.fl-table thead, .fl-table tbody, .fl-table thead th {
		display: block;
	}
	.fl-table thead th:last-child {
		border-bottom: none;
	}
	.fl-table thead {
		float: left;
	}
	.fl-table tbody {
		width: auto;
		position: relative;
		overflow-x: auto;
	}
	.fl-table td, .fl-table th {
		padding: 20px .625em .625em .625em;
		height: 60px;
		vertical-align: middle;
		box-sizing: border-box;
		overflow-x: hidden;
		overflow-y: auto;
		width: 120px;
		font-size: 13px;
		text-overflow: ellipsis;
	}
	.fl-table thead th {
		text-align: left;
		border-bottom: 1px solid #f7f7f9;
	}
	.fl-table tbody tr {
		display: table-cell;
	}
	.fl-table tbody tr:nth-child(odd) {
		background: none;
	}
	.fl-table tr:nth-child(even) {
		background: transparent;
	}
	.fl-table tr td:nth-child(odd) {
		background: #F8F8F8;
		border-right: 1px solid #E6E4E4;
	}
	.fl-table tr td:nth-child(even) {
		border-right: 1px solid #E6E4E4;
	}
	.fl-table tbody td {
		display: block;
		text-align: center;
	}
}
</style>
<body>
	<nav class="navbar navbar-default">
		<div class="container-fluid">
			<div class="navbar-header">
				<a class="navbar-brand" href="#">OP</a>
			</div>
			<ul class="nav navbar-nav">
				<li><a href="http://localhost:8080/empresa/menu.jsp">Menu</a></li>
				<li><a href="http://localhost:8080/empresa/index_Pro.jsp">Proveedor</a></li>
				<li class="active"><a href="#">Empleado</a></li>
				<li><a href="http://localhost:8080/empresa/index_Cli.jsp">Cliente</a></li>
				<li><a href="http://localhost:8080/empresa/index_P.jsp">Producto</a></li>
			</ul>
		</div>
	</nav>
	<%
	Class.forName("com.mysql.cj.jdbc.Driver");
	Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "AlumnoIFP");
	Statement s = conexion.createStatement();
	ResultSet listado = s.executeQuery("SELECT * FROM empleado");

	Class.forName("com.mysql.cj.jdbc.Driver");
	conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "AlumnoIFP");
	s = conexion.createStatement();
	ResultSet listado1 = s.executeQuery("SELECT * FROM empleado");
	%>

	<table class="fl-table">
		<tr>
			<th>ID_Tienda</th>
			<th>Nombre</th>
			<th>Apellido_1</th>
			<th>Apellido_2</th>
			<th>DNI_NIF</th>
			<th>Telefono</th>
			<th>Email</th>
			<th>ID_Empleado</th>
			<th>Acciones</th>
		</tr>
		<tr><form method="get" action="buscaremp.jsp">
				<td><input type="text" name="ID_TiendaB" size="5"></td>
				<td><input type="text" name="NombreB" size="15"></td>
				<td><input type="text" name="Apellido_1B" size="15"></td>
				<td><input type="text" name="Apellido_2B" size="15"></td>
				<td><input type="text" name="DNI_NIFB" size="15"></td>
				<td><input type="text" name="TelefonoB" size="15"></td>
				<td><input type="text" name="EmailB" size="20"></td>
						
				<td><select name="ID_EmpleadoB">
						<%
						while (listado1.next()) {
							out.println("<option>" + listado1.getInt("ID_Empleado") + "</option>");
						}
						%>
				</select></td>
				<td><button type="submit" name="Buscar" class="btn btn-primary"
						size="30">
						<span class="glyphicon glyphicon-search"></span> Buscar
					</button></td>
		</form>
		
				<td></td>
			</tr>
		

	</table>

	<br>
	<br>

	<table class="fl-table">
		<tr>
			<th>ID_Empleado</th>
			<th>ID_Tienda</th>
			<th>Nombre</th>
			<th>Apellido_1</th>
			<th>Apellido_2</th>
			<th>DNI_NIF</th>
			<th>Telefono</th>
			<th>Email</th>
			<th>Acciones</th>
		</tr>
		<form method="get" action="grabaemp.jsp">
			<tr>
				<td><input type="text" name="ID_Empleado" size="5"></td>
				<td><input type="text" name="ID_Tienda" size="5"></td>
				<td><input type="text" name="Nombre" size="15"></td>
				<td><input type="text" name="Apellido_1" size="15"></td>
				<td><input type="text" name="Apellido_2" size="15"></td>
				<td><input type="text" name="DNI_NIF" size="15"></td>
				<td><input type="text" name="Telefono" size="15"></td>
				<td><input type="text" name="Email" size="20"></td>
				<td><button type="submit" name="Anadir" class="btn btn-primary"
						size="30">
						<span class="glyphicon glyphicon-plus"></span> Añadir
					</button></td>
				<td></td>
			</tr>
		</form>

		<%
		while (listado.next()) {
			out.println("<tr><td>");
			out.println(listado.getInt("ID_Empleado") + "</td>");
			out.println("<td>" + listado.getInt("ID_Tienda") + "</td>");
			out.println("<td>" + listado.getString("Nombre") + "</td>");
			out.println("<td>" + listado.getString("Apellido_1") + "</td>");
			out.println("<td>" + listado.getString("Apellido_2") + "</td>");
			out.println("<td>" + listado.getString("DNI_NIF") + "</td>");
			out.println("<td>" + listado.getString("Telefono") + "</td>");
			out.println("<td>" + listado.getString("Email") + "</td>");
		%>

		<td>
			<form method="get" action="modificaemp.jsp">
				<input type="hidden" name="ID_Empleado"
					value="<%=listado.getString("ID_Empleado")%>"> <input
					type="hidden" name="ID_Tienda"
					value="<%=listado.getString("ID_Tienda")%>"> <input
					type="hidden" name="Nombre"
					value="<%=listado.getString("Nombre")%>"> <input
					type="hidden" name="Apellido_1"
					value="<%=listado.getString("Apellido_1")%>"> <input
					type="hidden" name="Apellido_2"
					value="<%=listado.getString("Apellido_2")%>"> <input
					type="hidden" name="DNI_NIF"
					value="<%=listado.getString("DNI_NIF")%>"> <input
					type="hidden" name="Telefono"
					value="<%=listado.getString("Telefono")%>"> <input
					type="hidden" name="Email" value="<%=listado.getString("Email")%>">
				<button type="submit" class="btn btn-info">
					<span class="glyphicon glyphicon-pencil"></span> Modificar
				</button>
			</form>
		</td>
		<td>
			<form action="borraremp.jsp" method="get">
				<input type="hidden" name="ID_Empleado"
					value="<%=listado.getString("ID_Empleado")%>">
				<button type="submit" class="btn btn-danger">
					<span class="glyphicon glyphicon-remove"></span> Eliminar
				</button>
			</form>
		</td>
		</tr>

		<%
		}
		conexion.close();
		%>
	</table>

	<!-- jQuery (necesario para los complementos JavaScript de Bootstrap) -->
	<script src="js/jquery.min.js"></script>
	<script src="js/bootstrap.min.js"></script>

</body>
</html>
