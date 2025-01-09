<%@ page import="java.sql.ResultSet" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Connection" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Listado Pedidos</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/light.css">
</head>
<body>

    <h3>LISTADO PEDIDOS</h3>

    <%
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/ventas","root", "secret");
        Statement s = conexion.createStatement();
        ResultSet listado = s.executeQuery("SELECT * FROM ventas.pedido " +
                "AS pedido JOIN ventas.cliente " +
                "AS cliente ON pedido.id_cliente = cliente.id JOIN ventas.comercial " +
                "AS comercial ON pedido.id_comercial = comercial.id");
        // ResultSet listado = s.executeQuery ("SELECT * FROM pedido");

    %>

    <div>
        <div>
            <form method="get" action="CrearPedidoServlet">
                <input type="submit" value="Crear Pedido">
            </form>
        </div>
    </div>

    <table>
        <tr>
            <th>ID</th>
            <th>TOTAL</th>
            <th>FECHA</th>
            <th>CLIENTE</th>
            <th>COMERCIAL</th>
        </tr>

        <%
            Integer pedidoDAODestacar = (Integer)session.getAttribute("pedidoDAODestacar");
            String claseDestacar = "";
            while (listado.next()) {
                claseDestacar = (pedidoDAODestacar != null
                        && pedidoDAODestacar ==listado.getInt("id") ) ?
                        "destacar" :  "";
        %>

        <tr class="<%= claseDestacar%>" >
            <td ><%=listado.getInt("id")%></td>
            <td><%=listado.getDouble("total")%></td>
            <td><%=listado.getDate("fecha")%></td>
            <td>
                <%= listado.getString("cliente.nombre")%>
                <%= listado.getString("cliente.apellido1")%>
                <%= listado.getString("cliente.apellido2")%>
            </td>
            <td>
                <%= listado.getString("comercial.nombre")%>
                <%= listado.getString("comercial.apellido1")%>
                <%= listado.getString("comercial.apellido2")%>
            </td>

            <td>
                <form method="get" action="EditarClienteServlet">
                    <input type="hidden" name="id" value="<%= listado.getInt("id_cliente")%>">
                    <input type="submit" value="Cliente">
                </form>
                <form method="post" action="BorrarPedidoServlet">
                    <input type="hidden" name="id" value="<%= listado.getInt("id")%>">
                    <input type="submit" value="Borrar">
                </form>
            </td>
        </tr>
        <%
            } // while
            conexion.close();
        %>
    </table>
</body>
</html>
