package pos;

import pos.logic.Service;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Application {

    public static void main(String[] args) {

        System.out.println("Hello World");

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        }
        catch (Exception ex) {};

        window = new JFrame();
        JTabbedPane tabbedPane = new JTabbedPane();
        window.setContentPane(tabbedPane);

        window.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                super.windowClosing(e);
                Service.instance().stop();
            }
        });
        //Productos
        pos.presentation.Activos.Model activosModel = new pos.presentation.Activos.Model();
        pos.presentation.Activos.View activosView = new pos.presentation.Activos.View();
        activosController = new pos.presentation.Activos.Controller(activosView, activosModel);

        tabbedPane.addTab("Activos  ", activosView.getPanel());


        // Ventana
        window.setSize(1000,550);
        window.setResizable(false);
        window.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        window.setTitle("Activos");
        window.setVisible(true);
    }

    public static pos.presentation.Activos.Controller activosController;

    public static JFrame window;
    public final static int MODE_CREATE=1;
    public final static int MODE_EDIT=2;
    public static Border BORDER_ERROR = BorderFactory.createMatteBorder(0, 0, 2, 0, Color.RED);

}
