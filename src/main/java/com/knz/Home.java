package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Home extends JPanel {

  JPanel top;
  JLabel title;

  JPanel content;
  JPanel descriptionCont;
  JLabel description;
  JLayeredPane LP;
  JPanel catalogueCont;
  JButton catalogue;
  JPanel backPanel;
  JLabel takeATour;
  JLabel whiteBar;

  JPanel bottom;

  ImageIcon titleIcon = new ImageIcon("Components/body_header.png");
  ImageIcon descriptionIcon = new ImageIcon("Components/Description.png");
  ImageIcon catalogueIcon = new ImageIcon("Components/Catalogue.png");
  ImageIcon takeATourIcon = new ImageIcon("Components/take_a_tour.png");
  ImageIcon whiteBarIcon = new ImageIcon("Components/whiteBar.png");

  Timer timer;
  int x = 1;

  Home(BodyPanel BP) {
    top = new JPanel();
    top.setOpaque(false);
    top.setPreferredSize(new Dimension(1681, 160));
    top.setLayout(new GridBagLayout());
    title = new JLabel();
    title.setIcon(Main.iconResizer(titleIcon, 875, 112));
    top.add(title);

    content = new JPanel();
    content.setOpaque(false);
    content.setPreferredSize(new Dimension(1680, 511));
    content.setLayout(new GridLayout(1, 2));
    descriptionCont = new JPanel();
    descriptionCont.setOpaque(false);
    descriptionCont.setLayout(new BorderLayout());
    description = new JLabel();
    description.setIcon(Main.iconResizer(descriptionIcon, 680, 340));
    JPanel North1 = new JPanel();
    North1.setOpaque(false);
    North1.setPreferredSize(new Dimension(840, 85));
    JPanel East1 = new JPanel();
    East1.setOpaque(false);
    East1.setPreferredSize(new Dimension(80, 340));
    JPanel West1 = new JPanel();
    West1.setOpaque(false);
    West1.setPreferredSize(new Dimension(80, 340));
    JPanel South1 = new JPanel();
    South1.setOpaque(false);
    South1.setPreferredSize(new Dimension(840, 85));
    descriptionCont.add(North1, BorderLayout.NORTH);
    descriptionCont.add(East1, BorderLayout.EAST);
    descriptionCont.add(West1, BorderLayout.WEST);
    descriptionCont.add(South1, BorderLayout.SOUTH);
    descriptionCont.add(description, BorderLayout.CENTER);

    LP = new JLayeredPane();
    LP.setLayout(null);
    catalogueCont = new JPanel();
    catalogueCont.setOpaque(false);
    catalogueCont.setLayout(new GridBagLayout());
    catalogueCont.setBounds(0, 0, 840, 511);
    catalogue = new JButton();
    catalogue.setOpaque(false);
    catalogue.setFocusable(false);
    catalogue.setContentAreaFilled(false);
    catalogue.setBorder(BorderFactory.createEmptyBorder());
    catalogue.setIcon(Main.iconResizer(catalogueIcon, 344, 68));
    catalogue.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        if (timer != null && timer.isRunning()) {
          timer.stop();
        }
        timer = new Timer(3, new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            if (x <= 344) {
              x += 3;
              whiteBar.setIcon(Main.iconResizer(whiteBarIcon, x, 68));
              backPanel.add(whiteBar);
              backPanel.repaint();
            } else {
              timer.stop();
            }
          }
        });
        timer.start();
      }

      public void mouseExited(MouseEvent e) {
        if (timer != null && timer.isRunning()) {
          timer.stop();
        }
        timer = new Timer(3, new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            if (x >= 1) {
              x -= 3;
              whiteBar.setIcon(Main.iconResizer(whiteBarIcon, x, 68));
              backPanel.add(whiteBar);
              backPanel.repaint();
            } else {
              backPanel.remove(whiteBar);
              backPanel.repaint();
              timer.stop();
            }
          }
        });
        timer.start();
      }
    });
    catalogue.addActionListener((e) -> {
      BP.catalogue();
    });
    catalogueCont.add(catalogue);
    takeATour = new JLabel();
    takeATour.setIcon(Main.iconResizer(takeATourIcon, 198, 93));
    takeATour.setBounds(190, 130, 198, 93);
    whiteBar = new JLabel();
    whiteBar.setBounds(248, 295, 344, 5);
    backPanel = new JPanel();
    backPanel.setLayout(null);
    backPanel.setOpaque(false);
    backPanel.setBounds(0, 0, 840, 511);
    backPanel.add(takeATour);
    LP.add(backPanel, Integer.valueOf(0));
    LP.add(catalogueCont, Integer.valueOf(1));
    content.add(descriptionCont);
    content.add(LP);

    bottom = new JPanel();
    bottom.setOpaque(false);
    bottom.setPreferredSize(new Dimension(1681, 160));

    this.setLayout(new BorderLayout());
    this.setOpaque(false);
    this.add(top, BorderLayout.NORTH);
    this.add(content, BorderLayout.CENTER);
    this.add(bottom, BorderLayout.SOUTH);
  }
}
