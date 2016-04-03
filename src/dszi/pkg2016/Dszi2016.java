/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dszi.pkg2016;

import dszi.abstractClasses.FieldPriorityHandler;
import dszi.logic.Field;
import dszi.logic.Location;
import dszi.logic.TractorFPH;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Karol Mazurek <kmazurek93@gmail.com>
 */
//this is test case!!!
public class Dszi2016 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        FieldPriorityHandler handler = new TractorFPH("res/fieldHandling.fcl", true, 5, 5);
        Random r = new Random();
        ArrayList<Field> fields = new ArrayList<>();
        for(int x=1; x<6; x++) {
            for (int y=1; y<6; y++) {
                
                Double soil = r.nextDouble() * 100d;
                Double irr = r.nextDouble() * 100d;
                Field f = new Field(irr, soil, x, y);
                fields.add(f);
            }
        }
        Location tractorLocation = new Location(3, 2);
        System.out.println("Maximum distance: 8");
        for(Field f : fields) {
            String toFormat = "Location x: %d, y: %d | irrigation: %.3f | soilRichness: %.3f | ManhattanDistance: %d | Priority: %.3f\n";
            Double priority = handler.getFieldPriority(f, tractorLocation);
            System.out.printf(toFormat, 
                    f.getLocation().getX(), f.getLocation().getY(), 
                    f.getIrrigation(), f.getSoilRichness(), 
                    f.getLocation().getManhattanDistanceTo(tractorLocation), 
                    priority);
        }
    }
    
}
