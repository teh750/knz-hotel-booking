package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.text.DecimalFormat;
import java.util.Calendar;
import java.util.Date;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSpinnerUI;
import javax.sound.sampled.*;

public class Booking extends JPanel {

  private static final DecimalFormat CURRENCY = new DecimalFormat("0.00");

  JLayeredPane LP;
  JPanel beneath, above;

  JPanel top;
  JLabel bookingTitle;

  JPanel bottom;
  JPanel bottomCeiling;
  JPanel bottomCeilingLeft;
  JLabel choiceOf;
  JPanel bottomCeilingRight;
  JLabel HowMany;
  JPanel contentBody;
  JPanel contentLeft;

  JPanel contentRight;
  JButton singleButton;
  JButton doubleButton;
  JButton familyButton;
  JButton penthouseButton;
  JLabel optional;
  JLabel towels;
  SpinnerModel towelsModel = new SpinnerNumberModel(0, 0, 5, 1);
  JSpinner towelSpinner;
  JLabel beds;
  SpinnerModel bedsModel = new SpinnerNumberModel(0, 0, 10, 1);
  JSpinner bedSpinner;
  JLabel breakfast;
  SpinnerModel paxModel = new SpinnerNumberModel(0, 0, 20, 1);
  JSpinner paxSpinner;
  JLabel currentPrice;
  JLabel grossPrice;
  double grossPriceValue;
  JButton paymentButton;

  JLabel checkInDateTitle;
  Calendar calendarIn;
  SpinnerDateModel dateInModel;
  JSpinner dateIn;
  Calendar calendarOut;
  SpinnerDateModel dateOutModel;
  JSpinner dateOut;
  JLabel nights;
  JLabel horizontalBar;
  JLabel description;
  JLabel checkOutDateTitle;

  JButton previous = new JButton();

  ImageIcon bookingTitleIcon = new ImageIcon("Components/Booking/BookingTitle.png");
  ImageIcon ChoiceTitle = new ImageIcon("Components/Booking/ChoiceOf.png");
  ImageIcon HowManyTitle = new ImageIcon("Components/Booking/HowMany.png");

  ImageIcon UnselectedSingleIcon = new ImageIcon("Components/Booking/UnselectedSingle.png");
  ImageIcon selectedSingleIcon = new ImageIcon("Components/Booking/SingleSelected.png");
  ImageIcon UnselectedDoubleIcon = new ImageIcon("Components/Booking/UnselectedDouble.png");
  ImageIcon selectedDoubleIcon = new ImageIcon("Components/Booking/DoubleSelected.png");
  ImageIcon UnselectedFamilyIcon = new ImageIcon("Components/Booking/UnselectedFamily.png");
  ImageIcon selectedFamilyIcon = new ImageIcon("Components/Booking/FamilySelected.png");
  ImageIcon UnselectedPenthouseIcon = new ImageIcon("Components/Booking/UnselectedPenthouse.png");
  ImageIcon selectedPenthouseIcon = new ImageIcon("Components/Booking/PenthouseSelected.png");

  ImageIcon optionalIcon = new ImageIcon("Components/Booking/Optional.png");
  ImageIcon towelsIcon = new ImageIcon("Components/Booking/Towels.png");
  ImageIcon bedsIcon = new ImageIcon("Components/Booking/Beds.png");
  ImageIcon breakfastIcon = new ImageIcon("Components/Booking/Breakfast.png");

  ImageIcon CurrentPriceTitle = new ImageIcon("Components/Booking/CurrentPrice.png");
  ImageIcon UnselectedPaymentIcon = new ImageIcon("Components/Booking/UnselectedPayment.png");
  ImageIcon selectedPaymentIcon = new ImageIcon("Components/Booking/PaymentSelected.png");
  ImageIcon whiteBarIcon = new ImageIcon("Components/whiteBar.png");
  ImageIcon previousIcon = new ImageIcon("Components/Catalogue/left.png");

  ImageIcon singleIcon = new ImageIcon("Components/Booking/Single.png");
  ImageIcon doubleIcon = new ImageIcon("Components/Booking/Double.png");
  ImageIcon familyIcon = new ImageIcon("Components/Booking/Family.png");
  ImageIcon penthouseIcon = new ImageIcon("Components/Booking/Penthouse.png");

  Room room;
  Date current;
  Date In;
  Date Out;

