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
    public static boolean optymalizeSwitch = false;
    //optymalimaze params
    public static double roundNumber;
    public static double actualAverageResult;
    public static double bestAverageResult;
    
    public static double actualIrrigationWeight;
    public static double actualSoilRichnessWeight;
    public static double actualDistanceWeight;
    public static double bestIrrigationWeight;
    public static double bestSoilRichnessWeitght;
    public static double bestDistanceWeight;
    //end
    public static void main(String[] args) throws Exception {
    	if(!optymalizeSwitch){
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
    	else{
            actualIrrigationWeight = 1;
            actualSoilRichnessWeight = 1;
            actualDistanceWeight = 1;
            roundNumber=0;
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

}

