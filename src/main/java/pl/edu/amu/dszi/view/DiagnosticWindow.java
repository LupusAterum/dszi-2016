package pl.edu.amu.dszi.view;

import pl.edu.amu.dszi.model.LevelledDecision;
import pl.edu.amu.dszi.model.Tractor;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.model.weather.Weather;
import pl.edu.amu.dszi.model.weather.WeatherChanger;

import javax.swing.*;
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


    public DiagnosticWindow() {
        Tractor.getInstance().addObserver(this);
    }

    @Override
    public void update(Observable o, Object arg) {
        if(o instanceof WeatherChanger) {
            if(arg instanceof Weather) {
                weatherSun.setText(((Weather) arg).getSunType().toString());
                weatherRain.setText(((Weather) arg).getRain().toString());
            }
        } else if (o instanceof Tractor) {
            if(arg instanceof TreeMap) {
                TreeMap<String, LevelledDecision> decisionTreeMap = (TreeMap<String, LevelledDecision>) arg;
                irrigationDecision.setText(decisionTreeMap.get(Tractor.IRRIGATION).toString());
                fertilizationDecision.setText(decisionTreeMap.get(Tractor.FERTILIZATION).toString());
            } else if(arg instanceof Location) {
                currentLocation.setText("X: " + ((Location) arg).getX() + " Y: " + ((Location) arg).getY());
            } else if(arg instanceof Integer) {
                
            }
        }
    }
}
