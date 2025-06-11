package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Catalogue extends JPanel {

  JLayeredPane LP;
  JPanel beneath, above;
  JButton chooseButton;
  JButton loginButton;
  JLabel blackBar;

  JPanel top;
  JPanel bottom;
  CardLayout c = new CardLayout();
  JPanel left;
  JPanel right;

  JPanel singleRoom;
  JPanel leftSingle;
  JLabel singleDescription;
  JPanel rightSingle;
  JLabel singleFeature;

  JPanel doubleRoom;
  JPanel leftDouble;
  JLabel doubleDescription;
  JPanel rightDouble;
  JLabel doubleFeature;

  JPanel familyRoom;
  JPanel leftFamily;
  JLabel familyDescription;
  JPanel rightFamily;
  JLabel familyFeature;

  JPanel penthouse;
  JPanel leftPenthouse;
  JLabel penthouseDescription;
  JPanel rightPenthouse;
  JLabel penthouseFeature;

  JLabel catalogueLogo;
  JButton previous;
  JButton next;

  ImageIcon catalogueIcon = new ImageIcon("Components/Catalogue/Catalogue.png");
  ImageIcon leftIcon = new ImageIcon("Components/Catalogue/left.png");
  ImageIcon rightIcon = new ImageIcon("Components/Catalogue/right.png");

  ImageIcon singleDescriptionIcon = new ImageIcon("Components/Catalogue/Single.png");
  ImageIcon singleFeatureIcon = new ImageIcon("Components/Catalogue/featureSingle.png");
  ImageIcon doubleDescriptionIcon = new ImageIcon("Components/Catalogue/Double.png");
  ImageIcon doubleFeatureIcon = new ImageIcon("Components/Catalogue/featureDouble.png");
  ImageIcon familyDescriptionIcon = new ImageIcon("Components/Catalogue/Family.png");
  ImageIcon familyFeatureIcon = new ImageIcon("Components/Catalogue/featureFamily.png");
  ImageIcon penthouseDescriptionIcon = new ImageIcon("Components/Catalogue/Penthouse.png");
  ImageIcon penthouseFeatureIcon = new ImageIcon("Components/Catalogue/featurePenthouse.png");

  ImageIcon ChooseRoomIcon = new ImageIcon("Components/Catalogue/ChooseThisRoom.png");
  ImageIcon LoginBookIcon = new ImageIcon("Components/Catalogue/LoginToBook.png");
  ImageIcon blackBarIcon = new ImageIcon("Components/selection.png");

  Catalogue(BodyPanel BP, boolean isLogin, User user, JFrame frame) {
    DoublyLinkList DLL = new DoublyLinkList();
    DLL.addNode("Single");
    DLL.addNode("Double");
    DLL.addNode("Family");
    DLL.addNode("Penthouse");

    top = new JPanel();
    top.setLayout(new GridBagLayout());
    top.setPreferredSize(new Dimension(1680, 131));
    top.setOpaque(false);
    catalogueLogo = new JLabel();
    catalogueLogo.setIcon(catalogueIcon);
    catalogueLogo.setOpaque(false);
    top.add(catalogueLogo);

    left = new JPanel();
    left.setPreferredSize(new Dimension(172, 831));
    left.setLayout(new GridBagLayout());
    left.setOpaque(false);
    previous = new JButton();
    previous.setOpaque(false);
    previous.setBorder(BorderFactory.createEmptyBorder());
    previous.setContentAreaFilled(false);
    previous.setFocusable(false);
    previous.setIcon(leftIcon);
    previous.addActionListener((e) -> {
      c.show(bottom, DLL.prevPanel(DLL.position));
    });
    left.add(previous);

    right = new JPanel();
    right.setPreferredSize(new Dimension(172, 831));
    right.setLayout(new GridBagLayout());
    right.setOpaque(false);
    next = new JButton();
    next.setOpaque(false);
    next.setBorder(BorderFactory.createEmptyBorder());
    next.setContentAreaFilled(false);
    next.setFocusable(false);
    next.setIcon(rightIcon);
    next.addActionListener((e) -> {
      c.show(bottom, DLL.nextPanel(DLL.position));
    });
    right.add(next);

    singleRoom = new JPanel();
    singleRoom.setOpaque(false);
    singleRoom.setLayout(new BorderLayout());
    leftSingle = new JPanel();
    leftSingle.setPreferredSize(new Dimension(770, 613));
    leftSingle.setOpaque(false);
    leftSingle.setLayout(new GridBagLayout());
    singleDescription = new JLabel();
    singleDescription.setOpaque(false);
    singleDescription.setIcon(singleDescriptionIcon);
    leftSingle.add(singleDescription);
    rightSingle = new JPanel();
    rightSingle.setPreferredSize(new Dimension(512, 613));
    rightSingle.setOpaque(false);
    rightSingle.setLayout(new GridLayout(2, 1));
    singleFeature = new JLabel();
    singleFeature.setOpaque(false);
    singleFeature.setIcon(singleFeatureIcon);
    rightSingle.add(singleFeature);
    singleRoom.add(leftSingle, BorderLayout.WEST);
    singleRoom.add(rightSingle, BorderLayout.EAST);

    doubleRoom = new JPanel();
    doubleRoom.setOpaque(false);
    doubleRoom.setLayout(new BorderLayout());
    leftDouble = new JPanel();
    leftDouble.setPreferredSize(new Dimension(730, 618));
    leftDouble.setOpaque(false);
    leftDouble.setLayout(new GridBagLayout());
    doubleDescription = new JLabel();
    doubleDescription.setOpaque(false);
    doubleDescription.setIcon(doubleDescriptionIcon);
    leftDouble.add(doubleDescription);
    rightDouble = new JPanel();
    rightDouble.setPreferredSize(new Dimension(530, 618));
    rightDouble.setOpaque(false);
    rightDouble.setLayout(new GridLayout(2, 1));
    doubleFeature = new JLabel();
    doubleFeature.setOpaque(false);
    doubleFeature.setIcon(doubleFeatureIcon);
    rightDouble.add(doubleFeature);
    doubleRoom.add(leftDouble, BorderLayout.WEST);
    doubleRoom.add(rightDouble, BorderLayout.EAST);

    familyRoom = new JPanel();
    familyRoom.setOpaque(false);
    familyRoom.setLayout(new BorderLayout());
    leftFamily = new JPanel();
    leftFamily.setPreferredSize(new Dimension(768, 591));
    leftFamily.setOpaque(false);
    leftFamily.setLayout(new GridBagLayout());
    familyDescription = new JLabel();
    familyDescription.setOpaque(false);
    familyDescription.setIcon(familyDescriptionIcon);
    leftFamily.add(familyDescription);
    rightFamily = new JPanel();
    rightFamily.setPreferredSize(new Dimension(500, 591));
    rightFamily.setOpaque(false);
    rightFamily.setLayout(new GridLayout(2, 1));
    familyFeature = new JLabel();
    familyFeature.setOpaque(false);
    familyFeature.setIcon(familyFeatureIcon);
    rightFamily.add(familyFeature);
    familyRoom.add(leftFamily, BorderLayout.WEST);
    familyRoom.add(rightFamily, BorderLayout.EAST);

    penthouse = new JPanel();
    penthouse.setOpaque(false);
    penthouse.setLayout(new BorderLayout());
    leftPenthouse = new JPanel();
    leftPenthouse.setPreferredSize(new Dimension(670, 600));
    leftPenthouse.setOpaque(false);
    leftPenthouse.setLayout(new GridBagLayout());
    penthouseDescription = new JLabel();
    penthouseDescription.setOpaque(false);
    penthouseDescription.setIcon(penthouseDescriptionIcon);
    leftPenthouse.add(penthouseDescription);
    rightPenthouse = new JPanel();
    rightPenthouse.setPreferredSize(new Dimension(530, 600));
    rightPenthouse.setOpaque(false);
    rightPenthouse.setLayout(new GridLayout(2, 1));
    penthouseFeature = new JLabel();
    penthouseFeature.setOpaque(false);
    penthouseFeature.setIcon(penthouseFeatureIcon);
    rightPenthouse.add(penthouseFeature);
    penthouse.add(leftPenthouse, BorderLayout.WEST);
    penthouse.add(rightPenthouse, BorderLayout.EAST);

    bottom = new JPanel();
    bottom.setPreferredSize(new Dimension(1680, 700));
    bottom.setOpaque(false);
    bottom.setLayout(c);
    bottom.add(singleRoom, "Single");
    bottom.add(doubleRoom, "Double");
    bottom.add(familyRoom, "Family");
    bottom.add(penthouse, "Penthouse");
    c.show(bottom, "Single");

    LP = new JLayeredPane();
    LP.setOpaque(false);
    LP.setLayout(null);

    beneath = new JPanel();
    beneath.setOpaque(false);
    beneath.setLayout(new BorderLayout());
    beneath.setBounds(0, 0, 1680, 831);
    beneath.add(top, BorderLayout.NORTH);
    beneath.add(bottom, BorderLayout.CENTER);
    beneath.add(left, BorderLayout.WEST);
    beneath.add(right, BorderLayout.EAST);
    LP.add(beneath, Integer.valueOf(0));

    above = new JPanel();
    above.setOpaque(false);
    above.setLayout(null);
    above.setBounds(1000, 620, 520, 320);
    chooseButton = new JButton();
    chooseButton.setOpaque(false);
    chooseButton.setBorder(BorderFactory.createEmptyBorder());
    chooseButton.setContentAreaFilled(false);
    chooseButton.setFocusable(false);
    chooseButton.setIcon(ChooseRoomIcon);
    chooseButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        blackBar.setIcon(Main.iconResizer(blackBarIcon, 514, 5));
        blackBar.setBounds(0, 55, 514, 5);
        above.add(blackBar);
        above.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        above.remove(blackBar);
        above.repaint();
      }
    });
    chooseButton.addActionListener((e) -> {
      user.setRoomChosen(DLL.position);
      BP.booking(user, frame);
    });
    chooseButton.setBounds(0, 0, 514, 47);

    loginButton = new JButton();
    loginButton.setOpaque(false);
    loginButton.setBorder(BorderFactory.createEmptyBorder());
    loginButton.setContentAreaFilled(false);
    loginButton.setFocusable(false);
    loginButton.setIcon(LoginBookIcon);
    loginButton.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseEntered(MouseEvent e) {
        blackBar.setIcon(Main.iconResizer(blackBarIcon, 393, 5));
        blackBar.setBounds(50, 60, 514, 5);
        above.add(blackBar);
        above.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        above.remove(blackBar);
        above.repaint();
      }
    });
    loginButton.addActionListener((e) -> {
      BP.login();
    });
    loginButton.setBounds(50, 0, 393, 59);
    blackBar = new JLabel();
    if (isLogin) {
      above.add(chooseButton);
    } else {
      above.add(loginButton);
    }
    LP.add(above, Integer.valueOf(1));

    this.setBackground(new Color(0xfbe5e5));
    this.setLayout(new BorderLayout());
    this.add(LP);
  }
}

