package org.iesvdm.repaso_jsp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.repaso_jsp.dao.PedidoDAO;
import org.iesvdm.repaso_jsp.dao.PedidoDAOImpl;
import org.iesvdm.repaso_jsp.model.Pedido;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "ListarSocioServlet", value = "/ListarSocioServlet")
public class ListarSocioServlet extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoPedidos.jsp");

        List<Pedido> listado = this.pedidoDAO.getAll();
        request.setAttribute("listado", listado);

        dispatcher.forward(request, response);

    }

}
