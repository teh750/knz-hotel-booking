package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Homepage extends JFrame {

  int width, height;

  JLayeredPane LP;
  ImageIcon logo;
  ImagePanel background;
  JPanel frontPanel;
  HeaderPanel HP;
  BodyPanel BP;

  Homepage(boolean isLogin, User user) {
    logo = new ImageIcon("Components/KNZ_logo.png");
    BP = new BodyPanel(isLogin, user, this);
    HP = new HeaderPanel(isLogin, user, BP, this);

    frontPanel = new JPanel(new BorderLayout());
    frontPanel.setOpaque(false);
    frontPanel.setBounds(0, 0, 1680, 961);
    frontPanel.add(HP, BorderLayout.NORTH);
    frontPanel.add(BP, BorderLayout.CENTER);

    background = new ImagePanel();

    LP = new JLayeredPane();
    LP.setBounds(0, 0, 1680, 961);
    LP.setLayout(null);
    LP.add(background, Integer.valueOf(0));
    LP.add(frontPanel, Integer.valueOf(1));

    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setTitle("KNZ Hotel Online Booking System");
    this.setIconImage(logo.getImage());
    this.setLayout(null);
    this.setSize(1680, 961);
    this.setResizable(true);
    this.setLocationRelativeTo(null);
    this.add(LP);
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        width = getWidth();
        height = getHeight();
        LP.setBounds(0, 0, width, height);
        background.setBounds(0, 0, LP.getWidth(), LP.getHeight());
        frontPanel.setBounds(0, 0, LP.getWidth(), LP.getHeight());
        // System.out.println(width);
        // System.out.println(height);
        super.componentResized(e);
      }
    });
    this.setVisible(true);
  }
}
