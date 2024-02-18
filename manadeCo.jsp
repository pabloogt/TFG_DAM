<%@page import="java.sql.Statement"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

  <head>

    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    

    <!-- Bootstrap -->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>
	<style>
		.azul{
			  background-color: lightblue;
			}
		body{
			background-image: linear-gradient(white, gray, black);
			}
	</style>
    <title>Gestisimal</title>

  </head>

  <body>

    <% request.setCharacterEncoding("UTF-8"); %>

    <div class="container">
<%
          Class.forName("com.mysql.jdbc.Driver");
          Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root", "pablito12");
          Statement s = conexion.createStatement();

          ResultSet listado = s.executeQuery ("SELECT id_proveedor FROM proveedor");
          
          Class.forName("com.mysql.jdbc.Driver");
           conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root", "pablito12");
           s = conexion.createStatement();
           ResultSet listado2 = s.executeQuery ("SELECT Nombre FROM proveedor");
           
          Class.forName("com.mysql.jdbc.Driver");
           conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root", "pablito12");
           s = conexion.createStatement();
           ResultSet listado3 = s.executeQuery ("SELECT id_tienda FROM empleado");
           
          Class.forName("com.mysql.jdbc.Driver");
           conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root", "pablito12");
           s = conexion.createStatement();
           ResultSet listado4 = s.executeQuery ("SELECT id_producto FROM producto");
          Class.forName("com.mysql.jdbc.Driver");
           conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root", "pablito12");
           s = conexion.createStatement();
           ResultSet listado5 = s.executeQuery ("SELECT nombre FROM producto");
          Class.forName("com.mysql.jdbc.Driver");
           conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root", "pablito12");
           s = conexion.createStatement();
           ResultSet listado6 = s.executeQuery ("SELECT precio_venta FROM producto");
			
        %>
      <br><br>

      <div class="panel panel-info">

        <div class="panel panel-info panel-heading text-center azul">Cabecera - Compras</div>
          
          <form method="get" action="grabaCompra.jsp">
            <div class="form-group"> 
              <label>&nbsp;&nbsp;ID Compras:&nbsp;</label><input type="text" size="5" name="ID_Compras" value="1">
              <label>&nbsp;&nbsp;Nº de factura:&nbsp;</label><input type="text" size="5" name="N_Factura" value="34">
            </div>
            <div class="form-group">
           
             <label>&nbsp;&nbsp;Código de proveedor:&nbsp;</label>
             <select name="Codigo_proveedor" >
				        <%
				            while (listado.next()) {
				                out.println("<option>" + listado.getInt("id_proveedor") + "</option>");
				            }
				        %>
				    </select>
             <label>&nbsp;&nbsp;Nombre de proveedor:&nbsp;</label>
              <select name="Nombre_Proveedor" >
				        <%
				            while (listado2.next()) {
				                out.println("<option>" + listado2.getString("nombre") + "</option>");
				            }
				        %>
				    </select>        
            </div>
            <div class="form-group">
			 <label>&nbsp;&nbsp;Fecha:&nbsp;</label><input type="text" name="Fecha" size="20" value="2022-10-31">
			 <label>&nbsp;&nbsp;ID Tienda:&nbsp;</label>
			 <select name="ID_tienda" >
				        <%
				            while (listado3.next()) {
				                out.println("<option>" + listado3.getString("id_tienda") + "</option>");
				            }
				        %>
				    </select> 
			 <label>&nbsp;&nbsp;Importe de factura:&nbsp;</label><input type="text" name="Importe_Factura" size="20" value="precio_unidad * cantidad" readonly>           
			
            </div>
            <hr>
          
        <div class="panel panel-info panel-heading text-center azul">Detalles - Compras</div>
          
            <div class="form-group"> 
              <label>&nbsp;&nbsp;Nº de línea:&nbsp;</label><input type="text" size="20" name="N_Linea" value="Mismo id que id_compras" readonly>
            </div>
            <div class="form-group">
             <label>&nbsp;&nbsp;Código de artículo:&nbsp;</label>
             <select name="Codigo_Articulo" >
				        <%
				            while (listado4.next()) {
				                out.println("<option>" + listado4.getString("id_producto") + "</option>");
				            }
				        %>
				    </select> 
             <label>&nbsp;&nbsp;Nombre de artículo:&nbsp;</label>
                <select name="Nombre_Articulo" >
				        <%
				            while (listado5.next()) {
				                out.println("<option>" + listado5.getString("nombre") + "</option>");
				            }
				        %>
				    </select>       
            </div>
            <div class="form-group">
			 <label>&nbsp;&nbsp;Cantidad:&nbsp;</label><input type="text" name="Cantidad" size="10" value="2">
			 <label>&nbsp;&nbsp;Precio:&nbsp;</label>
			 <select name="Precio" >
				        <%
				            while (listado6.next()) {
				                out.println("<option>" + listado6.getString("precio_venta") + "</option>");
				            }
				        %>
				    </select>
			 <label>&nbsp;&nbsp;Importe de línea:&nbsp;</label><input type="text" name="Importe_Linea" size="10" value="rojo">           
            </div>
            <hr>
            &nbsp;&nbsp;<a href="indexC.jsp" class="btn btn-danger"><span class="glyphicon glyphicon-remove"></span>Cancelar</a>

            <button type="submit" class="btn btn-success"><span class="glyphicon glyphicon-ok"></span>Aceptar</button><br><br>
		</form>
      </div>
		
      <div class="text-center">&copy; Oscar & Pablo</div>

    </div>

    <!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->

    <script src="js/jquery.min.js"></script>

    <script src="js/bootstrap.min.js"></script>

  </body>

</html>
