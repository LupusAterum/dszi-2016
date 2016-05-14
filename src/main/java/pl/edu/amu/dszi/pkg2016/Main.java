/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pl.edu.amu.dszi.pkg2016;

import pl.edu.amu.dszi.logic.MainFuzzyLogicServiceHandler;
import pl.edu.amu.dszi.logic.WeatherHandler;

import java.io.IOException;

/**
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
//this is test case!!!
public class Main {

    public static void main(String[] args) throws IOException {

        MainFuzzyLogicServiceHandler mainFuzzyLogicServiceHandler = new MainFuzzyLogicServiceHandler();
        Thread mainFuzzyLogicThread = new Thread(mainFuzzyLogicServiceHandler);
        mainFuzzyLogicThread.start();
//        weather observer test
//        WeatherObserver wo = new WeatherObserver();
//        WeatherHandler wh = WeatherHandler.getInstance();
//        wh.addObserver(wo);
//        Thread weatherThread = new Thread(wh);
//        weatherThread.start();
    }

}

