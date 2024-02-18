<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
     <meta charset="utf-8">
      <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	<style>
		nav,form { display: flex;
  				justify-content: center;
  				}
	</style>
    <title>Compras</title>
</head>
<body>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="navbar-header">
      <a class="navbar-brand" href="#">OP</a>
    </div>
    <ul class="nav navbar-nav">
      <li><a href="http://localhost:8080/sistEmpresarial/menu.jsp">Menu</a></li>
      <li><a href="http://localhost:8080/sistEmpresarial/index_Pro.jsp">Proveedor</a></li>
      <li><a href="http://localhost:8080/sistEmpresarial/emp.jsp">Empleado</a></li>
      <li><a href="http://localhost:8080/sistEmpresarial/index.jsp">Cliente</a></li>
      <li><a href="http://localhost:8080/sistEmpresarial/index_P.jsp">Producto</a></li>
      <li class="active"><a href="http://localhost:8080/sistEmpresarial/indexC.jsp">Compras</a></li>
      <li><a href="http://localhost:8080/sistEmpresarial/indexV.jsp">Ventas</a></li>
    </ul>
  </div>
</nav>

<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","pablito12");
    Statement s = conexion.createStatement();
    ResultSet listado = s.executeQuery("SELECT * FROM Cabecera_compras");
    
    Class.forName("com.mysql.cj.jdbc.Driver");
    conexion= DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","pablito12");
    s = conexion.createStatement();
    ResultSet listado2 = s.executeQuery("SELECT * FROM DETALLE_compras");
%>

<table class="table table-striped">
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<form method="get" action="manadeCo.jsp" >
     		        <button type="submit" name="Anadir" class="btn btn-primary" size="30">
     		                <span class="bi bi-plus"></span>Añadir</button>
    </form>
    <% 
        while(listado.next()){%>
        	 <tr>
             <th>ID_compras</th>
             <th>NªFacturas</th>
             <th>CodigoProveedor</th>
             <th>NombreProveedor</th>
             <th>Fecha</th>
             <th>ID_tienda</th>
             <th>ImporteFacturas</th>
             
         </tr><% 
            out.println("<tr><td>");
            out.println(listado.getInt("ID_compras") + "</td>");
            out.println("<td>" + listado.getInt("N_Factura") + "</td>");
            out.println("<td>" + listado.getInt("Codigo_proveedor") + "</td>");
            out.println("<td>" + listado.getString("Nombre_proveedor") + "</td>");
            out.println("<td>" + listado.getString("Fecha") + "</td>");
            out.println("<td>" + listado.getInt("ID_tienda") + "</td>");
            out.println("<td>" + listado.getDouble("Importe_factura") + "</td>");
    %>

    <td>
        <form action="borrarCompra.jsp" method="get">
            <input type="hidden" name="ID_Compras" value="<%=listado.getString("ID_compras") %>">
            <button type="submit" class="btn btn-danger"><span class="bi bi-remove"></span>Eliminar</button>
        </form>
    </td></tr>
	
            <tr>
		        <th>NºLinea</th>
		        <th>CodigoArticulo</th>
		        <th>NombreArticulo</th>
		        <th>Cantidad</th>
		        <th>Precio</th>
		        <th>ImporteLinea</th>
		    </tr>
            <% 
            if(listado2.next()){
            out.println("<tr><td>");
            out.println(listado2.getInt("N_linea") + "</td>");
            out.println("<td>" + listado2.getInt("Codigo_articulo") + "</td>");
            out.println("<td>" + listado2.getString("Nombre_articulo") + "</td>");
            out.println("<td>" + listado2.getInt("Cantidad") + "</td>");
            out.println("<td>" + listado2.getDouble("Precio") + "</td>");
            out.println("<td>" + listado2.getString("Importe_linea") + "</td></tr>");}
    %><p></p>
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
