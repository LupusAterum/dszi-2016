package pl.edu.amu.dszi.view;

import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;
import pl.edu.amu.dszi.model.FieldHandler;
import pl.edu.amu.dszi.model.LevelledDecision;
import pl.edu.amu.dszi.model.Tractor;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.model.weather.Weather;
import pl.edu.amu.dszi.model.weather.WeatherChanger;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

/**
 * Created by lupus on 20.05.16.
 */
public class DiagnosticWindow implements Observer {
    private JTextField currentLocation;
    private JTextField targetLocation;
    private JTextField currentIrrigation;
    private JTextField currentSoilRichness;
    private JTextField currentDistance;
    private JTextField currentPriority;
    private JTextField weatherSun;
    private JTextField weatherRain;
    private JTextField irrigationDecision;
    private JTextArea logTextArea;
    private JTextField fertilizationDecision;
    private JPanel mainPanel;
    private static DiagnosticWindow instance;

    public DiagnosticWindow() {
        Tractor.getInstance().addObserver(this);
        FieldHandler.getInstance().addObserver(this);
        WeatherChanger.getInstance().addObserver(this);
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("DiagnosticWindow");
        instance = new DiagnosticWindow();
        frame.setContentPane(instance.mainPanel);
        frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);

    }

    @Override
    public void update(Observable o, Object arg) {
        DefaultCaret caret = (DefaultCaret) logTextArea.getCaret();
        caret.setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
//        if (o instanceof WeatherChanger) {
//            if (arg instanceof Weather) {
//                weatherSun.setText(((Weather) arg).getSunType().toString());
//                weatherRain.setText(((Weather) arg).getRain().toString());
//                logTextArea.append("Weather changed to: " + weatherSun.getText() + ", " + weatherRain.getText() + "\n");
//
//            }
//        } else
        if (o instanceof Tractor) {
            if (arg instanceof String) {
                if (arg == Tractor.NEW_TARGET) {
                    Location l = Tractor.getInstance().getTargetLocation();
                    targetLocation.setText("X: " + l.getX() + " Y: " + l.getY());
                    logTextArea.append("TARGET> My new target is: " + targetLocation.getText() + "\n");
                } else if (arg == Tractor.IRRIGATION) {
                    irrigationDecision.setText(Tractor.getInstance().getDecisions()
                            .get(Tractor.IRRIGATION).toString());
                    logTextArea.append("DEC> Made irrigation decision: " + irrigationDecision.getText() + "\n");
                } else if (arg == Tractor.FERTILIZATION) {
                    fertilizationDecision.setText(Tractor.getInstance().getDecisions()
                            .get(Tractor.FERTILIZATION).toString());
                    logTextArea.append("DEC> Made fertilization decision: " + fertilizationDecision.getText() + "\n");
                }

            } else if (arg instanceof Location) {
                currentLocation.setText("X: " + ((Location) arg).getX() + " Y: " + ((Location) arg).getY());
                Field f = FieldHandler.getInstance().getFieldAt((Location) arg);
                currentPriority.setText(f.getPriority().toString());
                currentDistance.setText(f.getLocation().
                        getManhattanDistanceTo(Tractor.getInstance().getLocation()).toString());
                currentIrrigation.setText(f.getIrrigation().toString());
                currentSoilRichness.setText(f.getSoilRichness().toString());
                logTextArea.append("MOVE> I'm at " + currentLocation.getText() + "\n");

            } else if (arg instanceof Weather) {
                weatherSun.setText(((Weather) arg).getSunType().toString());
                weatherRain.setText(((Weather) arg).getRain().toString());
                logTextArea.append("WEATH> Weather changed to: " + weatherSun.getText() + ", " + weatherRain.getText() + "\n");
            }
        } else if (o instanceof FieldHandler) {
            if (arg instanceof TreeMap) {
                TreeMap<Location, Field> fieldMap = (TreeMap<Location, Field>) arg;
                Field f = fieldMap.get(Tractor.getInstance().getLocation());
                currentPriority.setText(f.getPriority().toString());
                currentDistance.setText(f.getLocation().
                        getManhattanDistanceTo(Tractor.getInstance().getLocation()).toString());
                currentIrrigation.setText(f.getIrrigation().toString());
                currentSoilRichness.setText(f.getSoilRichness().toString());
                logTextArea.append("VAR> Field parameters changed\n");
            }
        }
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayoutManager(16, 7, new Insets(0, 0, 0, 0), -1, -1));
        mainPanel.setMinimumSize(new Dimension(800, 600));
        mainPanel.setPreferredSize(new Dimension(800, 600));
        final JLabel label1 = new JLabel();
        label1.setFont(new Font(label1.getFont().getName(), Font.BOLD, 18));
        label1.setText("current Position:");
        mainPanel.add(label1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label2 = new JLabel();
        label2.setFont(new Font(label2.getFont().getName(), label2.getFont().getStyle(), 14));
        label2.setText("Irrigation:");
        mainPanel.add(label2, new GridConstraints(9, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label3 = new JLabel();
        label3.setFont(new Font(label3.getFont().getName(), label3.getFont().getStyle(), 14));
        label3.setText("Soil richness:");
        mainPanel.add(label3, new GridConstraints(10, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label4 = new JLabel();
        label4.setFont(new Font(label4.getFont().getName(), label4.getFont().getStyle(), 14));
        label4.setText("Distance:");
        mainPanel.add(label4, new GridConstraints(11, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label5 = new JLabel();
        label5.setFont(new Font(label5.getFont().getName(), Font.BOLD, 18));
        label5.setText("Decision:");
        mainPanel.add(label5, new GridConstraints(13, 0, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label6 = new JLabel();
        label6.setFont(new Font(label6.getFont().getName(), label6.getFont().getStyle(), 14));
        label6.setText("Priority:");
        mainPanel.add(label6, new GridConstraints(12, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label7 = new JLabel();
        label7.setFont(new Font(label7.getFont().getName(), Font.BOLD, 18));
        label7.setText("target:");
        mainPanel.add(label7, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentLocation = new JTextField();
        currentLocation.setEditable(false);
        currentLocation.setEnabled(true);
        currentLocation.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        mainPanel.add(currentLocation, new GridConstraints(0, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        targetLocation = new JTextField();
        targetLocation.setEditable(false);
        targetLocation.setEnabled(true);
        targetLocation.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        mainPanel.add(targetLocation, new GridConstraints(1, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label8 = new JLabel();
        label8.setText("Irrigate:");
        mainPanel.add(label8, new GridConstraints(13, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label9 = new JLabel();
        label9.setText("Fertilize:");
        mainPanel.add(label9, new GridConstraints(14, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        currentIrrigation = new JTextField();
        currentIrrigation.setEditable(false);
        currentIrrigation.setEnabled(true);
        currentIrrigation.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        mainPanel.add(currentIrrigation, new GridConstraints(9, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        currentSoilRichness = new JTextField();
        currentSoilRichness.setEditable(false);
        currentSoilRichness.setEnabled(true);
        currentSoilRichness.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        mainPanel.add(currentSoilRichness, new GridConstraints(10, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        currentDistance = new JTextField();
        currentDistance.setEditable(false);
        currentDistance.setEnabled(true);
        currentDistance.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        currentDistance.setText("");
        mainPanel.add(currentDistance, new GridConstraints(11, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        currentPriority = new JTextField();
        currentPriority.setEditable(false);
        currentPriority.setEnabled(true);
        currentPriority.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        mainPanel.add(currentPriority, new GridConstraints(12, 1, 1, 2, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JLabel label10 = new JLabel();
        label10.setFont(new Font(label10.getFont().getName(), Font.BOLD, 18));
        label10.setText("current Field parameters:");
        mainPanel.add(label10, new GridConstraints(2, 0, 7, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label11 = new JLabel();
        label11.setFont(new Font(label11.getFont().getName(), Font.BOLD, 18));
        label11.setText("Weather");
        mainPanel.add(label11, new GridConstraints(0, 4, 2, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JLabel label12 = new JLabel();
        label12.setFont(new Font(label12.getFont().getName(), label12.getFont().getStyle(), 14));
        label12.setText("SUN:");
        mainPanel.add(label12, new GridConstraints(0, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(30, 39), null, 0, false));
        final JLabel label13 = new JLabel();
        label13.setFont(new Font(label13.getFont().getName(), label13.getFont().getStyle(), 14));
        label13.setText("RAIN:");
        mainPanel.add(label13, new GridConstraints(1, 5, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(32, 10), null, 0, false));
        weatherSun = new JTextField();
        weatherSun.setEditable(false);
        weatherSun.setEnabled(true);
        weatherSun.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        weatherSun.setText("");
        mainPanel.add(weatherSun, new GridConstraints(0, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        weatherRain = new JTextField();
        weatherRain.setEditable(false);
        weatherRain.setEnabled(true);
        weatherRain.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        weatherRain.setText("");
        mainPanel.add(weatherRain, new GridConstraints(1, 6, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        irrigationDecision = new JTextField();
        irrigationDecision.setEditable(false);
        irrigationDecision.setEnabled(true);
        irrigationDecision.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        mainPanel.add(irrigationDecision, new GridConstraints(13, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(98, 25), null, 0, false));
        fertilizationDecision = new JTextField();
        fertilizationDecision.setEditable(false);
        fertilizationDecision.setEnabled(true);
        fertilizationDecision.setFont(new Font("Liberation Mono", Font.BOLD, 14));
        mainPanel.add(fertilizationDecision, new GridConstraints(14, 2, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(98, 25), null, 0, false));
        final JLabel label14 = new JLabel();
        label14.setFont(new Font(label14.getFont().getName(), Font.BOLD, 16));
        label14.setText("[LOG]");
        mainPanel.add(label14, new GridConstraints(2, 4, 7, 3, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final JScrollPane scrollPane1 = new JScrollPane();
        scrollPane1.setVerticalScrollBarPolicy(22);
        mainPanel.add(scrollPane1, new GridConstraints(9, 4, 7, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        logTextArea = new JTextArea();
        logTextArea.setEditable(false);
        scrollPane1.setViewportView(logTextArea);
        final Spacer spacer1 = new Spacer();
        mainPanel.add(spacer1, new GridConstraints(15, 0, 1, 3, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        final Spacer spacer2 = new Spacer();
        mainPanel.add(spacer2, new GridConstraints(0, 3, 16, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, 1, null, null, null, 0, false));
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return mainPanel;
    }
}
