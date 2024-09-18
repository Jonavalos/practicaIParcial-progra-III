package pos.presentation.Activos;

import pos.Application;
import pos.logic.Activo;
import pos.presentation.AbstractModel;

import java.beans.PropertyChangeListener;
import java.util.List;

public class Model extends AbstractModel {

    Activo filter;
    Activo current;
    int mode;

    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        super.addPropertyChangeListener(listener);

        firePropertyChange(CURRENT);
        firePropertyChange(FILTER);
    }

    public Model() {
    }

    public void init(List<Activo> list) {
        this.current = new Activo(); //creacion de constructor default Activo
        this.filter = new Activo();
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
    public static final String CURRENT = "current";
    public static final String FILTER = "filter";
}
