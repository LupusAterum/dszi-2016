package pl.edu.amu.dszi.pkg2016;

/**
 * Created by exos on 16.05.2016.
 */
public class ParameterWeightOptymalizer {
    public static double getIrrigationWeight() {
        return irrigationWeight;
    }

    public static double getSoilRichnessWeitght() {
        return soilRichnessWeitght;
    }

    public static double getDistanceWeight() {
        return distanceWeight;
    }

    private static double irrigationWeight;
    private static double soilRichnessWeitght;
    private static double distanceWeight;

    public static double setParameters(double irr,double soil, double dis){
        double result = 0 ;//TODO getAverageResultAfter(irr,soil,dis);
        double bestIrr=irr, bestSoil=soil, bestDis=dis;
        double bestResult = 0;
        for(double ir=irr-0.01; ir<irr+0.01;ir=ir+0.01){
            for(double so=soil-0.01; so<soil+0.01;so=so+0.01){
                for(double di=dis-0.01; di<dis+0.01;di=di+0.01){
                    if(di==dis && so==soil && ir==irr){continue;}
                    if(bestResult<0/*TODO getAverageResultAfter(irr,soil,dis);*/){
                        bestResult = 0;/*TODO getAverageResultAfter(irr,soil,dis);*/
                        bestIrr=ir;bestSoil=so; bestDis=di;
                    }
                }
            }
        }
        if (bestResult<result){
            result=bestResult;
            return setParameters(bestIrr,bestSoil,bestDis);
        }
        else{
            irrigationWeight=irr;
            soilRichnessWeitght=soil;
            distanceWeight=dis;
            return result;
        }
    }



}
