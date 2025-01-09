package org.iesvdm.repaso_jsp.dao;

import org.iesvdm.repaso_jsp.model.Cliente;

import java.util.List;
import java.util.Optional;

public interface ClienteDAO {
    public List<Cliente> getAll();
    public Optional<Cliente> find(int id);
    public void update(Cliente cliente);
}
