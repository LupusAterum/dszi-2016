package dszi.pkg2016;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import dszi.model.Field;
import dszi.model.Tractor;
import dszi.view.Panel;
/**
 * Created by softra43 on 20.04.2016.
 */
public class WindowManager extends JFrame {

    Panel panel;

    public WindowManager() {
        super();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setBackground(Color.WHITE);

        panel = new Panel();
        panel.setBackground(new Color(73, 207, 86));
        Container c = getContentPane();
        c.add(panel);


        setVisible(true);


//        Timer timer = new Timer();
//        DrawingTask dTask = new DrawingTask(dPanel);
//        timer.schedule(dTask, 0, 10);

    }

    public void initMap(List<Field> fieldList, Tractor tractor) {
        panel.initMap(fieldList, tractor);
    }
}
