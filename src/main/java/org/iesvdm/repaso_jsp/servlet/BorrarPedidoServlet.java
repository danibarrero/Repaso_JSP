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

@WebServlet(name = "BorrarPedidoServlet", value = "/BorrarPedidoServlet")
public class BorrarPedidoServlet extends HttpServlet {

    private PedidoDAO pedidoDAO = new PedidoDAOImpl();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        String idSTR = request.getParameter("id");

        Integer id = null;

        try {
            id = Integer.parseInt(idSTR);
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }

        if (id != null) {
            //Parametro valido
            pedidoDAO.delete(id);

            //REDIRECCION INTERNA
            List<Pedido> listado = pedidoDAO.getAll();
            request.setAttribute("listado", listado);

            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/ListadoPedidos.jsp");
            dispatcher.forward(request, response);

        }
    }
}
