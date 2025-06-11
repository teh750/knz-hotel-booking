package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ImagePanel extends JPanel {

  int width, height;

  ImageIcon backgroundImage;
  JLabel image;

  ImagePanel() {
    backgroundImage = new ImageIcon("Components/frame.png");

    image = new JLabel();
    image.setOpaque(false);
    image.setPreferredSize(new Dimension(1681, 961));
    image.setIcon(Main.iconResizer(backgroundImage, 1680, 961));

    this.setOpaque(false);
    this.setLayout(new BorderLayout());
    this.setBounds(0, 0, 1680, 961);
    this.add(image, BorderLayout.CENTER);
    this.addComponentListener(new ComponentAdapter() {
      @Override
      public void componentResized(ComponentEvent e) {
        width = getWidth();
        height = getHeight();
        image.setIcon(Main.iconResizer(backgroundImage, width, height));
        image.setBounds(0, 0, width, height);
        super.componentResized(e);
      }
    });
  }
}
