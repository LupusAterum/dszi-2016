package pl.edu.amu.dszi.view;

import pl.edu.amu.dszi.model.Field;
import pl.edu.amu.dszi.model.GrassType;
import pl.edu.amu.dszi.logic.Location;
import pl.edu.amu.dszi.model.Tractor;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * Created by softra43 on 20.04.2016.
 */
public class Panel extends JPanel {

    private static final Integer tileSize = new Integer(80);
    private static BufferedImage okGrass;
    private static BufferedImage neglectedGrass;
    private static BufferedImage urgentAttentionGrass;
    private static BufferedImage toTotalReclamation;
    private static BufferedImage tractorImage;

    private List<Field> fields;

    private Tractor tractor;


    public Panel() {
        super();
        loadImages();
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        for (Field field : fields) {
            Location location = field.getLocation();
            if (GrassType.NEGLECTED_GRASS.inRange(field.getPriority().intValue())) {
                drawField(g, neglectedGrass, location);
            } else if (GrassType.URGENT_ATTENTION_GRASS.inRange(field.getPriority().intValue())) {
                drawField(g, urgentAttentionGrass, location);
            } else if (GrassType.OK_GRASS.inRange(field.getPriority().intValue())) {
                drawField(g, okGrass, location);
            } else if (GrassType.TO_TOTAL_RECLAMATION_GRASS.inRange(field.getPriority().intValue())) {
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

    public void initMap(List<Field> fieldList, Tractor tractor) {
        this.fields = fieldList;
        this.tractor = tractor;
    }

}