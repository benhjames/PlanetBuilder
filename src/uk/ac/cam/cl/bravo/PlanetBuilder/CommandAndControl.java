package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.swing.*;
import java.awt.*;

public class CommandAndControl extends JFrame {
    public CommandAndControl() {
        setTitle("Planet Builder - Controls");
        setSize(200, 768);
        drawInitialUI();
    }

    private void drawInitialUI() {
        Container pane = getContentPane();
        GroupLayout gl = new GroupLayout(pane);
        pane.setLayout(gl);
        GroupLayout.Group horiz = gl.createParallelGroup();
        GroupLayout.Group vert = gl.createSequentialGroup();
        gl.setHorizontalGroup(horiz);
        gl.setVerticalGroup(vert);

        JLabel detailLabel = new JLabel("Detail Level");
        horiz.addComponent(detailLabel);
        vert.addComponent(detailLabel);

        JSlider detailSlider = new JSlider(0, 100);
        horiz.addComponent(detailSlider);
        vert.addComponent(detailSlider);

        JLabel renderLabel = new JLabel("Render details?");
        horiz.addComponent(renderLabel);
        vert.addComponent(renderLabel);

        JCheckBox renderCheck = new JCheckBox();
        horiz.addComponent(renderCheck);
        vert.addComponent(renderCheck);

        JSeparator globalSep = new JSeparator();
        horiz.addComponent(globalSep);
        vert.addComponent(globalSep);

        JLabel terrainLabel = new JLabel("Terrain");
        horiz.addComponent(terrainLabel);
        vert.addComponent(terrainLabel);

        JSlider terrainSlider = new JSlider(0, 100);
        horiz.addComponent(terrainSlider);
        vert.addComponent(terrainSlider);

        JLabel vegetationLabel = new JLabel("Vegetation");
        horiz.addComponent(vegetationLabel);
        vert.addComponent(vegetationLabel);

        JSlider vegetationSlider = new JSlider(0, 100);
        horiz.addComponent(vegetationSlider);
        vert.addComponent(vegetationSlider);

        JLabel desertLabel = new JLabel("Desert");
        horiz.addComponent(desertLabel);
        vert.addComponent(desertLabel);

        JSlider desertSlider = new JSlider(0, 100);
        horiz.addComponent(desertSlider);
        vert.addComponent(desertSlider);

        JLabel iceLabel = new JLabel("Ice");
        horiz.addComponent(iceLabel);
        vert.addComponent(iceLabel);

        JSlider iceSlider = new JSlider(0, 100);
        horiz.addComponent(iceSlider);
        vert.addComponent(iceSlider);

        JLabel waterLabel = new JLabel("Water Level");
        horiz.addComponent(waterLabel);
        vert.addComponent(waterLabel);

        JSlider waterSlider = new JSlider(0, 100);
        horiz.addComponent(waterSlider);
        vert.addComponent(waterSlider);

        JLabel settlementLabel = new JLabel("Settlements");
        horiz.addComponent(settlementLabel);
        vert.addComponent(settlementLabel);

        JSlider settlementSlider = new JSlider(0, 100);
        horiz.addComponent(settlementSlider);
        vert.addComponent(settlementSlider);

        JLabel ringsLabel = new JLabel("Planet rings?");
        horiz.addComponent(ringsLabel);
        vert.addComponent(ringsLabel);

        JCheckBox ringsCheck = new JCheckBox();
        horiz.addComponent(ringsCheck);
        vert.addComponent(ringsCheck);

        JLabel inhabitedLabel = new JLabel("Inhabited?");
        horiz.addComponent(inhabitedLabel);
        vert.addComponent(inhabitedLabel);

        JCheckBox inhabitedCheck = new JCheckBox();
        horiz.addComponent(inhabitedCheck);
        vert.addComponent(inhabitedCheck);

        //Will open colour chooser in popup
        JButton groundColLabel = new JButton("Ground colours");
        horiz.addComponent(groundColLabel);
        vert.addComponent(groundColLabel);

        //Will open colour chooser in popup
        JButton waterColLabel = new JButton("Water colours");
        horiz.addComponent(waterColLabel);
        vert.addComponent(waterColLabel);

        JSeparator ioSep = new JSeparator();
        horiz.addComponent(ioSep);
        vert.addComponent(ioSep);

        JButton newWorld = new JButton("New world");
        horiz.addComponent(newWorld);
        vert.addComponent(newWorld);

        JButton saveWorld = new JButton("Save world");
        horiz.addComponent(saveWorld);
        vert.addComponent(saveWorld);

        JButton loadWorld = new JButton("Load world");
        horiz.addComponent(loadWorld);
        vert.addComponent(loadWorld);

    }
}
