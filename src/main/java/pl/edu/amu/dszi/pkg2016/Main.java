package pl.edu.amu.dszi.pkg2016;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import pl.edu.amu.dszi.ai.DecisionTreeEvaluator;
import pl.edu.amu.dszi.logic.MainFuzzyLogicServiceHandler;
import pl.edu.amu.dszi.model.FertilizationOrIrrigationDecision;
import pl.edu.amu.dszi.model.Field;
import pl.edu.amu.dszi.model.weather.SunType;
import pl.edu.amu.dszi.model.weather.Weather;

import java.io.IOException;

import static pl.edu.amu.dszi.model.weather.RainType.LIGHT_RAIN;
import static pl.edu.amu.dszi.model.weather.RainType.NO_RAIN;
import static pl.edu.amu.dszi.model.weather.SunType.*;

/**
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
//this is test case!!!
public class Main {

    public static void main(String[] args) throws IOException, InterruptedException {

        MainFuzzyLogicServiceHandler mainFuzzyLogicServiceHandler = new MainFuzzyLogicServiceHandler();
        Thread mainFuzzyLogicThread = new Thread(mainFuzzyLogicServiceHandler);
        mainFuzzyLogicThread.start();
        Thread.sleep(300);

        try {
            DecisionTreeEvaluator dte = new DecisionTreeEvaluator();
            System.out.println("Current weather: NO_SUN, NO_RAIN");
            String toFormat = "irr: %.3f soil: %.3f pr: %.3f, NO_SUN, NO_RAIN, fertDec: %s, irrDec: %s\n";
            for(Field f :mainFuzzyLogicServiceHandler.fields) {
                FertilizationOrIrrigationDecision fertDec = dte.testClassifyFert(f.getPriority(), f.getSoilRichness(), f.getIrrigation());
                FertilizationOrIrrigationDecision irrDec = dte.classifyIrrigation(f.getIrrigation(), HOT_SUN, NO_RAIN);
                System.out.printf(toFormat, f.getIrrigation(), f.getSoilRichness(), f.getPriority(), fertDec, irrDec);
//                System.out.println("evaluated: " + fertDec);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
//        String toFormat = "%.3f,%s,%s,?\n";
//        Random r = new Random();
//        for(int i = 0; i<30; i++)
//        System.out.printf(toFormat, r.nextDouble() * 100, anyEnumValue(Weather.SunType.class), anyEnumValue(Weather.RainType.class));
//        weather observer test
//        WeatherObserver wo = new WeatherObserver();
//        WeatherHandler wh = WeatherHandler.getInstance();
//        wh.addObserver(wo);
//        Thread weatherThread = new Thread(wh);
//        weatherThread.start();
    }

}

