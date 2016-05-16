package pl.edu.amu.dszi.logic.ai;

import pl.edu.amu.dszi.model.LevelDecision;
import pl.edu.amu.dszi.model.weather.RainType;
import pl.edu.amu.dszi.model.weather.SunType;
import weka.classifiers.Classifier;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.LMT;
import weka.core.*;
import weka.core.converters.ConverterUtils.DataSource;

/**
 * Created by lupus on 15.05.16.
 */
public class DecisionEvaluator {
    private String fileFert = "res/test_fert.arff";
    private String fileIrr = "res/test.arff";
    private Classifier irrigationDecisionTree;
    private Classifier fertilizationDecisionTree;
    private Instances fertilizationDecisionTrainSet;
    private Instances irrigationDecisionTrainSet;

    public DecisionEvaluator() throws Exception {

        DataSource fertilizeDataSource = new DataSource(fileFert);
        DataSource irrigationDataSource = new DataSource(fileIrr);
        fertilizationDecisionTrainSet = fertilizeDataSource.getDataSet();
        irrigationDecisionTrainSet = irrigationDataSource.getDataSet();
        fertilizationDecisionTrainSet.setClassIndex(3);
        irrigationDecisionTrainSet.setClassIndex(3);
        fertilizationDecisionTree = new LMT();
        irrigationDecisionTree = new J48();
        fertilizationDecisionTree.buildClassifier(fertilizationDecisionTrainSet);
        irrigationDecisionTree.buildClassifier(irrigationDecisionTrainSet);

    }

    public LevelDecision calassifyFertilization(double priority, double soilRichness,
                                                double irrigation) throws Exception {
        Instance currentInstance = new DenseInstance(4);
        currentInstance.setValue(0, priority);
        currentInstance.setValue(1, soilRichness);
        currentInstance.setValue(2, irrigation);
        currentInstance.setMissing(3);
        currentInstance.setDataset(fertilizationDecisionTrainSet);
        double toReturn = fertilizationDecisionTree.classifyInstance(currentInstance);
        return LevelDecision.getEnumFromInt((int) toReturn);
    }

    public LevelDecision classifyIrrigation(double irrigation, SunType sunType,
                                            RainType rainType) throws Exception {
        Instance toTest = new DenseInstance(4);
        toTest.setValue(0, irrigation);
        toTest.setValue(1, (double) sunType.getValue());
        toTest.setValue(2, (double) rainType.getValue());
        toTest.setMissing(3);
        toTest.setDataset(irrigationDecisionTrainSet);
        double toReturn = irrigationDecisionTree.classifyInstance(toTest);
        return LevelDecision.getEnumFromInt((int) toReturn);
    }

}