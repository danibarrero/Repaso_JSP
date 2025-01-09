package org.iesvdm.repaso_jsp.servlet;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.iesvdm.repaso_jsp.dao.PedidoDAO;
import org.iesvdm.repaso_jsp.dao.PedidoDAOImpl;
import org.iesvdm.repaso_jsp.model.Pedido;

import java.io.IOException;
import java.util.Optional;

@WebServlet(name = "GrabarSocioServlet", value = "/GrabarSocioServlet")
public class CrearSocioServlet {
    private PedidoDAO pedidoDAO = new PedidoDAOImpl();


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/FormularioPedidos.jsp");

        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = null;

        Optional<Pedido> optionalSocio = UtilServlet.validaGrabar(request);

        if (optionalSocio.isPresent()) {

            Socio socio = optionalSocio.get();

            this.socioDAO.create(socio);

            List<Socio> listado = this.socioDAO.getAll();

                                  V
            request.setAttribute("listado", listado);


            request.setAttribute("newSocioID", socio.getSocioId() );

                                                                           V
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/listadoSociosB.jsp");
        } else {
            request.setAttribute("error", "Error de validación!");
                                                                      V
            dispatcher = request.getRequestDispatcher("/WEB-INF/jsp/formularioSocioB.jsp");
        }

        dispatcher.forward(request,response);
    }
}