class DoublyLinkList {
  class Node {
    String panelName;
    Node prev;
    Node next;

    public Node(String panelName) {
      this.panelName = panelName;
    }
  }

  private Node head, tail = null;
  private int size = 0;
  String position = "Single";

  public void addNode(String panelName) {
    Node newNode = new Node(panelName);

    if (head == null) {
      head = tail = newNode;
      head.next = null;
      tail.prev = null;
    } else {
      tail.next = newNode;
      newNode.prev = tail;
      tail = newNode;
    }

    tail.next = head;
    head.prev = tail;
    size++;
  }

  public int getSize() {
    return size;
  }

  public String nextPanel(String panelName) {
    Node currrentNode = head;
    while (currrentNode != null) {
      if (currrentNode.panelName.equals(panelName)) {
        if (currrentNode.next != null) {
          return position = currrentNode.next.panelName;
        } else {
          return position = head.panelName;
        }
      }
      currrentNode = currrentNode.next;
    }
    return null;
  }

  public String prevPanel(String panelName) {
    Node currentNode = head;
    while (currentNode != null) {
      if (currentNode.panelName.equals(panelName)) {
        if (currentNode.prev != null) {
          return position = currentNode.prev.panelName;
        } else {
          return position = tail.panelName;
        }
      }
      currentNode = currentNode.next;
    }
    return null;
  }
}