  Booking(BodyPanel BP, User user, JFrame frame) {
    room = new Room(user);
    room.setRoomType(user.getRoomChosen(), user);
    In = room.getDateCheckIn();
    Out = room.getDateCheckOut();
    grossPriceValue = room.getGrossPrice();
    calendarIn = Calendar.getInstance();
    calendarIn.setTime(room.getDateCheckIn());
    dateInModel = new SpinnerDateModel(calendarIn.getTime(), null, null, Calendar.DATE);
    calendarOut = Calendar.getInstance();
    calendarOut.setTime(room.getDateCheckOut());
    dateOutModel = new SpinnerDateModel(calendarOut.getTime(), null, null, Calendar.DATE);

    LP = new JLayeredPane();
    LP.setLayout(null);

    beneath = new JPanel();
    beneath.setOpaque(false);
    beneath.setLayout(new BorderLayout());
    beneath.setBounds(0, 0, 1690, 831);

    top = new JPanel();
    top.setLayout(new GridBagLayout());
    top.setBackground(new Color(0x5865F2));
    top.setPreferredSize(new Dimension(1680, 90));
    bookingTitle = new JLabel();
    bookingTitle.setIcon(bookingTitleIcon);
    top.add(bookingTitle);
    beneath.add(top, BorderLayout.NORTH);

    bottom = new JPanel();
    bottom.setLayout(new BorderLayout());
    // bottom.setBackground(new Color(0x2c3135));
    bottom.setOpaque(false);
    bottom.setPreferredSize(new Dimension(1680, 741));
    bottomCeiling = new JPanel();
    bottomCeiling.setPreferredSize(new Dimension(1680, 60));
    bottomCeiling.setLayout(new GridLayout(1, 2));
    bottomCeiling.setOpaque(false);
    bottomCeilingLeft = new JPanel();
    bottomCeilingLeft.setPreferredSize(new Dimension(840, 60));
    bottomCeilingLeft.setOpaque(false);
    bottomCeilingLeft.setLayout(new GridBagLayout());
    HowMany = new JLabel();
    HowMany.setIcon(HowManyTitle);
    bottomCeilingLeft.add(HowMany);
    bottomCeilingRight = new JPanel();
    bottomCeilingRight.setPreferredSize(new Dimension(840, 60));
    bottomCeilingRight.setOpaque(false);
    bottomCeilingRight.setLayout(new GridBagLayout());
    choiceOf = new JLabel();
    choiceOf.setIcon(ChoiceTitle);
    bottomCeilingRight.add(choiceOf);
    bottomCeiling.add(bottomCeilingLeft);
    bottomCeiling.add(bottomCeilingRight);
    bottom.add(bottomCeiling, BorderLayout.NORTH);

    contentBody = new JPanel();
    contentBody.setOpaque(false);
    contentBody.setPreferredSize(new Dimension(1680, 681));
    contentBody.setLayout(new GridLayout(1, 2));

    contentRight = new JPanel();
    contentRight.setOpaque(false);
    contentRight.setLayout(null);
    singleButton = new JButton();
    singleButton.setOpaque(false);
    singleButton.setBorder(BorderFactory.createEmptyBorder());
    singleButton.setContentAreaFilled(false);
    singleButton.setFocusable(false);
    singleButton.setBounds(80, 0, 333, 70);
    if (user.getRoomChosen().equals("Single")) {
      singleButton.setIcon(selectedSingleIcon);
      singleButton.repaint();
    } else {
      singleButton.setIcon(UnselectedSingleIcon);
      singleButton.repaint();
    }
    singleButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        singleButton.setIcon(selectedSingleIcon);
        singleButton.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        if (!user.getRoomChosen().equals("Single")) {
          singleButton.setIcon(UnselectedSingleIcon);
          singleButton.repaint();
        }
      }
    });
    singleButton.addActionListener((e) -> {
      user.setRoomChosen("Single");
      room.setRoomType(user.getRoomChosen(), user);
      grossPriceValue = room.getGrossPrice();
      grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
      grossPrice.repaint();
      singleButton.setIcon(selectedSingleIcon);
      doubleButton.setIcon(UnselectedDoubleIcon);
      familyButton.setIcon(UnselectedFamilyIcon);
      penthouseButton.setIcon(UnselectedPenthouseIcon);
      descriptionIcon(user);
      description.repaint();
      contentRight.repaint();
    });
    contentRight.add(singleButton);
    doubleButton = new JButton();
    doubleButton.setOpaque(false);
    doubleButton.setBorder(BorderFactory.createEmptyBorder());
    doubleButton.setContentAreaFilled(false);
    doubleButton.setFocusable(false);
    doubleButton.setBounds(450, 0, 333, 70);
    if (user.getRoomChosen().equals("Double")) {
      doubleButton.setIcon(selectedDoubleIcon);
    } else {
      doubleButton.setIcon(UnselectedDoubleIcon);
    }
    doubleButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        doubleButton.setIcon(selectedDoubleIcon);
        doubleButton.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        if (!user.getRoomChosen().equals("Double")) {
          doubleButton.setIcon(UnselectedDoubleIcon);
          doubleButton.repaint();
        }
      }
    });
    doubleButton.addActionListener((e) -> {
      user.setRoomChosen("Double");
      room.setRoomType(user.getRoomChosen(), user);
      grossPriceValue = room.getGrossPrice();
      grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
      grossPrice.repaint();
      doubleButton.setIcon(selectedDoubleIcon);
      singleButton.setIcon(UnselectedSingleIcon);
      familyButton.setIcon(UnselectedFamilyIcon);
      penthouseButton.setIcon(UnselectedPenthouseIcon);
      descriptionIcon(user);
      description.repaint();
      contentRight.repaint();
    });
    contentRight.add(doubleButton);
    familyButton = new JButton();
    familyButton.setOpaque(false);
    familyButton.setBorder(BorderFactory.createEmptyBorder());
    familyButton.setContentAreaFilled(false);
    familyButton.setFocusable(false);
    familyButton.setBounds(80, 90, 333, 70);
    if (user.getRoomChosen().equals("Family")) {
      familyButton.setIcon(selectedFamilyIcon);
    } else {
      familyButton.setIcon(UnselectedFamilyIcon);
    }
    familyButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        familyButton.setIcon(selectedFamilyIcon);
        familyButton.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        if (!user.getRoomChosen().equals("Family")) {
          familyButton.setIcon(UnselectedFamilyIcon);
          familyButton.repaint();
        }
      }
    });
    familyButton.addActionListener((e) -> {
      user.setRoomChosen("Family");
      room.setRoomType(user.getRoomChosen(), user);
      grossPriceValue = room.getGrossPrice();
      grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
      grossPrice.repaint();
      familyButton.setIcon(selectedFamilyIcon);
      singleButton.setIcon(UnselectedSingleIcon);
      doubleButton.setIcon(UnselectedDoubleIcon);
      penthouseButton.setIcon(UnselectedPenthouseIcon);
      descriptionIcon(user);
      description.repaint();
      contentRight.repaint();
    });
    contentRight.add(familyButton);
    penthouseButton = new JButton();
    penthouseButton.setOpaque(false);
    penthouseButton.setBorder(BorderFactory.createEmptyBorder());
    penthouseButton.setContentAreaFilled(false);
    penthouseButton.setFocusable(false);
    penthouseButton.setBounds(450, 90, 333, 70);
    if (user.getRoomChosen().equals("Penthouse")) {
      penthouseButton.setIcon(selectedPenthouseIcon);
    } else {
      penthouseButton.setIcon(UnselectedPenthouseIcon);
    }
    penthouseButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        penthouseButton.setIcon(selectedPenthouseIcon);
        penthouseButton.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        if (!user.getRoomChosen().equals("Penthouse")) {
          penthouseButton.setIcon(UnselectedPenthouseIcon);
          penthouseButton.repaint();
        }
      }
    });
    penthouseButton.addActionListener((e) -> {
      user.setRoomChosen("Penthouse");
      room.setRoomType(user.getRoomChosen(), user);
      grossPriceValue = room.getGrossPrice();
      grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
      grossPrice.repaint();
      penthouseButton.setIcon(selectedPenthouseIcon);
      singleButton.setIcon(UnselectedSingleIcon);
      doubleButton.setIcon(UnselectedDoubleIcon);
      familyButton.setIcon(UnselectedFamilyIcon);
      descriptionIcon(user);
      description.repaint();
      contentRight.repaint();
    });
    contentRight.add(penthouseButton);
    optional = new JLabel();
    optional.setIcon(optionalIcon);
    optional.setBounds(250, 180, 353, 37);
    contentRight.add(optional);
    towels = new JLabel();
    towels.setIcon(towelsIcon);
    towels.setBounds(70, 240, 360, 60);
    contentRight.add(towels);
    beds = new JLabel();
    beds.setIcon(bedsIcon);
    beds.setBounds(70, 320, 323, 64);
    contentRight.add(beds);
    breakfast = new JLabel();
    breakfast.setIcon(breakfastIcon);
    breakfast.setBounds(70, 400, 421, 74);
    contentRight.add(breakfast);
    towelSpinner = new JSpinner(towelsModel);
    towelSpinner.setUI(new customSpinnerUI());
    towelSpinner.setFont(new Font("Arial", Font.PLAIN, 40));
    towelSpinner.setBounds(700, 250, 70, 50);
    towelSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        int towelsQty = (int) towelSpinner.getValue();
        if (towelsQty > 0) {
          room.setAdditionalTowels(true);
        } else {
          room.setAdditionalTowels(false);
        }
        room.setTowels(towelsQty, user);
        grossPriceValue = room.getGrossPrice();
        grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
        grossPrice.repaint();
      }
    });
    contentRight.add(towelSpinner);
    bedSpinner = new JSpinner(bedsModel);
    bedSpinner.setUI(new customSpinnerUI());
    bedSpinner.setFont(new Font("Arial", Font.PLAIN, 40));
    bedSpinner.setBounds(700, 330, 70, 50);
    bedSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        int bedsQty = (int) bedSpinner.getValue();
        if (bedsQty > 0) {
          room.setAdditionalBeds(true, user);
        } else {
          room.setAdditionalBeds(false, user);
        }
        room.setBeds(bedsQty, user);
        grossPriceValue = room.getGrossPrice();
        grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
        grossPrice.repaint();
      }
    });
    contentRight.add(bedSpinner);
    paxSpinner = new JSpinner(paxModel);
    paxSpinner.setUI(new customSpinnerUI());
    paxSpinner.setFont(new Font("Arial", Font.PLAIN, 40));
    paxSpinner.setBounds(700, 410, 70, 50);
    paxSpinner.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        int paxQty = (int) paxSpinner.getValue();
        if (paxQty > 0) {
          room.setBreakfast(true, user);
        } else {
          room.setBreakfast(false, user);
        }
        room.setPax(paxQty, user);
        grossPriceValue = room.getGrossPrice();
        grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
        grossPrice.repaint();
      }
    });
    contentRight.add(paxSpinner);
    currentPrice = new JLabel();
    currentPrice.setIcon(CurrentPriceTitle);
    currentPrice.setBounds(80, 500, 243, 28);
    contentRight.add(currentPrice);
    grossPrice = new JLabel();
    grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
    grossPrice.setFont(new Font("Arial", Font.BOLD, 40));
    grossPrice.setForeground(Color.WHITE);
    grossPrice.setBounds(80, 530, 300, 50);
    contentRight.add(grossPrice);
    paymentButton = new JButton();
    paymentButton.setOpaque(false);
    paymentButton.setBorder(BorderFactory.createEmptyBorder());
    paymentButton.setContentAreaFilled(false);
    paymentButton.setFocusable(false);
    paymentButton.setIcon(UnselectedPaymentIcon);
    paymentButton.setBounds(400, 510, 400, 70);
    paymentButton.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        paymentButton.setIcon(selectedPaymentIcon);
        paymentButton.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        paymentButton.setIcon(UnselectedPaymentIcon);
        paymentButton.repaint();
      }
    });
    paymentButton.addActionListener((e) -> {
      current = new Date();
      room.convertStringToDate(room.getDCIn(), room.getDCOut());
      if (room.getDateCheckIn().compareTo(room.getDateCheckOut()) == 0) {
        errorParseSound();
        JOptionPane.showMessageDialog(this, "Error: Check In Date and Check Out Date cannot be the same",
            "Date Parse Error", JOptionPane.ERROR_MESSAGE);
      } else if (room.getDateCheckIn().compareTo(current) < 0) {
        errorParseSound();
        JOptionPane.showMessageDialog(this, "Error: Cannot set to Today's Date or before", "Date Parse Error",
            JOptionPane.ERROR_MESSAGE);
      } else {
        BP.transaction(user, room, frame);
      }
    });
    contentRight.add(paymentButton);

    contentLeft = new JPanel();
    contentLeft.setOpaque(false);
    contentLeft.setLayout(null);
    checkInDateTitle = new JLabel("Pick a check in date:");
    checkInDateTitle.setFont(new Font("Arial", Font.BOLD, 30));
    checkInDateTitle.setForeground(Color.WHITE);
    checkInDateTitle.setBounds(50, 10, 400, 40);
    contentLeft.add(checkInDateTitle);
    dateIn = new JSpinner(dateInModel);
    dateIn.setUI(new customSpinnerUI());
    dateIn.setFont(new Font("Arial", Font.PLAIN, 35));
    dateIn.setBounds(90, 60, 200, 50);
    dateIn.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        In = (Date) dateIn.getValue();
        if (In.compareTo(Out) < 0) {
          room.convertDateToString(In, Out);
          room.setDCIn(room.getDCIn(), user);
          nights.setText("Total nights: " + room.getNights());
          nights.repaint();
          grossPriceValue = room.getGrossPrice();
          grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
          grossPrice.repaint();
        }
      }
    });
    contentLeft.add(dateIn);
    checkOutDateTitle = new JLabel("Pick a check out date:");
    checkOutDateTitle.setFont(new Font("Arial", Font.BOLD, 30));
    checkOutDateTitle.setForeground(Color.WHITE);
    checkOutDateTitle.setBounds(470, 10, 400, 40);
    contentLeft.add(checkOutDateTitle);
    dateOut = new JSpinner(dateOutModel);
    dateOut.setUI(new customSpinnerUI());
    dateOut.setFont(new Font("Arial", Font.PLAIN, 35));
    dateOut.setBounds(530, 60, 200, 50);
    dateOut.addChangeListener(new ChangeListener() {
      @Override
      public void stateChanged(ChangeEvent e) {
        Out = (Date) dateOut.getValue();
        // System.out.println(Out);
        if (Out.compareTo(In) > 0) {
          room.convertDateToString(In, Out);
          room.setDCOut(room.getDCOut(), user);
          nights.setText("Total nights: " + room.getNights());
          nights.repaint();
          grossPriceValue = room.getGrossPrice();
          grossPrice.setText("RM " + CURRENCY.format(grossPriceValue));
          grossPrice.repaint();
        }
      }
    });
    contentLeft.add(dateOut);
    nights = new JLabel();
    nights.setText("Total nights: " + room.getNights());
    nights.setFont(new Font("Arial", Font.BOLD, 40));
    nights.setForeground(Color.WHITE);
    nights.setBounds(50, 170, 350, 50);
    contentLeft.add(nights);
    horizontalBar = new JLabel();
    horizontalBar.setIcon(Main.iconResizer(whiteBarIcon, 750, 10));
    horizontalBar.setBounds(40, 250, 750, 8);
    // contentLeft.add(horizontalBar);
    description = new JLabel();
    descriptionIcon(user);
    description.setBounds(90, 280, 640, 330);
    contentLeft.add(description);

    contentBody.add(contentLeft);
    contentBody.add(contentRight);

    above = new JPanel();
    above.setOpaque(false);
    above.setLayout(null);
    above.setBounds(0, 0, 200, 200);
    previous = new JButton();
    previous.setOpaque(false);
    previous.setBorder(BorderFactory.createEmptyBorder());
    previous.setContentAreaFilled(false);
    previous.setFocusable(false);
    previous.setIcon(previousIcon);
    previous.setBounds(20, 0, 92, 92);
    previous.addActionListener((e) -> {
      BP.catalogue();
    });
    above.add(previous);

    bottom.add(contentBody, BorderLayout.CENTER);
    beneath.add(bottom, BorderLayout.CENTER);
    LP.add(beneath, Integer.valueOf(0));
    LP.add(above, Integer.valueOf(1));

    this.setLayout(new BorderLayout());
    this.setOpaque(false);
    this.add(LP);
  }

  public void descriptionIcon(User user) {
    if (user.getRoomChosen().equals("Single")) {
      description.setIcon(singleIcon);
    } else if (user.getRoomChosen().equals("Double")) {
      description.setIcon(doubleIcon);
    } else if (user.getRoomChosen().equals("Family")) {
      description.setIcon(familyIcon);
    } else if (user.getRoomChosen().equals("Penthouse")) {
      description.setIcon(penthouseIcon);
    }
  }

  class customSpinnerUI extends BasicSpinnerUI {
    protected JButton createNextButton() {
      // Create a custom "Next" button with larger size
      JButton button = (JButton) super.createNextButton();
      button.setPreferredSize(new Dimension(20, 50)); // Set preferred size for width and height
      return button;
    }

    @Override
    protected JButton createPreviousButton() {
      // Create a custom "Previous" button with larger size
      JButton button = (JButton) super.createPreviousButton();
      button.setPreferredSize(new Dimension(20, 50)); // Set preferred size for width and height
      return button;
    }
  }

  public static void errorParseSound() {
    try {
      File wavFile = new File("Components/Booking/xpErrorSound.wav");
      AudioInputStream xpErrorSound = AudioSystem.getAudioInputStream(wavFile);
      Clip clip = AudioSystem.getClip();
      clip.open(xpErrorSound);
      clip.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
