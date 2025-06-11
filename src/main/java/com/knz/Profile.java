package com.knz;

import javax.swing.*;
import javax.swing.plaf.basic.BasicComboBoxUI;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class Profile extends JPanel {

  final Font ARIAL = new Font("Arial", Font.BOLD, 35);

  Room room;

  JPanel top, bottom;

  JPanel welcome;
  JLabel welcomeText;
  JPanel blackBarPanel;
  JLabel blackBar;
  JButton signOut;

  JLabel RoomID;
  JLabel roomType;
  JLabel nights;
  JLabel dateIn;
  JLabel dateOut;
  JLabel additionalTowels;
  JLabel additionalBeds;
  JLabel breakfastEntries;

  JLabel roomDesc;
  JButton cancelRoom;

  ArrayList<Integer> roomIDs;
  JComboBox roomSelector;

  Timer timer;
  int x = 1;

  ImageIcon BlackBarIcon = new ImageIcon("Components/selection.png");
  ImageIcon singleIcon = new ImageIcon("Components/Booking/Single.png");
  ImageIcon doubleIcon = new ImageIcon("Components/Booking/Double.png");
  ImageIcon familyIcon = new ImageIcon("Components/Booking/Family.png");
  ImageIcon penthouseIcon = new ImageIcon("Components/Booking/Penthouse.png");
  ImageIcon GreenTickIcon = new ImageIcon("Components/Transaction/GreenTick.png");

  Profile(User user, JFrame frame) {
    top = new JPanel();
    top.setBackground(new Color(0xF9F5EB));
    top.setPreferredSize(new Dimension(1680, 250));
    top.setLayout(new GridBagLayout());
    GridBagConstraints gbc = new GridBagConstraints();

    welcome = new JPanel();
    welcome.setPreferredSize(new Dimension(2000, 500));
    welcome.setOpaque(false);

    welcomeText = new JLabel("Welcome Back " + user.getUsername() + "!");
    welcomeText.setFont(new Font("Arial", Font.BOLD, 120));
    welcomeText.setForeground(Color.BLACK);
    signOut = new JButton("Sign Out");
    signOut.setOpaque(false);
    signOut.setBorder(BorderFactory.createEmptyBorder());
    signOut.setContentAreaFilled(false);
    signOut.setFocusable(false);
    signOut.setForeground(Color.black);
    signOut.setFont(new Font("Arial", Font.BOLD, 80));
    signOut.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        if (timer != null && timer.isRunning()) {
          timer.stop();
        }
        timer = new Timer(3, new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            if (x <= 330) {
              x += 3;
              blackBar.setIcon(Main.iconResizer(BlackBarIcon, x, 12));
              blackBarPanel.add(blackBar);
              blackBarPanel.repaint();
            } else {
              timer.stop();
            }
          }
        });
        timer.start();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        if (timer != null && timer.isRunning()) {
          timer.stop();
        }
        timer = new Timer(3, new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            if (x >= 1) {
              x -= 3;
              blackBar.setIcon(Main.iconResizer(BlackBarIcon, x, 12));
              blackBarPanel.add(blackBar);
              blackBarPanel.repaint();
            } else {
              blackBarPanel.remove(blackBar);
              blackBarPanel.repaint();
              timer.stop();
            }
          }
        });
        timer.start();
      }
    });
    signOut.addActionListener((e) -> {
      user.logout(frame);
    });
    blackBarPanel = new JPanel();
    blackBarPanel.setOpaque(false);
    blackBar = new JLabel();
    welcome.add(welcomeText);
    top.add(welcome, gbc);
    gbc.gridx = 0;
    gbc.gridy = 1;
    top.add(signOut, gbc);
    gbc.gridx = 0;
    gbc.gridy = 2;
    top.add(blackBarPanel, gbc);

    bottom = new JPanel(null);
    bottom.setOpaque(false);
    bottom.setPreferredSize(new Dimension(1680, 581));

    RoomID = new JLabel("Room ID: ");
    RoomID.setFont(new Font("Arial", Font.BOLD, 40));
    RoomID.setBounds(450, 40, 400, 40);
    bottom.add(RoomID);
    roomSelector = new JComboBox<>();
    roomSelector.addItem("No Room Selected");
    roomIDs = new ArrayList<>();
    roomIDs = user.getRoomNumbersList();
    for (Integer roomID : roomIDs) {
      roomSelector.addItem(roomID);
    }
    roomSelector.addActionListener((e) -> {
      try {
        room = new Room(user, roomSelector.getSelectedIndex());
        roomType.setText("Room Type: " + room.getRoomType());
        nights.setText("Nights: " + room.getNights());
        dateIn.setText("Date Check in: " + room.getDCIn());
        dateOut.setText("Date Check out: " + room.getDCOut());
        additionalTowels.setText("Additional Towels: " + room.getTowels());
        additionalBeds.setText("Additional Beds: " + room.getBeds());
        breakfastEntries.setText("Breakfast Entries: " + room.getPax());
        switch (room.getRoomType()) {
          case "Single":
            roomDesc.setIcon(singleIcon);
            roomDesc.repaint();
            break;
          case "Double":
            roomDesc.setIcon(doubleIcon);
            roomDesc.repaint();
            break;
          case "Family":
            roomDesc.setIcon(familyIcon);
            roomDesc.repaint();
            break;
          case "Penthouse":
            roomDesc.setIcon(penthouseIcon);
            roomDesc.repaint();
            break;
        }
        bottom.add(cancelRoom);
        bottom.repaint();
      } catch (Exception f) {
        clearRoomDetails();
      }
    });
    roomSelector.setEditable(true);
    roomSelector.setUI(new customComboBoxUI());
    roomSelector.setBounds(640, 40, 400, 40);
    roomSelector.setFont(new Font("Arial", Font.BOLD, 35));
    roomSelector.setBorder(BorderFactory.createLineBorder(Color.BLACK));
    bottom.add(roomSelector);

    roomType = new JLabel("Room Type: ");
    roomType.setFont(ARIAL);
    roomType.setBounds(180, 100, 600, 50);
    bottom.add(roomType);
    nights = new JLabel("Nights: ");
    nights.setFont(ARIAL);
    nights.setBounds(260, 140, 600, 50);
    bottom.add(nights);
    dateIn = new JLabel("Date Check in: ");
    dateIn.setFont(ARIAL);
    dateIn.setBounds(140, 180, 600, 50);
    bottom.add(dateIn);
    dateOut = new JLabel("Date Check out: ");
    dateOut.setFont(ARIAL);
    dateOut.setBounds(117, 220, 600, 50);
    bottom.add(dateOut);
    additionalTowels = new JLabel("Additional Towels: ");
    additionalTowels.setFont(ARIAL);
    additionalTowels.setBounds(72, 260, 600, 50);
    bottom.add(additionalTowels);
    additionalBeds = new JLabel("Additional Beds: ");
    additionalBeds.setFont(ARIAL);
    additionalBeds.setBounds(105, 300, 600, 50);
    bottom.add(additionalBeds);
    breakfastEntries = new JLabel("Breakfast Entries: ");
    breakfastEntries.setFont(ARIAL);
    breakfastEntries.setBounds(83, 340, 600, 50);
    bottom.add(breakfastEntries);
    roomDesc = new JLabel();
    roomDesc.setBounds(960, 85, 638, 329);
    bottom.add(roomDesc);
    cancelRoom = new JButton("Request Booking Cancellation");
    cancelRoom.setOpaque(false);
    cancelRoom.setBorder(BorderFactory.createEmptyBorder());
    cancelRoom.setContentAreaFilled(false);
    cancelRoom.setFocusable(false);
    cancelRoom.setFont(ARIAL);
    cancelRoom.setForeground(Color.BLACK);
    cancelRoom.setBounds(980, 450, 600, 50);
    cancelRoom.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        cancelRoom.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
        cancelRoom.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        cancelRoom.setBorder(BorderFactory.createEmptyBorder());
        cancelRoom.repaint();
      }
    });
    cancelRoom.addActionListener((e) -> {
      Integer thisRoomID = (Integer) roomSelector.getSelectedItem();
      room = new Room(user, roomSelector.getSelectedIndex());
      room.deleteJson(user);
      roomSelector.removeAllItems();
      roomSelector.addItem("No Room Selected");
      roomIDs = new ArrayList<>();
      roomIDs = user.getRoomNumbersList();
      for (Integer roomID : roomIDs) {
        roomSelector.addItem(roomID);
      }
      clearRoomDetails();
      Transaction.paymentConfirmNotification();
      JOptionPane.showMessageDialog(this,
          "Cancellation Successful! Room ID: " + thisRoomID
              + " has been cancelled. Any issues please contact KNZ support",
          "KNZ Online Services", JOptionPane.INFORMATION_MESSAGE, Main.iconResizer(GreenTickIcon, 50, 50));
    });

    this.setLayout(new BorderLayout());
    this.setBackground(new Color(0xE4DCCF));
    this.add(top, BorderLayout.NORTH);
    this.add(bottom, BorderLayout.CENTER);
  }

  public void clearRoomDetails() {
    roomType.setText("Room Type: ");
    nights.setText("Nights: ");
    dateIn.setText("Date Check in: ");
    dateOut.setText("Date Check out: ");
    additionalTowels.setText("Additional Towels: ");
    additionalBeds.setText("Additional Beds: ");
    breakfastEntries.setText("Breakfast Entries: ");
    roomDesc.setIcon(null);
    bottom.remove(cancelRoom);
    bottom.repaint();
  }

  class customComboBoxUI extends BasicComboBoxUI {
    protected JButton createArrowButton() {
      JButton button = (JButton) super.createArrowButton();
      button.setPreferredSize(new Dimension(20, 50));
      return button;
    }
  }
}
