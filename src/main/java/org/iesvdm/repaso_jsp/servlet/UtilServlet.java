package org.iesvdm.repaso_jsp.servlet;

import jakarta.servlet.http.HttpServletRequest;
import org.iesvdm.repaso_jsp.model.Cliente;
import org.iesvdm.repaso_jsp.model.Pedido;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Objects;
import java.util.Optional;

public class UtilServlet {
    public static Optional<Pedido> validaGrabar(HttpServletRequest request) {

        boolean valida = true;
        Double total = 0.0;
        Date fecha = null;
        int id_cliente = -1;
        int id_comercial = -1;

        try {

            // TOTAL
            Objects.requireNonNull(request.getParameter("total"));
            if (request.getParameter("total").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            if (Double.parseDouble(request.getParameter("total")) < 0) throw new Exception("No puede ser negativo");
            total = Double.parseDouble(request.getParameter("total"));

            // FECHA
            String fechaString = request.getParameter("fecha");
            if (fechaString == null) throw new RuntimeException("No válido");
            if (request.getParameter("fecha").isBlank()) throw new RuntimeException("No válido");
            SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
            formatoFecha.setLenient(false);

            try {
                java.util.Date fechaIngresada = formatoFecha.parse(fechaString);
                java.util.Date fechaActual = new java.util.Date();

                if (fechaIngresada.before(fechaActual)) {
                    throw new RuntimeException("La fecha no puede ser anterior al día actual");
                }

                fecha = new java.sql.Date(fechaIngresada.getTime());
            } catch (ParseException e) {
                throw new RuntimeException("Fecha inválida");
            }

            // ID_CLIENTE
            Objects.requireNonNull(request.getParameter("id_cliente"));
            if (request.getParameter("id_cliente").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            id_cliente = Integer.parseInt(request.getParameter("id_cliente"));

            // ID_COMERCIAL
            Objects.requireNonNull(request.getParameter("id_comercial"));
            if (request.getParameter("id_comercial").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            id_comercial = Integer.parseInt(request.getParameter("id_comercial"));


            return Optional.of(new Pedido(-1, total, fecha, id_cliente, id_comercial));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }

    public static Optional<Cliente> validarEditar(HttpServletRequest request) {

        boolean valida = true;
        String nombre = null;
        String apellido1 = null;
        String apellido2 = null;
        String ciudad = null;
        int categoria = 0;

        try {
            // NOMBRE
            Objects.requireNonNull(request.getParameter("nombre"));
            if (request.getParameter("nombre").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            nombre = request.getParameter("nombre");

            // APELLIDO1
            Objects.requireNonNull(request.getParameter("apellido1"));
            if (request.getParameter("apellido1").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            apellido1 = request.getParameter("apellido1");

            // APELLIDO2
            Objects.requireNonNull(request.getParameter("apellido2"));
            if (request.getParameter("apellido2").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            apellido2 = request.getParameter("apellido2");

            //CIUDAD
            Objects.requireNonNull(request.getParameter("ciudad"));
            if (request.getParameter("ciudad").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios en blancos.");
            ciudad = request.getParameter("ciudad");

            // CATEGORÍA
            Objects.requireNonNull(request.getParameter("categoria"));
            if (request.getParameter("categoria").isBlank()) throw new RuntimeException("Parámetro vacío o todo espacios blancos.");
            categoria = Integer.parseInt(request.getParameter("categoria"));
            if (categoria < 0 || categoria > 10) throw new Exception("Tiene que estar entre 0 y 10");

            return Optional.of(new Cliente(-1, nombre, apellido1, apellido2, ciudad, categoria));

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return Optional.empty();
    }
}
