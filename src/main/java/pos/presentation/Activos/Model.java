package pos.presentation.Activos;

import pos.Application;
import pos.logic.Activo;
import pos.logic.Categoria;
import pos.presentation.AbstractModel;

import javax.swing.*;
import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {

    private DefaultComboBoxModel<Categoria> categorias;
    Activo filter;
    Activo current;
    int mode;

    public DefaultComboBoxModel<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> cat) {
        for(Categoria c: cat){
            categorias.addElement(c);
        }
        firePropertyChange(CATEGORIAS);
    }

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);

        firePropertyChange(CURRENT);
        firePropertyChange(CATEGORIAS);
        firePropertyChange(FILTER);
    }

    public Model() {
    }

    public void init(List<Activo> list) {
        this.current = new Activo(); //creacion de constructor default Activo
        this.filter = new Activo();
        this.categorias = new DefaultComboBoxModel<Categoria>();
        this.mode = Application.MODE_CREATE;
    }


    public Activo getCurrent() {
        return current;
    }

    public void setCurrent(Activo current) {
        this.current = current;
        firePropertyChange(CURRENT);
    }

    public Activo getFilter() {
        return filter;
    }

    public void setFilter(Activo filter) {
        this.filter = filter;
        firePropertyChange(FILTER);
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }
    public static final String LIST = "list";
    public static final String CATEGORIAS = "categorias";
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
}
