package org.iesvdm.repaso_jsp.dao;

import org.iesvdm.repaso_jsp.model.Pedido;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PedidoDAOImpl extends AbstractDAOImpl implements PedidoDAO {
    @Override
    public void create(Pedido pedido) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        ResultSet rsGenKeys = null;

        try {
            conn = connectDB();

            ps = conn.prepareStatement("INSERT INTO pedido (total, fecha, id_cliente, id_comercial) VALUES (?, ? , ?, ?)", Statement.RETURN_GENERATED_KEYS);

            int idx = 1;
            ps.setDouble(idx++, pedido.getTotal());
            ps.setDate(idx++, pedido.getFecha());
            ps.setInt(idx++, pedido.getId_cliente());
            ps.setInt(idx, pedido.getId_comercial());

            int rows = ps.executeUpdate();
            if (rows == 0)
                System.out.println("INSERT de pedido con 0 filas insertadas.");

            rsGenKeys = ps.getGeneratedKeys();
            if (rsGenKeys.next())
                pedido.setId(rsGenKeys.getInt(1));

        } catch (SQLException | ClassNotFoundException  e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, ps, rs);
        }
    }

    @Override
    public List<Pedido> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Pedido> listaPedido = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM pedido");
            while (rs.next()) {
                Pedido pedido = new Pedido();

                pedido.setId(rs.getInt("id"));
                pedido.setTotal(rs.getDouble("total"));
                pedido.setFecha(rs.getDate("fecha"));
                pedido.setId_cliente(rs.getInt("id_cliente"));
                pedido.setId_comercial(rs.getInt("id_comercial"));
                listaPedido.add(pedido);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaPedido;
    }
}
