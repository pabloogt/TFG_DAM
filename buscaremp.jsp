<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
    <%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa", "root", "AlumnoIFP");
        Statement s = conexion.createStatement();
    
        String selectedID = request.getParameter("ID_EmpleadoB");
        ResultSet empleadoResult = s.executeQuery("SELECT * FROM empleado WHERE ID_Empleado = '" + selectedID + "'");
        s.close();
    %>
    <br>
    <script>document.location="emp.jsp"</script>
</body>
</html>