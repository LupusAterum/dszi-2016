package pl.edu.amu.dszi.view;

import pl.edu.amu.dszi.model.FieldHandler;
import pl.edu.amu.dszi.model.field.Location;
import pl.edu.amu.dszi.model.field.Field;
import pl.edu.amu.dszi.model.field.GrassType;
import pl.edu.amu.dszi.model.Tractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.List;

/**
 * Created by softra43 on 20.04.2016.
 */
public class Panel extends JPanel implements Observer {

    private static final Integer tileSize = new Integer(80);
    private static BufferedImage okGrass;
    private static BufferedImage neglectedGrass;
    private static BufferedImage urgentAttentionGrass;
    private static BufferedImage toTotalReclamation;
    private static BufferedImage tractorImage;


    private Tractor tractor;


    public Panel() {
        super();
        loadImages();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        TreeMap<Location, Field> fieldMap = null;
        while(fieldMap == null) {
            fieldMap = FieldHandler.getInstance().getFields();
        }
        for (Map.Entry<Location, Field> entry : fieldMap.entrySet()) {
            Location location = entry.getKey();
            if (GrassType.NEGLECTED_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, neglectedGrass, location);
            } else if (GrassType.URGENT_ATTENTION_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, urgentAttentionGrass, location);
            } else if (GrassType.OK_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, okGrass, location);
            } else if (GrassType.TO_TOTAL_RECLAMATION_GRASS.inRange(entry.getValue().getPriority().intValue())) {
                drawField(g, toTotalReclamation, location);
            }
        }
        drawField(g, tractorImage, tractor.getLocation());

    }

    public void loadImages() {
        try {
            okGrass = ImageIO.read(new File("res/trawa1.png"));
            neglectedGrass = ImageIO.read(new File("res/trawa2.png"));
            urgentAttentionGrass = ImageIO.read(new File("res/trawa3.png"));
            toTotalReclamation = ImageIO.read(new File("res/trawa4.png"));
            tractorImage = ImageIO.read(new File("res/traktor1.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void drawField(Graphics g, BufferedImage image, Location location) {
        g.drawImage(
                image,
                tileSize * location.getX(),
                tileSize * location.getY(),
                null
        );
    }

    public void initMap(Tractor tractor) {
        this.tractor = tractor;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
