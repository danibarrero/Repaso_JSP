package org.iesvdm.repaso_jsp.dao;

import org.iesvdm.repaso_jsp.model.Pedido;

import java.util.List;
import java.util.Optional;

public interface PedidoDAO {
    public void create(Pedido pedido);
    public List<Pedido> getAll();
    public Optional<Pedido> find(int id);
}
