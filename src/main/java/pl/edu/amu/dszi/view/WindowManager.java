package pl.edu.amu.dszi.view;

import pl.edu.amu.dszi.model.FieldHandler;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.Tractor;
import pl.edu.amu.dszi.view.Panel;

import javax.swing.*;
import java.awt.*;
import java.util.List;

/**
 * Created by softra43 on 20.04.2016.
 */
public class WindowManager extends JFrame {

    Panel panel;

    public WindowManager() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1024, 768);
        setBackground(Color.WHITE);

        panel = new Panel();
        panel.setBackground(new Color(73, 207, 86));
        Container c = getContentPane();
        c.add(panel);
        DiagnosticWindow d = new DiagnosticWindow();


        setVisible(true);


//        Timer timer = new Timer();
//        DrawingTask dTask = new DrawingTask(dPanel);
//        timer.schedule(dTask, 0, 10);

    }

    public void initMap(Tractor tractor) {
        panel.initMap(tractor);
    }
}
