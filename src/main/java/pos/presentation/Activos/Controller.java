package pos.presentation.Activos;


import pos.Application;
import pos.logic.Activo;
import pos.logic.Categoria;
import pos.logic.Service;
import pos.presentation.Activos.View;

import javax.swing.*;
import java.util.Objects;


public class Controller {
    View view;
    Model model;

    public Controller(View view, Model model) {
        model.init(Service.instance().search(new Activo()));
        this.view = view;
        this.model = model;
        view.setController(this);
        view.setModel(model);
        model.setCategorias(Service.instance().search(new Categoria()));
    }
    public void search(Activo filter) throws  Exception{
        try{
            model.setFilter(filter);
            if((Service.instance().read(filter)!=null))
                model.setMode(Application.MODE_EDIT);
            else{
                model.setMode(Application.MODE_CREATE);
            }
            model.setCurrent(Service.instance().read(filter)); //re setear el current
        }catch (Exception ex){
            JOptionPane.showMessageDialog(null, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    public void save(Activo e) throws  Exception{
        switch (model.getMode()) {
            case Application.MODE_CREATE:
                Service.instance().create(e);
                break;
            case Application.MODE_EDIT:
                Service.instance().update(e);
                break;
        }
        model.setFilter(new Activo());
        model.setMode(Application.MODE_CREATE);
        //search(model.getFilter()); //error aqui
        model.setCurrent(new Activo());
    }
    public void edit(int row){
        Activo e = model.getCurrent();
        try {
            model.setMode(Application.MODE_EDIT);
            model.setCurrent(Service.instance().read(e));
        } catch (Exception ex) {}
    }

    public void delete() throws Exception {
        Service.instance().delete(model.getCurrent());
        search(model.getFilter());
    }

    public void clear() {
        model.setMode(Application.MODE_CREATE);
        model.setCurrent(new Activo());
    }
    

}
