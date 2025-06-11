package com.knz;

import javax.swing.*;
import java.awt.*;

public class Facilities extends JPanel {

  JPanel top;
  JPanel bottom;
  JLabel title;

  JPanel one;
  JLabel label1;
  JLabel arcDecsLabel1;

  JPanel two;
  JLabel label2;
  JLabel gymDecsLabel2;

  JPanel three;
  JLabel label3;
  JLabel swimDecsLabel1;

  ImageIcon logo;
  ImageIcon arcade = new ImageIcon("Components/HotelArcade.jpg");
  ImageIcon gym = new ImageIcon("Components/HotelGym.jpg");
  ImageIcon swimmingpool = new ImageIcon("Components/swimmingPool.jpg");

  Facilities() {

    JLabel arcDescLabel1 = new JLabel(
        "<html><div style='text-align: center;'>Facilities for Arcade :<br>Arcade games, Pinball machines, Claw machines,<br>Streetball and Basketball shooting games<br><br>Our arcade is a gamer's paradise, with a wide variety of games to choose from. Whether you're a fan of classic pinball machines, or more modern arcade games, we've got something for everyone. Come visit us today and let the games begin!<br><br>Arcade size: 500 feet<br></div></html>");
    arcDescLabel1.setForeground(Color.white);
    arcDescLabel1.setFont(new Font("Arial", Font.BOLD, 20));
    arcDescLabel1.setVerticalAlignment(JLabel.BOTTOM);
    arcDescLabel1.setPreferredSize(new Dimension(550, 300));

    JLabel gymDescLabel2 = new JLabel(
        "<html><div style='text-align: center;'>Facilities for Gym :<br>Free weights: barbells, dumbbells, and weight plates<br>Weight machines:cables, pulleys, and resistance<br>Cardio machines: treadmills, stationary bikes, elliptical machines<br><br>Our gym is fully equipped with state-of-the-art equipment and a variety of fitness classes to help you stay in shape during your stay with us. Whether you're looking to maintain your fitness routine or just want to squeeze in a quick workout, our gym has everything you need. Come visit us today and stay fit while you travel!<br><br>Gym size: 800 feet<br></div></html>");
    gymDescLabel2.setForeground(Color.white);
    gymDescLabel2.setFont(new Font("Arial", Font.BOLD, 20));
    gymDescLabel2.setVerticalAlignment(JLabel.TOP);
    gymDescLabel2.setPreferredSize(new Dimension(650, 490));

    JLabel gymDescLabel3 = new JLabel(
        "<html><div style='text-align: center;'>Facilities for Swimming Pool :<br>Our hotel features a luxurious indoor swimming pool for our guests to enjoy during their stay. Whether you're looking to swim laps for exercise or simply relax and unwind, our pool has something for everyone.<br><br>Our pool area also features comfortable lounge chairs and towels for your convenience. Our pool is open year-round, so come take a dip and escape the heat or relax after a long day of sightseeing. We can't wait to see you!<br><br> size: 700 feet<br></div></html>");
    gymDescLabel3.setForeground(Color.white);
    gymDescLabel3.setFont(new Font("Arial", Font.BOLD, 20));
    gymDescLabel3.setVerticalAlignment(JLabel.BOTTOM);
    gymDescLabel3.setPreferredSize(new Dimension(550, 350));

    label1 = new JLabel();
    label1.setText("ARCADE");
    label1.setIcon(arcade);
    label1.setForeground(Color.white);
    label1.setFont(new Font("Arial", Font.BOLD, 30));
    label1.setHorizontalTextPosition(JLabel.CENTER);
    label1.setVerticalTextPosition(JLabel.TOP);
    label1.setIcon(Main.iconResizer(arcade, 700, 299));

    label2 = new JLabel();
    label2.setText("GYM");
    label2.setIcon(gym);
    label2.setForeground(Color.white);
    label2.setFont(new Font("Arial", Font.BOLD, 30));
    label2.setHorizontalTextPosition(JLabel.CENTER);
    label2.setVerticalTextPosition(JLabel.TOP);
    label2.setIcon(Main.iconResizer(gym, 700, 299));

    label3 = new JLabel();
    label3.setText("SWIMMINGPOOL");
    label3.setIcon(swimmingpool);
    label3.setForeground(Color.white);
    label3.setFont(new Font("Arial", Font.BOLD, 30));
    label3.setHorizontalTextPosition(JLabel.CENTER);
    label3.setVerticalTextPosition(JLabel.TOP);
    label3.setIcon(Main.iconResizer(swimmingpool, 700, 299));

    top = new JPanel();
    top.setPreferredSize(new Dimension(10680, 100));
    top.setOpaque(false);
    top.setLayout(new GridBagLayout());
    title = new JLabel(" Facilities in our Hotel KNZ");
    title.setForeground(Color.WHITE);
    title.setFont(new Font("Arial", Font.BOLD, 40));
    top.add(title);

    bottom = new JPanel();
    bottom.setPreferredSize(new Dimension(1680, 731));
    bottom.setOpaque(false);
    bottom.setLayout(new GridLayout(1, 3));

    one = new JPanel();
    one.setOpaque(false);

    two = new JPanel();
    two.setOpaque(false);

    three = new JPanel();
    three.setOpaque(false);

    bottom.add(one);
    bottom.add(two);
    bottom.add(three);

    one.add(label1);
    one.add(arcDescLabel1, BorderLayout.SOUTH);

    two.add(label2);
    two.add(gymDescLabel2, BorderLayout.SOUTH);

    three.add(label3);
    three.add(gymDescLabel3, BorderLayout.SOUTH);

    this.setOpaque(false);
    this.setLayout(new BorderLayout());
    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER);

  }
}
