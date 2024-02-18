<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<!DOCTYPE html>
<html>
<head>
	<meta http-eqiv="Content-Type" content="text/html; charset=UTF-8">
</head>
<body>
	<%
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/empresa","root","pablito12");
		Statement s = conexion.createStatement();
	
		s.execute ("DELETE FROM cabecera_ventas WHERE ID_ventas="+ request.getParameter("ID_Ventas"));
		s.execute ("DELETE FROM DEtalle_ventas WHERE N_Linea="+ request.getParameter("ID_Ventas"));
		s.close();
	%>
	<br>
    <script>document.location="indexV.jsp"</script>
</body>
</html>