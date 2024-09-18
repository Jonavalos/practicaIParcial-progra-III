package pos.data;

import pos.logic.*;
import jakarta.xml.bind.annotation.*;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Data {

    @XmlElementWrapper(name = "activos")
    @XmlElement(name = "activo")
    private List<Activo> activos;

    @XmlElementWrapper(name = "categorias")
    @XmlElement(name = "categoria")
    private List<Categoria> categorias;

    public Data() {
        activos = new ArrayList<>();
        categorias = new ArrayList<>();
    }

    public List<Activo> getActivos() { return activos; }

    public List<Categoria> getCategorias() { return categorias; }


}