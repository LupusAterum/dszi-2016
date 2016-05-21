package pl.edu.amu.dszi.pkg2016;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pl.edu.amu.dszi.logic.ai.MainFuzzyLogicServiceHandler;
import pl.edu.amu.dszi.model.FieldValueChanger;
import pl.edu.amu.dszi.model.Tractor;
import pl.edu.amu.dszi.model.weather.WeatherChanger;
import pl.edu.amu.dszi.view.DiagnosticWindow;

/**
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
//this is test case!!!
public class Main {
    public static boolean DEBUG = false;
    public static void main(String[] args) throws Exception {
        WeatherChanger.getInstance().addObserver(Tractor.getInstance());
        Thread weatherChangerThread = new Thread(WeatherChanger.getInstance());
        weatherChangerThread.start();
        MainFuzzyLogicServiceHandler mainFuzzyLogicServiceHandler = new MainFuzzyLogicServiceHandler();
        Thread mainFuzzyLogicThread = new Thread(mainFuzzyLogicServiceHandler);
        mainFuzzyLogicThread.start();
        DiagnosticWindow.main(args);
        Thread fieldDegradeThread = new Thread(FieldValueChanger.getInstance());
        fieldDegradeThread.start();

    }

}

