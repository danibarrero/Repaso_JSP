package org.iesvdm.repaso_jsp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.repaso_jsp.dao.ClienteDAO;
import org.iesvdm.repaso_jsp.dao.ClienteDAOImpl;
import org.iesvdm.repaso_jsp.dao.PedidoDAO;
import org.iesvdm.repaso_jsp.dao.PedidoDAOImpl;
import org.iesvdm.repaso_jsp.model.Cliente;
import org.iesvdm.repaso_jsp.model.Pedido;

import java.io.IOException;
import java.util.List;
import java.util.Optional;


@WebServlet(name = "CrearPedidoServlet", value = "/CrearPedidoServlet")
public class CrearPedidoServlet extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();
    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Cliente> clientes = clienteDAO.getAll();
        request.setAttribute("clientes", clientes);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormularioPedidos.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        Optional<Pedido> optionalSocio = UtilServlet.validaGrabar(request);

        if (optionalSocio.isPresent()) {

            Pedido pedido = optionalSocio.get();

            this.pedidoDAO.create(pedido);

            List<Pedido> listado = this.pedidoDAO.getAll();

            request.setAttribute("listado", listado);

            request.setAttribute("newPedidoID", pedido.getId() );

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoPedidos.jsp");

        } else {

            request.setAttribute("error", "Error de validación!");

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormularioPedidos.jsp");
        }

        dispatcher.forward(request,response);
    }
}
