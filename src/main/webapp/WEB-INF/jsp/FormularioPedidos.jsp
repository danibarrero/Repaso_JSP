<%@ page import="org.iesvdm.repaso_jsp.model.Cliente" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>FORMULARIO CREAR PEDIDOS</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/water.css@2/out/light.css">
</head>
<body>
<h3>FORMULARIO PEDIDO</h3>

<div>
    <div>
        <div>Introduzca los datos del nuevo pedido</div>
        <br>
    </div>
</div>

<div>
    <form method="post" action="CrearPedidoServlet">
        <div>
            <div>Total</div>
            <div><input type="number" name="total"/></div>
            <br>
        </div>
        <div>
            <div>Fecha</div>
            <div><input type="date" name="fecha"/></div>
            <br>
        </div>
        <!-- Cliente como radio buttons -->
        <div>
            <div>Cliente</div>
            <div>
                <%
                    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                    if (clientes != null) {
                        for (Cliente cliente : clientes) {
                %>
                <label>
                    <input type="radio" name="id_cliente" value="<%= cliente.getId() %>">
                    <%= cliente.getNombre() %>
                </label><br>
                <%
                    }
                } else {
                %>
                <div>No hay clientes disponibles</div>
                <%
                    }
                %>
            </div>
            <br>
        </div>
        <div>
            <div>Comercial</div>
            <div><input type="text" name="id_comercial"/></div>
            <br>
        </div>

        <!-- Botón aceptar -->
        <div>
            <div>
                <form method="get" action="CrearPedidoServlet">
                    <input type="submit" value="Aceptar">
                </form>
            </div>
        </div>
    </form>

    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>

    <div>
        <div>
            <div>
                <strong>Error!</strong> <%=error%>
                <button type="button" data-bs-dismiss="alert"></button>
            </div>
        </div>
    </div>

    <%
        }
    %>

</div>

</body>
</html>
