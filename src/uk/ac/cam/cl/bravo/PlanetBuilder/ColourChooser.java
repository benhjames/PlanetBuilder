package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ColourChooser extends JDialog {

    public enum ColourableItems {GROUND, WATER};

    public ColourChooser(JFrame parent, String title, ColourableItems item) {
        super(parent, title, true); //True sets modal
        setSize(600, 600);
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        GroupLayout.Group horiz = gl.createParallelGroup();
        GroupLayout.Group vert = gl.createSequentialGroup();
        gl.setHorizontalGroup(horiz);
        gl.setVerticalGroup(vert);

        JLabel startLabel = new JLabel("Start Colour");
        horiz.addComponent(startLabel);
        vert.addComponent(startLabel);

        final JColorChooser startCol = new JColorChooser();
        startCol.setPreviewPanel(new JPanel());
        switch (item) {
            case GROUND:
                startCol.setColor(WorldOptions.getInstance().getGroundStart());
                startCol.getSelectionModel().addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent changeEvent) {
                        WorldOptions.getInstance().setGroundStart(startCol.getColor());
                    }
                });
                break;
            case WATER:
                startCol.setColor(WorldOptions.getInstance().getWaterStart());
                startCol.getSelectionModel().addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent changeEvent) {
                        WorldOptions.getInstance().setWaterStart(startCol.getColor());
                    }
                });
                break;
        }
        horiz.addComponent(startCol);
        vert.addComponent(startCol);

        JSeparator colourSep = new JSeparator();
        horiz.addComponent(colourSep);
        vert.addComponent(colourSep);

        JLabel endLabel = new JLabel("End Colour");
        horiz.addComponent(endLabel);
        vert.addComponent(endLabel);

        final JColorChooser endCol = new JColorChooser();
        endCol.setPreviewPanel(new JPanel());
        switch (item) {
            case GROUND:
                endCol.setColor(WorldOptions.getInstance().getGroundEnd());
                endCol.getSelectionModel().addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent changeEvent) {
                        WorldOptions.getInstance().setGroundEnd(endCol.getColor());
                    }
                });
                break;
            case WATER:
                endCol.setColor(WorldOptions.getInstance().getWaterEnd());
                endCol.getSelectionModel().addChangeListener(new ChangeListener() {
                    @Override
                    public void stateChanged(ChangeEvent changeEvent) {
                        WorldOptions.getInstance().setWaterEnd(endCol.getColor());
                    }
                });
                break;
        }
        horiz.addComponent(endCol);
        vert.addComponent(endCol);
    }
}
