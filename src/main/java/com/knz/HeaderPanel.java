package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HeaderPanel extends JPanel {

  JPanel topHeader;

  ImageIcon bar = new ImageIcon("./Components/selection.png");
  ImageIcon triangle = new ImageIcon("./Components/triangle.png");

  JPanel title;
  JLabel titleLabel;

  JLayeredPane LP1;
  JPanel booking;
  JButton bookingButton;
  GridBagConstraints gbcBooking;
  JPanel compCont1;
  JLabel bar1;
  JLabel tri1;

  JLayeredPane LP2;
  JPanel facilities;
  JButton facilitiesButton;
  GridBagConstraints gbcFacilities;
  JPanel compCont2;
  JLabel bar2;
  JLabel tri2;

  JLayeredPane LP3;
  JPanel contact;
  JButton contactButton;
  GridBagConstraints gbcContact;
  JPanel compCont3;
  JLabel bar3;
  JLabel tri3;

  JLayeredPane LP4;
  JPanel login;
  JButton loginButton;
  GridBagConstraints gbcLogin;
  JPanel compCont4;
  JLabel bar4;
  JLabel tri4;

  JPanel bottomHeader;

  int choice = 0;

  HeaderPanel(boolean isLogin, User user, BodyPanel BP, JFrame frame) {
    topHeader = new JPanel();
    topHeader.setPreferredSize(new Dimension(1920, 120));
    topHeader.setBackground(Color.WHITE);
    topHeader.setLayout(new GridLayout(1, 7));

    JPanel emp1 = new JPanel();
    emp1.setOpaque(false);
    topHeader.add(emp1);

    title = new JPanel();
    title.setLayout(new BorderLayout());
    title.setOpaque(false);
    titleLabel = new JLabel("KNZ Hotel");
    titleLabel.setFont(new Font("Arial", Font.BOLD, 28));
    titleLabel.setIconTextGap(10);
    ImageIcon logo = new ImageIcon("Components/KNZ_logo.png");
    titleLabel.setIcon(logo);
    titleLabel.addMouseListener(new MouseAdapter() {
      public void mousePressed(MouseEvent e) {
        resetHeaderEffects();
        BP.home();
      }
    });
    title.add(titleLabel, BorderLayout.CENTER);
    topHeader.add(title);

    JPanel emp2 = new JPanel();
    emp2.setOpaque(false);
    topHeader.add(emp2);

    LP1 = new JLayeredPane();
    LP1.setLayout(null);
    booking = new JPanel();
    booking.setOpaque(false);
    booking.setLayout(new GridBagLayout());
    booking.setBounds(0, 0, 274, 120);
    gbcBooking = new GridBagConstraints();
    bookingButton = new JButton("Booking");
    bookingButton.setOpaque(false);
    bookingButton.setBorder(BorderFactory.createEmptyBorder());
    bookingButton.setContentAreaFilled(false);
    bookingButton.setFocusable(false);
    bookingButton.setForeground(Color.GRAY);
    bookingButton.setFont(new Font("Arial", Font.BOLD, 18));
    bookingButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    gbcBooking.gridx = 0;
    gbcBooking.gridy = 0;
    booking.add(bookingButton, gbcBooking);
    LP1.add(booking, Integer.valueOf(1));
    compCont1 = new JPanel();
    compCont1.setLayout(null);
    compCont1.setBounds(0, 0, 274, 120);
    compCont1.setOpaque(false);
    bar1 = new JLabel();
    bar1.setIcon(Main.iconResizer(bar, 75, 3));
    bar1.setBounds(100, 70, 75, 5);
    tri1 = new JLabel();
    tri1.setIcon(triangle);
    tri1.setBounds(113, 110, 47, 26);
    LP1.add(compCont1, Integer.valueOf(0));
    bookingButton.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        bookingButton.setForeground(Color.BLACK);
        compCont1.add(bar1);
        compCont1.add(tri1);
        compCont1.repaint();
      }

      public void mouseExited(MouseEvent e) {
        compCont1.remove(bar1);
        compCont1.repaint();
        if (choice != 1) {
          bookingButton.setForeground(Color.GRAY);
          compCont1.remove(tri1);
          compCont1.repaint();
        }
      }
    });
    bookingButton.addActionListener((e) -> {
      if (choice == 2) {
        facilitiesButton.setForeground(Color.GRAY);
        compCont2.remove(bar2);
        compCont2.remove(tri2);
        compCont2.repaint();
      } else if (choice == 3) {
        contactButton.setForeground(Color.GRAY);
        compCont3.remove(bar3);
        compCont3.remove(tri3);
        compCont3.repaint();
      } else if (choice == 4) {
        loginButton.setForeground(Color.GRAY);
        compCont4.remove(bar4);
        compCont4.remove(tri4);
        compCont4.repaint();
      }
      choice = 1;
      if (isLogin) {
        BP.booking(user, frame);
      } else {
        BP.catalogue();
      }
    });
    topHeader.add(LP1);

    LP2 = new JLayeredPane();
    LP2.setLayout(null);
    facilities = new JPanel();
    facilities.setOpaque(false);
    facilities.setLayout(new GridBagLayout());
    facilities.setBounds(0, 0, 274, 120);
    gbcFacilities = new GridBagConstraints();
    facilitiesButton = new JButton("Facilities");
    facilitiesButton.setOpaque(false);
    facilitiesButton.setBorder(BorderFactory.createEmptyBorder());
    facilitiesButton.setContentAreaFilled(false);
    facilitiesButton.setFocusable(false);
    facilitiesButton.setForeground(Color.GRAY);
    facilitiesButton.setFont(new Font("Arial", Font.BOLD, 18));
    facilitiesButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    gbcFacilities.gridx = 0;
    gbcFacilities.gridy = 0;
    facilities.add(facilitiesButton, gbcFacilities);
    LP2.add(facilities, Integer.valueOf(1));
    compCont2 = new JPanel();
    compCont2.setLayout(null);
    compCont2.setBounds(0, 0, 274, 120);
    compCont2.setOpaque(false);
    bar2 = new JLabel();
    bar2.setIcon(Main.iconResizer(bar, 80, 3));
    bar2.setBounds(96, 70, 80, 5);
    tri2 = new JLabel();
    tri2.setIcon(triangle);
    tri2.setBounds(113, 110, 47, 26);
    LP2.add(compCont2, Integer.valueOf(0));
    facilitiesButton.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        facilitiesButton.setForeground(Color.BLACK);
        compCont2.add(bar2);
        compCont2.add(tri2);
        compCont2.repaint();
      }

      public void mouseExited(MouseEvent e) {
        compCont2.remove(bar2);
        compCont2.repaint();
        if (choice != 2) {
          facilitiesButton.setForeground(Color.GRAY);
          compCont2.remove(tri2);
          compCont2.repaint();
        }
      }
    });
    facilitiesButton.addActionListener((e) -> {
      if (choice == 1) {
        bookingButton.setForeground(Color.GRAY);
        compCont1.remove(bar1);
        compCont1.remove(tri1);
        compCont1.repaint();
      } else if (choice == 3) {
        contactButton.setForeground(Color.GRAY);
        compCont3.remove(bar3);
        compCont3.remove(tri3);
        compCont3.repaint();
      } else if (choice == 4) {
        loginButton.setForeground(Color.GRAY);
        compCont4.remove(bar4);
        compCont4.remove(tri4);
        compCont4.repaint();
      }
      choice = 2;
      BP.facilities();
    });
    topHeader.add(LP2);

    LP3 = new JLayeredPane();
    LP3.setLayout(null);
    contact = new JPanel();
    contact.setOpaque(false);
    contact.setLayout(new GridBagLayout());
    contact.setBounds(0, 0, 274, 120);
    gbcContact = new GridBagConstraints();
    contactButton = new JButton("Contact Us");
    contactButton.setOpaque(false);
    contactButton.setBorder(BorderFactory.createEmptyBorder());
    contactButton.setContentAreaFilled(false);
    contactButton.setFocusable(false);
    contactButton.setForeground(Color.GRAY);
    contactButton.setFont(new Font("Arial", Font.BOLD, 18));
    contactButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    gbcContact.gridx = 0;
    gbcContact.gridy = 0;
    contact.add(contactButton, gbcContact);
    LP3.add(contact, Integer.valueOf(1));
    compCont3 = new JPanel();
    compCont3.setLayout(null);
    compCont3.setBounds(0, 0, 274, 120);
    compCont3.setOpaque(false);
    bar3 = new JLabel();
    bar3.setIcon(Main.iconResizer(bar, 100, 3));
    bar3.setBounds(87, 70, 100, 5);
    tri3 = new JLabel();
    tri3.setIcon(triangle);
    tri3.setBounds(113, 110, 47, 26);
    LP3.add(compCont3, Integer.valueOf(0));
    contactButton.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        contactButton.setForeground(Color.BLACK);
        compCont3.add(bar3);
        compCont3.add(tri3);
        compCont3.repaint();
      }

      public void mouseExited(MouseEvent e) {
        compCont3.remove(bar3);
        compCont3.repaint();
        if (choice != 3) {
          contactButton.setForeground(Color.GRAY);
          compCont3.remove(tri3);
          compCont3.repaint();
        }
      }
    });
    contactButton.addActionListener((e) -> {
      if (choice == 1) {
        bookingButton.setForeground(Color.GRAY);
        compCont1.remove(bar1);
        compCont1.remove(tri1);
        compCont1.repaint();
      } else if (choice == 2) {
        facilitiesButton.setForeground(Color.GRAY);
        compCont2.remove(bar2);
        compCont2.remove(tri2);
        compCont2.repaint();
      } else if (choice == 4) {
        loginButton.setForeground(Color.GRAY);
        compCont4.remove(bar4);
        compCont4.remove(tri4);
        compCont4.repaint();
      }
      choice = 3;
      BP.contact();
    });
    topHeader.add(LP3);

    LP4 = new JLayeredPane();
    LP4.setLayout(null);
    login = new JPanel();
    login.setOpaque(false);
    login.setLayout(new GridBagLayout());
    login.setBounds(0, 0, 274, 120);
    gbcLogin = new GridBagConstraints();
    loginButton = new JButton();
    if (isLogin == true) {
      loginButton.setText(user.getUsername());
    } else {
      loginButton.setText("Login");
    }
    loginButton.setOpaque(false);
    loginButton.setBorder(BorderFactory.createEmptyBorder());
    loginButton.setContentAreaFilled(false);
    loginButton.setFocusable(false);
    loginButton.setForeground(Color.GRAY);
    loginButton.setFont(new Font("Arial", Font.BOLD, 18));
    loginButton.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    gbcLogin.gridx = 0;
    gbcLogin.gridy = 0;
    login.add(loginButton, gbcLogin);
    LP4.add(login, Integer.valueOf(1));
    compCont4 = new JPanel();
    compCont4.setLayout(null);
    compCont4.setBounds(0, 0, 274, 120);
    compCont4.setOpaque(false);
    bar4 = new JLabel();
    bar4.setIcon(Main.iconResizer(bar, 55, 3));
    bar4.setBounds(110, 70, 55, 5);
    tri4 = new JLabel();
    tri4.setIcon(triangle);
    tri4.setBounds(113, 110, 47, 26);
    LP4.add(compCont4, Integer.valueOf(0));
    loginButton.addMouseListener(new MouseAdapter() {
      public void mouseEntered(MouseEvent e) {
        loginButton.setForeground(Color.BLACK);
        compCont4.add(bar4);
        compCont4.add(tri4);
        compCont4.repaint();
      }

      public void mouseExited(MouseEvent e) {
        compCont4.remove(bar4);
        compCont4.repaint();
        if (choice != 4) {
          loginButton.setForeground(Color.GRAY);
          compCont4.remove(tri4);
          compCont4.repaint();
        }
      }
    });
    loginButton.addActionListener((e) -> {
      if (choice == 1) {
        bookingButton.setForeground(Color.GRAY);
        compCont1.remove(bar1);
        compCont1.remove(tri1);
        compCont1.repaint();
      } else if (choice == 2) {
        facilitiesButton.setForeground(Color.GRAY);
        compCont2.remove(bar2);
        compCont2.remove(tri2);
        compCont2.repaint();
      } else if (choice == 3) {
        contactButton.setForeground(Color.GRAY);
        compCont3.remove(bar3);
        compCont3.remove(tri3);
        compCont3.repaint();
      }
      choice = 4;
      if (isLogin == true) {
        BP.profile(user, frame);
      } else {
        BP.login();
      }
    });
    topHeader.add(LP4);

    bottomHeader = new JPanel();
    bottomHeader.setPreferredSize(new Dimension(1680, 10));
    bottomHeader.setBackground(new Color(0x5539CC));

    this.setPreferredSize(new Dimension(1680, 130));
    this.setLayout(new BorderLayout());
    this.add(topHeader, BorderLayout.NORTH);
    this.add(bottomHeader, BorderLayout.SOUTH);
  }

  public void resetHeaderEffects() {
    choice = 0;
    bookingButton.setForeground(Color.GRAY);
    compCont1.removeAll();
    compCont1.repaint();
    facilitiesButton.setForeground(Color.GRAY);
    compCont2.removeAll();
    compCont2.repaint();
    contactButton.setForeground(Color.GRAY);
    compCont3.removeAll();
    compCont3.repaint();
    loginButton.setForeground(Color.GRAY);
    compCont4.removeAll();
    compCont4.repaint();
  }
}
