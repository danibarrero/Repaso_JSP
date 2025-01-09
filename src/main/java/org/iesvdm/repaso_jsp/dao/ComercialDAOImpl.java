package org.iesvdm.repaso_jsp.dao;

import org.iesvdm.repaso_jsp.model.Comercial;
import org.iesvdm.repaso_jsp.model.Pedido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ComercialDAOImpl extends AbstractDAOImpl implements ComercialDAO {
    @Override
    public List<Comercial> getAll() {
        Connection conn = null;
        Statement s = null;
        ResultSet rs = null;

        List<Comercial> listaPedido = new ArrayList<>();

        try {
            conn = connectDB();

            // Se utiliza un objeto Statement dado que no hay parámetros en la consulta.
            s = conn.createStatement();

            rs = s.executeQuery("SELECT * FROM comercial");
            while (rs.next()) {
                Comercial comercial = new Comercial();
                comercial.setId(rs.getInt("id"));
                comercial.setNombre(rs.getString("nombre"));
                comercial.setApellido1(rs.getString("apellido1"));
                comercial.setApellido2(rs.getString("apellido2"));
                comercial.setComision(rs.getDouble("comision"));
                listaPedido.add(comercial);
            }

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            closeDb(conn, s, rs);
        }
        return listaPedido;
    }

}
