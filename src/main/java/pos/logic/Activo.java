package pos.logic;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlID;
import jakarta.xml.bind.annotation.XmlIDREF;

import java.util.Objects;

@XmlAccessorType(XmlAccessType.FIELD)
public class Activo {
    @XmlID
    String codigo;
    String nombre;
    int fabricacion;    //anio
    double valorInicial;
    @XmlIDREF //guarda la referencia en lugar de un objeto nuevo, porque ya existe
    Categoria categoria;

    public Activo(){
        this("","",0,0.0, new Categoria());
    }

    public Activo(String codigo, String nombre, int fabricacion, double valorInicial, Categoria categoria) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.fabricacion = fabricacion;
        this.valorInicial = valorInicial;
        this.categoria = categoria;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getFabricacion() {
        return fabricacion;
    }

    public void setFabricacion(int fabricacion) {
        this.fabricacion = fabricacion;
    }

    public double getValorInicial() {
        return valorInicial;
    }

    public void setValorInicial(double valorInicial) {
        this.valorInicial = valorInicial;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }
    public void setCategoria(String codigo) {
        if(categoria == null)
            categoria = new Categoria();
        this.categoria.setNombre(codigo);
    }
}
