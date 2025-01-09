package org.iesvdm.repaso_jsp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.repaso_jsp.dao.ClienteDAO;
import org.iesvdm.repaso_jsp.dao.ClienteDAOImpl;
import org.iesvdm.repaso_jsp.model.Cliente;
import org.iesvdm.repaso_jsp.model.Pedido;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@WebServlet(name = "EditarClienteServlet", value = "/EditarClienteServlet")
public class EditarClienteServlet extends HttpServlet {

    private ClienteDAO clienteDAO = new ClienteDAOImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        String idSTR = request.getParameter("id");
        int id = Integer.parseInt(idSTR);

        try {
            Cliente cliente= this.clienteDAO.find(id).orElse(null);
            request.setAttribute("cliente", cliente);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormularioEditarCliente.jsp");
            dispatcher.forward(request, response);
        } catch (NumberFormatException e) {
            request.setAttribute("error", "No se encontro el ");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormularioEditarCliente.jsp");
            dispatcher.forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = null;

        int numero = 0;

        try {
            numero = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "ID Socio no válido");
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormularioEditarCliente.jsp");
            dispatcher.forward(request, response);
            return;
        }


        Optional<Cliente> socioOptional = this.clienteDAO.find(numero);
        if (!socioOptional.isPresent()) {
            request.setAttribute("error", "No existe el socio con el codigo: " + numero);
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormularioEditarCliente.jsp");
            dispatcher.forward(request, response);
            return;
        }

        Optional<Cliente> clienteValidado = UtilServlet.validarEditar(request);
        if (clienteValidado.isPresent()) {
            Cliente editarCliente = clienteValidado.get();
            editarCliente.setId(numero);

            this.clienteDAO.update(editarCliente);

            List<Cliente> listado = this.clienteDAO.getAll();
            request.setAttribute("listado", listado);
            request.getRequestDispatcher("/WEB-INF/jsp/ListadoPedidos.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Error en la validación de los datos, Inténtelo de nuevo");
            request.setAttribute("editarCliente", socioOptional.get());
            request.getRequestDispatcher("/WEB-INF/jsp/FormularioEditarCliente.jsp").forward(request, response);
        }
    }
}

