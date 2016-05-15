package pl.edu.amu.dszi.ai;
import pl.edu.amu.dszi.model.FertilizationOrIrrigationDecision;
import pl.edu.amu.dszi.model.weather.RainType;
import pl.edu.amu.dszi.model.weather.SunType;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

import java.util.ArrayList;

/**
 * Created by lupus on 15.05.16.
 */
public class DecisionTreeEvaluator {
    String fileFert = "res/test_fert.arff";
    String fileIrr = "res/test.arff";
    Classifier irrTree;
    Classifier fertTree;
    Instances fertilizationDesicionTrainSet;
    Instances irrigationDecisionTrainSet;
    public DecisionTreeEvaluator() throws Exception {

        DataSource fertilizeDataSource = new DataSource(fileFert);
        DataSource irrigationDataSource = new DataSource(fileIrr);
        fertilizationDesicionTrainSet = fertilizeDataSource.getDataSet();
        irrigationDecisionTrainSet = irrigationDataSource.getDataSet();
        fertilizationDesicionTrainSet.setClassIndex(3);
        irrigationDecisionTrainSet.setClassIndex(3);
        fertTree = new LMT();
        irrTree = new J48();
        fertTree.buildClassifier(fertilizationDesicionTrainSet);
        irrTree.buildClassifier(irrigationDecisionTrainSet);

    }
    public FertilizationOrIrrigationDecision testClassifyFert(double p, double r, double i) throws Exception{
        Instance currentInstance = new DenseInstance(4);
        currentInstance.setValue(0, p);
        currentInstance.setValue(1, r);
        currentInstance.setValue(2, i);
        currentInstance.setMissing(3);
        currentInstance.setDataset(fertilizationDesicionTrainSet);
        double toReturn = fertTree.classifyInstance(currentInstance);
        return FertilizationOrIrrigationDecision.getEnumFromInt((int) toReturn);
    }

    public FertilizationOrIrrigationDecision classifyIrrigation(double i, SunType sun, RainType rain) throws Exception {
        Instance toTest = new DenseInstance(4);
        toTest.setValue(0, i);
        toTest.setValue(1, (double) sun.getValue());
        toTest.setValue(2, (double) rain.getValue());
        toTest.setMissing(3);
        toTest.setDataset(irrigationDecisionTrainSet);
        double toReturn = irrTree.classifyInstance(toTest);
        return FertilizationOrIrrigationDecision.getEnumFromInt((int) toReturn);
    }

}