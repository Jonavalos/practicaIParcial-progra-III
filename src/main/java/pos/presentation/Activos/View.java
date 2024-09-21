package pos.presentation.Activos;

import pos.Application;
import pos.logic.Activo;
import pos.logic.Categoria;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

import static pos.presentation.Activos.Model.CATEGORIAS;

public class View implements PropertyChangeListener {
    private JLabel codigoLbl;
    private JTextField codigo;
    private JTextField activo;
    private JLabel activoLbl;
    private JTextField fabricacion;
    private JLabel fabricacionLbl;
    private JLabel valorLbl;
    private JTextField valor;
    private JComboBox categoriaCB;
    private JLabel categoriaLbl;
    private JButton consultarButton;
    private JButton limpiarButton;
    private JTextField edad;
    private JTextField depreciacion;
    private JLabel depreciacionLbl;
    private JTextField valorActual;
    private JLabel valorActualLbl;
    private JLabel edadLbl;
    private JButton guardarButton;
    private JPanel panelActivos;

    public JPanel getPanel() {
        return panelActivos;
    }

    public View() {
        consultarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Activo activo = new Activo();
                    activo.setCodigo(codigo.getText());
                    controller.search(activo);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
        limpiarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.clear();
            }
        });
        guardarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    controller.save(take());
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage(), "Información", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    // MVC
    private pos.presentation.Activos.Model model;
    private pos.presentation.Activos.Controller controller;

    public void setModel(Model model) {
        this.model = model;
        model.addPropertyChangeListener(this);
    }

    public void setController(Controller controller) {
        this.controller = controller;
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        switch (evt.getPropertyName()){
            case Model.CURRENT:
                codigo.setText(model.getCurrent().getCodigo());
                activo.setText(model.getCurrent().getNombre());
                fabricacion.setText("" + model.getCurrent().getFabricacion());
                valor.setText("" + model.getCurrent().getValorInicial());
                categoriaCB.setSelectedItem(model.getCurrent().getCategoria());
                edad.setText("" + 10);
                depreciacion.setText("" + 10);
                valorActual.setText("" + 10);

                Integer restaEdad = 2024 - Integer.parseInt(fabricacion.getText());
                edad.setText("" + restaEdad);

                if(Integer.parseInt(fabricacion.getText())==0)
                    edad.setText("0");

                Double restaDepreciacion = depreciacionSegunCategoria()*Double.parseDouble(valor.getText())*restaEdad;
                depreciacion.setText("" + restaDepreciacion);
                Double restaValorActual = Double.parseDouble(valor.getText()) - restaDepreciacion;

                valorActual.setText("" + restaValorActual);

                edad.setEnabled(false);
                depreciacion.setEnabled(false);
                valorActual.setEnabled(false);

                if (model.getMode() == Application.MODE_EDIT) {
                    codigo.setEnabled(false);
                    //delete.setEnabled(true);
                } else {
                    codigo.setEnabled(true);
                    //delete.setEnabled(false);
                }

            case Model.FILTER:
                codigo.setText(model.getFilter().getCodigo());
                break;

            case Model.CATEGORIAS:
                categoriaCB.setModel(model.getCategorias());

        }
    }
    public Activo take() throws Exception{
        Activo e = new Activo();
        e.setCodigo(codigo.getText());
        e.setNombre(activo.getText());
        if(Double.parseDouble(fabricacion.getText()) <= 0){
            throw new Exception("Se ha digitado una cantidad invalida para fabricacion");
        }
        e.setFabricacion(Integer.parseInt(fabricacion.getText()));
        e.setValorInicial(Double.parseDouble(valor.getText()));
        e.setCategoria((Categoria)categoriaCB.getSelectedItem() );
        return e;
    }

    public double depreciacionSegunCategoria() {
        if(categoriaCB.getSelectedIndex() == 0){return 0.0;}
        switch (categoriaCB.getSelectedItem().toString()){
            case "computadora (5 anios)":
                return 0.2;

            case "vehiculo (10 anios)":
                return 0.1;

            case "casa (20 anios)":
                return 0.05;
        }
            return 0;
    }

    public Categoria convertirCategoria(){  //cod nom vida
        switch (categoriaCB.getSelectedItem().toString()){
            case "computadora (5 anios)":
                return new Categoria("COM", "computadora", 5);

            case "vehiculo (10 anios)":
                return new Categoria("VEH", "vehiculo", 10);

            case "casa (20 anios)":
                return new Categoria("CA", "casa", 20);
        }
        return new Categoria();
    }


}
