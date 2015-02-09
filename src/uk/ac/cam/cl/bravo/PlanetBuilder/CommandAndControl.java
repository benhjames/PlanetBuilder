package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.swing.*;
import java.awt.*;

public class CommandAndControl extends JFrame {

    //Declare controls
    private JLabel detailLabel;
    private JSlider detailSlider;
    private JLabel renderLabel;
    private JCheckBox renderCheck;
    private JSeparator globalSep;
    private JLabel terrainLabel;
    private JSlider terrainSlider;
    private JLabel vegetationLabel;
    private JSlider vegetationSlider;
    private JLabel desertLabel;
    private JSlider desertSlider;
    private JLabel iceLabel;
    private JSlider iceSlider;
    private JLabel waterLabel;
    private JSlider waterSlider;
    private JLabel settlementLabel;
    private JSlider settlementSlider;
    private JLabel ringsLabel;
    private JCheckBox ringsCheck;
    private JLabel inhabitedLabel;
    private JCheckBox inhabitedCheck;
    private JButton groundColLabel;
    private JButton waterColLabel;
    private JSeparator ioSep;
    private JButton newWorld;
    private JButton saveWorld;
    private JButton loadWorld;

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

        //In general, for each control, create it, add change listener, add to groups

        detailLabel = new JLabel("Detail Level");
        horiz.addComponent(detailLabel);
        vert.addComponent(detailLabel);

        detailSlider = new JSlider(0, 100);
        horiz.addComponent(detailSlider);
        vert.addComponent(detailSlider);

        renderLabel = new JLabel("Render details?");
        horiz.addComponent(renderLabel);
        vert.addComponent(renderLabel);

        renderCheck = new JCheckBox();
        horiz.addComponent(renderCheck);
        vert.addComponent(renderCheck);

        globalSep = new JSeparator();
        horiz.addComponent(globalSep);
        vert.addComponent(globalSep);

        terrainLabel = new JLabel("Terrain");
        horiz.addComponent(terrainLabel);
        vert.addComponent(terrainLabel);

        terrainSlider = new JSlider(0, 100);
        horiz.addComponent(terrainSlider);
        vert.addComponent(terrainSlider);

        vegetationLabel = new JLabel("Vegetation");
        horiz.addComponent(vegetationLabel);
        vert.addComponent(vegetationLabel);

        vegetationSlider = new JSlider(0, 100);
        horiz.addComponent(vegetationSlider);
        vert.addComponent(vegetationSlider);

        desertLabel = new JLabel("Desert");
        horiz.addComponent(desertLabel);
        vert.addComponent(desertLabel);

        desertSlider = new JSlider(0, 100);
        horiz.addComponent(desertSlider);
        vert.addComponent(desertSlider);

        iceLabel = new JLabel("Ice");
        horiz.addComponent(iceLabel);
        vert.addComponent(iceLabel);

        iceSlider = new JSlider(0, 100);
        horiz.addComponent(iceSlider);
        vert.addComponent(iceSlider);

        waterLabel = new JLabel("Water Level");
        horiz.addComponent(waterLabel);
        vert.addComponent(waterLabel);

        waterSlider = new JSlider(0, 100);
        horiz.addComponent(waterSlider);
        vert.addComponent(waterSlider);

        settlementLabel = new JLabel("Settlements");
        horiz.addComponent(settlementLabel);
        vert.addComponent(settlementLabel);

        settlementSlider = new JSlider(0, 100);
        horiz.addComponent(settlementSlider);
        vert.addComponent(settlementSlider);

        ringsLabel = new JLabel("Planet rings?");
        horiz.addComponent(ringsLabel);
        vert.addComponent(ringsLabel);

        ringsCheck = new JCheckBox();
        horiz.addComponent(ringsCheck);
        vert.addComponent(ringsCheck);

        inhabitedLabel = new JLabel("Inhabited?");
        horiz.addComponent(inhabitedLabel);
        vert.addComponent(inhabitedLabel);

        inhabitedCheck = new JCheckBox();
        horiz.addComponent(inhabitedCheck);
        vert.addComponent(inhabitedCheck);

        //Will open colour chooser in popup
        groundColLabel = new JButton("Ground colours");
        horiz.addComponent(groundColLabel);
        vert.addComponent(groundColLabel);

        //Will open colour chooser in popup
        waterColLabel = new JButton("Water colours");
        horiz.addComponent(waterColLabel);
        vert.addComponent(waterColLabel);

        ioSep = new JSeparator();
        horiz.addComponent(ioSep);
        vert.addComponent(ioSep);

        newWorld = new JButton("New world");
        horiz.addComponent(newWorld);
        vert.addComponent(newWorld);

        saveWorld = new JButton("Save world");
        horiz.addComponent(saveWorld);
        vert.addComponent(saveWorld);

        loadWorld = new JButton("Load world");
        horiz.addComponent(loadWorld);
        vert.addComponent(loadWorld);

    }
}
