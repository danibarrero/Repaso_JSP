package org.iesvdm.repaso_jsp.dao;

import org.iesvdm.repaso_jsp.model.Comercial;
import org.iesvdm.repaso_jsp.model.Pedido;

import java.util.List;

public interface ComercialDAO {
    public List<Comercial> getAll();
}
