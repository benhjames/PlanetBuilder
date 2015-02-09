package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

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
    private JButton groundColButton;
    private JButton waterColButton;
    private JSeparator ioSep;
    private JButton newWorld;
    private JButton saveWorld;
    private JButton loadWorld;

    public CommandAndControl() {
        setTitle("Planet Builder - Controls");
        setSize(200, 768);
        drawInitialUI();
        refreshValues();
    }

    public void refreshValues() {
        detailSlider.setValue((int) GlobalOptions.getInstance().getDetailLevel());
        renderCheck.setSelected(GlobalOptions.getInstance().isRenderHigh());
        terrainSlider.setValue((int) WorldOptions.getInstance().getTerrainFactor());
        vegetationSlider.setValue((int) WorldOptions.getInstance().getVegetationFactor());
        desertSlider.setValue((int) WorldOptions.getInstance().getDesertFactor());
        iceSlider.setValue((int) WorldOptions.getInstance().getIceFactor());
        waterSlider.setValue((int) WorldOptions.getInstance().getWaterFactor());
        settlementSlider.setValue((int) WorldOptions.getInstance().getSettlementLevel());
        ringsCheck.setSelected(WorldOptions.getInstance().isPlanetRings());
        inhabitedCheck.setSelected(WorldOptions.getInstance().isInhabitants());
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
        detailSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                GlobalOptions.getInstance().setDetailLevel(detailSlider.getValue());
            }
        });
        horiz.addComponent(detailSlider);
        vert.addComponent(detailSlider);

        renderLabel = new JLabel("Render details?");
        horiz.addComponent(renderLabel);
        vert.addComponent(renderLabel);

        renderCheck = new JCheckBox();
        renderCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                GlobalOptions.getInstance().setRenderHigh(renderCheck.isSelected());
            }
        });
        horiz.addComponent(renderCheck);
        vert.addComponent(renderCheck);

        globalSep = new JSeparator();
        horiz.addComponent(globalSep);
        vert.addComponent(globalSep);

        terrainLabel = new JLabel("Terrain");
        horiz.addComponent(terrainLabel);
        vert.addComponent(terrainLabel);

        terrainSlider = new JSlider(0, 100);
        terrainSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setTerrainFactor(terrainSlider.getValue());
            }
        });
        horiz.addComponent(terrainSlider);
        vert.addComponent(terrainSlider);

        vegetationLabel = new JLabel("Vegetation");
        horiz.addComponent(vegetationLabel);
        vert.addComponent(vegetationLabel);

        vegetationSlider = new JSlider(0, 100);
        vegetationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setVegetationFactor(vegetationSlider.getValue());
            }
        });
        horiz.addComponent(vegetationSlider);
        vert.addComponent(vegetationSlider);

        desertLabel = new JLabel("Desert");
        horiz.addComponent(desertLabel);
        vert.addComponent(desertLabel);

        desertSlider = new JSlider(0, 100);
        desertSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setDesertFactor(desertSlider.getValue());
            }
        });
        horiz.addComponent(desertSlider);
        vert.addComponent(desertSlider);

        iceLabel = new JLabel("Ice");
        horiz.addComponent(iceLabel);
        vert.addComponent(iceLabel);

        iceSlider = new JSlider(0, 100);
        iceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setIceFactor(iceSlider.getValue());
            }
        });
        horiz.addComponent(iceSlider);
        vert.addComponent(iceSlider);

        waterLabel = new JLabel("Water Level");
        horiz.addComponent(waterLabel);
        vert.addComponent(waterLabel);

        waterSlider = new JSlider(0, 100);
        waterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setWaterFactor(waterSlider.getValue());
            }
        });
        horiz.addComponent(waterSlider);
        vert.addComponent(waterSlider);

        settlementLabel = new JLabel("Settlements");
        horiz.addComponent(settlementLabel);
        vert.addComponent(settlementLabel);

        settlementSlider = new JSlider(0, 100);
        settlementSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setSettlementLevel(settlementSlider.getValue());
            }
        });
        horiz.addComponent(settlementSlider);
        vert.addComponent(settlementSlider);

        ringsLabel = new JLabel("Planet rings?");
        horiz.addComponent(ringsLabel);
        vert.addComponent(ringsLabel);

        ringsCheck = new JCheckBox();
        ringsCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setPlanetRings(ringsCheck.isSelected());
            }
        });
        horiz.addComponent(ringsCheck);
        vert.addComponent(ringsCheck);

        inhabitedLabel = new JLabel("Inhabited?");
        horiz.addComponent(inhabitedLabel);
        vert.addComponent(inhabitedLabel);

        inhabitedCheck = new JCheckBox();
        inhabitedCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setInhabitants(inhabitedCheck.isSelected());
            }
        });
        horiz.addComponent(inhabitedCheck);
        vert.addComponent(inhabitedCheck);

        //Will open colour chooser in popup
        groundColButton = new JButton("Ground colours");
        groundColButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ColourChooser c = new ColourChooser(CommandAndControl.this, "Ground Colours", ColourChooser.ColourableItems.GROUND);
                c.setVisible(true);
            }
        });
        horiz.addComponent(groundColButton);
        vert.addComponent(groundColButton);

        //Will open colour chooser in popup
        waterColButton = new JButton("Water colours");
        waterColButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ColourChooser c = new ColourChooser(CommandAndControl.this, "Water Colours", ColourChooser.ColourableItems.WATER);
                c.setVisible(true);
            }
        });
        horiz.addComponent(waterColButton);
        vert.addComponent(waterColButton);

        ioSep = new JSeparator();
        horiz.addComponent(ioSep);
        vert.addComponent(ioSep);

        newWorld = new JButton("New world");
        newWorld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                WorldOptions.replaceInstance();
                refreshValues();
            }
        });
        horiz.addComponent(newWorld);
        vert.addComponent(newWorld);

        saveWorld = new JButton("Save world");
        saveWorld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser saveFile = new JFileChooser();
                int returnVal = saveFile.showSaveDialog(null);
                if (returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        String path = saveFile.getSelectedFile().getAbsolutePath();
                        WorldOptions.writeToFile(WorldOptions.getInstance(), path);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        horiz.addComponent(saveWorld);
        vert.addComponent(saveWorld);

        loadWorld = new JButton("Load world");
        loadWorld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JFileChooser loadFile = new JFileChooser();
                int returnVal = loadFile.showOpenDialog(null);
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                    try {
                        String path = loadFile.getSelectedFile().getAbsolutePath();
                        WorldOptions.readFromFile(path);
                        refreshValues();
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                }

            }
        });
        horiz.addComponent(loadWorld);
        vert.addComponent(loadWorld);

    }
}
