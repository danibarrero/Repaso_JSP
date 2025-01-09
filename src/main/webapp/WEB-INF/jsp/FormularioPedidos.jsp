<%@ page import="org.iesvdm.repaso_jsp.model.Cliente" %>
<%@ page import="java.util.List" %>
<%@ page import="org.iesvdm.repaso_jsp.model.Comercial" %>
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

        <!-- CLIENTE CON RADIO BUTTON -->
        <!-- <div>
            <div>Cliente</div>
            <div><input type="number" name="id_cliente"></div>
            <br>
        </div> -->
        <div>
            <div>Cliente</div>
            <div>
                <%
                    List<Cliente> clientes = (List<Cliente>) request.getAttribute("clientes");
                    if (clientes != null) {
                        for (Cliente cliente : clientes) {
                %>
                <label>
                    <input type="radio" name="id_cliente" value="<%= cliente.getId() %>"> <!-- Este name tiene que ser igual que el name de EditarClienteServlet -->
                    <%= cliente.getNombre() %>
                    <%= cliente.getApellido1() %>
                    <%= cliente.getApellido2() %>
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

        <!-- COMERCIAL CON SELECCIÓN MÚLTIPLE -->
        <!-- <div>
        <div>Comercial</div>
            <div><input type="number" name="id_comercial"></div>
        <br>
        </div> -->
        <div>
            <div>Comercial</div>
            <div>
                <%
                    List<Comercial> comerciales = (List<Comercial>) request.getAttribute("comerciales"); // Mismo nombre que en CrearPedidoservlet
                    if (comerciales != null) {
                %>
                <select name="id_comercial" multiple size="5">
                    <%
                        for (Comercial comercial : comerciales) {
                    %>
                    <option value="<%= comercial.getId() %>">
                        <%= comercial.getNombre() %>
                        <%= comercial.getApellido1() %>
                        <%= comercial.getApellido2() %>
                    </option>
                    <%
                        }
                    %>
                </select>
                <%
                } else {
                %>
                <div>No hay comerciales disponibles</div>
                <%
                    }
                %>
            </div>
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
