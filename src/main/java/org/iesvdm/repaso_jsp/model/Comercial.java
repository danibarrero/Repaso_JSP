package org.iesvdm.repaso_jsp.model;

import java.util.Objects;

public class Comercial {
    private int id;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private double comision;

    public Comercial() {
    }

    public Comercial(int id, String nombre, String apellido1, String apellido2, double comision) {
        this.id = id;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.comision = comision;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public double getComision() {
        return comision;
    }

    public void setComision(double comision) {
        this.comision = comision;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Comercial comercial = (Comercial) o;
        return id == comercial.id && Double.compare(comision, comercial.comision) == 0 && Objects.equals(nombre, comercial.nombre) && Objects.equals(apellido1, comercial.apellido1) && Objects.equals(apellido2, comercial.apellido2);
    }

    @Override
    public String toString() {
        return "Comercial{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", comision=" + comision +
                '}';
    }
}
