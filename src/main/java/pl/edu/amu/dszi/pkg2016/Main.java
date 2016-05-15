package pl.edu.amu.dszi.pkg2016;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import static net.java.quickcheck.generator.PrimitiveGeneratorSamples.*;

import pl.edu.amu.dszi.logic.MainFuzzyLogicServiceHandler;
import pl.edu.amu.dszi.logic.WeatherHandler;
import pl.edu.amu.dszi.model.Weather;

import java.io.IOException;
import java.util.Random;

/**
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
//this is test case!!!
public class Main {

    public static void main(String[] args) throws IOException {

        MainFuzzyLogicServiceHandler mainFuzzyLogicServiceHandler = new MainFuzzyLogicServiceHandler();
        Thread mainFuzzyLogicThread = new Thread(mainFuzzyLogicServiceHandler);
        mainFuzzyLogicThread.start();
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

