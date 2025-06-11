package com.knz;

import javax.swing.*;
import java.awt.*;

public class ContactUs extends JPanel {

  JPanel body;
  JPanel top;
  JPanel left;
  JPanel middle;
  JPanel right;
  JLabel sloganText;
  JLabel boonkimText;
  JLabel effendyText;
  JLabel syedText;

  ImageIcon sloganIcon = new ImageIcon("Components/slogan.png");
  ImageIcon boonkimIcon = new ImageIcon("Components/gbk.png");
  ImageIcon effendyIcon = new ImageIcon("Components/net.png");
  ImageIcon syedIcon = new ImageIcon("Components/syed.png");

  ContactUs() {
    body = new JPanel();
    body.setPreferredSize(new Dimension(1681, 709));
    body.setBackground(new Color(0x4b5aff));
    body.setLayout(new BorderLayout());
    top = new JPanel();
    top.setPreferredSize(new Dimension(1681, 130));
    top.setLayout(new GridBagLayout());
    top.setBackground(new Color(0x4b5aff));
    sloganText = new JLabel();
    sloganText.setIcon(sloganIcon);
    sloganText.setIcon(Main.iconResizer(sloganIcon, 1681, 130));
    top.add(sloganText);

    middle = new JPanel();
    middle.setPreferredSize(new Dimension(500, 600));
    middle.setLayout(new GridBagLayout());
    middle.setOpaque(false);
    boonkimText = new JLabel();
    boonkimText.setIcon(boonkimIcon);
    boonkimText.setOpaque(false);
    boonkimText.setIcon(Main.iconResizer(boonkimIcon, 500, 600));

    middle.add(boonkimText);
    body.add(middle, BorderLayout.CENTER);

    left = new JPanel();
    left.setPreferredSize(new Dimension(500, 600));
    left.setLayout(new GridBagLayout());
    left.setOpaque(false);
    effendyText = new JLabel();
    effendyText.setIcon(effendyIcon);
    effendyText.setOpaque(false);
    effendyText.setIcon(Main.iconResizer(effendyIcon, 500, 600));

    left.add(effendyText);
    body.add(left, BorderLayout.WEST);

    right = new JPanel();
    right.setPreferredSize(new Dimension(500, 600));
    right.setLayout(new GridBagLayout());
    right.setOpaque(false);
    syedText = new JLabel();
    syedText.setIcon(syedIcon);
    syedText.setOpaque(false);
    syedText.setIcon(Main.iconResizer(syedIcon, 500, 600));

    right.add(syedText);
    body.add(right, BorderLayout.EAST);

    this.setLayout(new BorderLayout());
    this.setOpaque(false);

    this.add(top, BorderLayout.NORTH);
    this.add(body, BorderLayout.CENTER);

  }
}
