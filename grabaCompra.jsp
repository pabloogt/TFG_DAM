<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <!-- Bootstrap -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" integrity="sha384-1q8mTJOASx8j1Au+a5WDVnPi2lkFfwwEAa8hDDdjZlpLegxhjVME1fgjWPGmkzs7" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap-theme.min.css" integrity="sha384-fLW2N01lMqjakBkx3l/M9EahuwpSfeNvV63J5ezn3uZzapT0u7EYsXMjQV+0En5r" crossorigin="anonymous">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/js/bootstrap.min.js" integrity="sha384-0mSbJDEHialfmuBBQP6A4Qrprq5OVfW37PRR3j5ELqxss1yVqOtnepnHVP9aJ7xS" crossorigin="anonymous"></script>

    <title>Compras - Pablo</title>
</head>
<body>

<%
    Class.forName("com.mysql.cj.jdbc.Driver");
    Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "pablito12");
    Statement s = conexion.createStatement();
    request.setCharacterEncoding("UTF-8");

    String idClienteParam = request.getParameter("ID_Compras");

    if (idClienteParam != null && !idClienteParam.isEmpty()) {
        try {
            int idCliente = Integer.parseInt(idClienteParam);

            String consultaNumSocio = "SELECT * FROM Cabecera_compras WHERE ID_Compras = " + idCliente;
            ResultSet numeroDeSocios = s.executeQuery(consultaNumSocio);

            if (numeroDeSocios.next()) {
                out.println("Lo siento, no se ha podido dar de alta, ya existe una compra con el mismo id (" + idCliente + ").");
            } else {
            	String insercion = "INSERT INTO Cabecera_compras VALUES ("
                        + idCliente + ", "
                        + Integer.valueOf(request.getParameter("N_Factura")) + ", "
                        + Integer.valueOf(request.getParameter("Codigo_proveedor")) + ", '"
                        + request.getParameter("Nombre_Proveedor") + "', '"
                        + request.getParameter("Fecha") + "', "
                        + Integer.valueOf(request.getParameter("ID_tienda")) + ", "
                       	+ (Integer.valueOf(request.getParameter("Cantidad"))) * (Double.valueOf(request.getParameter("Precio")))+ ")";
    			s.execute(insercion);
                out.println("Cabecera_compra dado de alta correctamente");
            }
        } catch (NumberFormatException e) {
            out.println("Error al convertir ID_Compras a entero: " + e.getMessage()+"");
        }
    } else {
        out.println("El parámetro ID_Compras es nulo o vacío");
    }
    idClienteParam = request.getParameter("ID_Compras");

    if (idClienteParam != null && !idClienteParam.isEmpty()) {
        try {
            int idCliente = Integer.parseInt(idClienteParam);

            String consultaNumSocio = "SELECT * FROM Detalle_compras WHERE N_Linea = " + idCliente;
            ResultSet numeroDeSocios = s.executeQuery(consultaNumSocio);

            if (numeroDeSocios.next()) {
                out.println("Lo siento, no se ha podido dar de alta, ya existe una compra con el mismo id (" + idCliente + ").");
            } else {
            	String insercion = "INSERT INTO Detalle_compras VALUES ("
                        + idCliente + ", "
                        + Integer.valueOf(request.getParameter("Codigo_Articulo")) + ", '"
                        + request.getParameter("Nombre_Articulo") + "', "
                        + Integer.valueOf(request.getParameter("Cantidad")) + ", "
                        + Double.valueOf(request.getParameter("Precio")) + ", '"
                        + request.getParameter("Importe_Linea") + "')";
    			s.execute(insercion);
                out.println("Detalle_compras dado de alta correctamente");
            }
        } catch (NumberFormatException e) {
            out.println("Error al convertir N_Linea a entero: " + e.getMessage());
        }
    } else {
        out.println("El parámetro N_linea es nulo o vacío");
    }
    conexion.close();
%>

<br>
<a href="indexC.jsp" class="btn btn-primary"><span class="glyphicon glyphicon-home"></span> Página principal</a>

<!-- jQuery (necesario para los complementos JavaScript de Bootstrap) -->
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
