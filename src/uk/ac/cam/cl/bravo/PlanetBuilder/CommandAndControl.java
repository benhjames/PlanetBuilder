package uk.ac.cam.cl.bravo.PlanetBuilder;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class CommandAndControl extends JPanel {

    //Declare controls
    private JLabel detailLabel;
    private JSlider detailSlider;
    private JLabel renderLabel;
    private JCheckBox renderCheck;
    private JLabel panLabel;
    private JCheckBox panCheck;
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
    private JButton exportWorld;

    public CommandAndControl() {
        setSize(200, 768);
        drawInitialUI();
        refreshValues();
    }

    public void refreshValues() {
        detailSlider.setValue((int) GlobalOptions.getInstance().getDetailLevel());
        renderCheck.setSelected(GlobalOptions.getInstance().isRenderHigh());
        panCheck.setSelected(GlobalOptions.getInstance().isAutoPan());
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
        GridBagLayout gbl = new GridBagLayout();
        setLayout(gbl);
        GridBagConstraints c;

        //In general, for each control, create it, add change listener, add to groups

        detailLabel = new JLabel("Detail Level", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 0;
        c.gridheight=1;
        c.gridwidth = 4;
        add(detailLabel, c);

        detailSlider = new JSlider(0, 100);
        detailSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                if(!detailSlider.getValueIsAdjusting()) {
                    GlobalOptions.getInstance().setDetailLevel(detailSlider.getValue());
                }
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 4;
        c.gridheight = 1;
        add(detailSlider, c);

        renderLabel = new JLabel("Details?", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.7;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 2;
        c.gridheight=1;
        c.gridwidth=1;
        add(renderLabel, c);

        renderCheck = new JCheckBox();
        renderCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                GlobalOptions.getInstance().setRenderHigh(renderCheck.isSelected());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridx = 1;
        c.gridy = 2;
        c.gridheight=1;
        c.gridwidth=1;
        add(renderCheck, c);

        panLabel = new JLabel("Rotate?", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.gridy = 2;
        c.weighty=0.1;
        c.weightx=0.7;
        c.gridheight=1;
        c.gridwidth=1;
        add(panLabel, c);

        panCheck = new JCheckBox();
        panCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                GlobalOptions.getInstance().setAutoPan(panCheck.isSelected());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridy = 2;
        c.gridheight=1;
        c.gridwidth=1;
        add(panCheck, c);

        globalSep = new JSeparator();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 0.01;
        c.gridy = 3;
        c.gridheight=1;
        c.gridwidth=4;
        add(globalSep, c);

        terrainLabel = new JLabel("Terrain", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 4;
        c.gridheight=1;
        c.gridwidth = 4;
        add(terrainLabel, c);

        terrainSlider = new JSlider(0, 100);
        terrainSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setTerrainFactor(terrainSlider.getValue());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 5;
        c.gridwidth = 4;
        c.gridheight = 1;
        add(terrainSlider, c);

        vegetationLabel = new JLabel("Vegetation", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 6;
        c.gridheight=1;
        c.gridwidth=4;
        add(vegetationLabel, c);

        vegetationSlider = new JSlider(0, 100);
        vegetationSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setVegetationFactor(vegetationSlider.getValue());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 4;
        c.gridheight = 1;
        add(vegetationSlider, c);

        desertLabel = new JLabel("Desert", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 8;
        c.gridheight=1;
        c.gridwidth=4;
        add(desertLabel, c);

        desertSlider = new JSlider(0, 100);
        desertSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setDesertFactor(desertSlider.getValue());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 9;
        c.gridwidth = 4;
        c.gridheight = 1;
        add(desertSlider, c);

        iceLabel = new JLabel("Ice", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 10;
        c.gridheight=1;
        c.gridwidth=4;
        add(iceLabel, c);

        iceSlider = new JSlider(0, 100);
        iceSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setIceFactor(iceSlider.getValue());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 11;
        c.gridwidth = 4;
        c.gridheight = 1;
        add(iceSlider, c);

        waterLabel = new JLabel("Water Level", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 12;
        c.gridheight=1;
        c.gridwidth=4;
        add(waterLabel, c);

        waterSlider = new JSlider(0, 100);
        waterSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setWaterFactor(waterSlider.getValue());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 13;
        c.gridwidth = 4;
        c.gridheight = 1;
        add(waterSlider, c);

        settlementLabel = new JLabel("Settlements", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 14;
        c.gridheight=1;
        c.gridwidth=4;
        add(settlementLabel, c);

        settlementSlider = new JSlider(0, 100);
        settlementSlider.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setSettlementLevel(settlementSlider.getValue());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 1;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 15;
        c.gridwidth = 4;
        c.gridheight = 1;
        add(settlementSlider, c);

        ringsLabel = new JLabel("Rings?", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.7;
        c.weighty = 0.1;
        c.gridx = 0;
        c.gridy = 16;
        c.gridheight=1;
        c.gridwidth=1;
        add(ringsLabel, c);

        ringsCheck = new JCheckBox();
        ringsCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setPlanetRings(ringsCheck.isSelected());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 1;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridy = 16;
        c.gridheight=1;
        c.gridwidth=1;
        add(ringsCheck, c);

        inhabitedLabel = new JLabel("Inhabited?", SwingConstants.CENTER);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.7;
        c.weighty = 0.1;
        c.gridx = 2;
        c.gridy = 16;
        c.gridheight=1;
        c.gridwidth=1;
        add(inhabitedLabel, c);


        inhabitedCheck = new JCheckBox();
        inhabitedCheck.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent changeEvent) {
                WorldOptions.getInstance().setInhabitants(inhabitedCheck.isSelected());
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 3;
        c.weightx = 0.1;
        c.weighty = 0.1;
        c.gridy = 16;
        c.gridheight=1;
        c.gridwidth=1;
        add(inhabitedCheck, c);

        //Will open colour chooser in popup
        groundColButton = new JButton("Ground Cols");
        groundColButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ColourChooser c = new ColourChooser(MainClass.window, "Ground Colours", ColourChooser.ColourableItems.GROUND);
                c.setVisible(true);
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridy = 17;
        c.gridheight=1;
        c.gridwidth=2;
        add(groundColButton, c);

        //Will open colour chooser in popup
        waterColButton = new JButton("Water Cols");
        waterColButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ColourChooser c = new ColourChooser(MainClass.window, "Water Colours", ColourChooser.ColourableItems.WATER);
                c.setVisible(true);
            }
        });
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridy = 17;
        c.gridheight=1;
        c.gridwidth=2;
        add(waterColButton, c);

        ioSep = new JSeparator();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 1;
        c.weighty = 0.01;
        c.gridy = 17;
        c.gridheight=1;
        c.gridwidth=4;
        add(ioSep, c);

        newWorld = new JButton("New world");
        newWorld.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                WorldOptions.replaceInstance();
                refreshValues();
            }
        });
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridy = 18;
        c.gridheight=1;
        c.gridwidth=2;
        add(newWorld, c);

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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 0;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridy = 19;
        c.gridheight=1;
        c.gridwidth=2;
        add(saveWorld, c);

        exportWorld = new JButton("Export world");
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridy = 19;
        c.gridheight=1;
        c.gridwidth=2;
        add(exportWorld, c);

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
        c.fill = GridBagConstraints.HORIZONTAL;
        c.gridx = 2;
        c.weightx = 0.5;
        c.weighty = 0.1;
        c.gridy = 18;
        c.gridheight=1;
        c.gridwidth=2;
        add(loadWorld, c);

    }
}
