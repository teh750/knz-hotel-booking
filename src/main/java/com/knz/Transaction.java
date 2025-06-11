package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.io.*;
import javax.sound.sampled.*;

public class Transaction extends JPanel {

  private static final DecimalFormat CURRENCY = new DecimalFormat("0.00");
  final Font ARIAL = new Font("Arial", Font.BOLD, 35);

  private double nativeRoomCost;

  JLayeredPane LP;
  JPanel beneath, above;

  JPanel header;
  JLabel title;

  JPanel content;
  JPanel contentLeft;
  JPanel leftTop;
  JLabel bookingSummary;

  JPanel leftBody;
  JButton previous;
  JLabel goBack;
  JLabel roomType;
  JLabel nights;
  JLabel dateIn;
  JLabel dateOut;
  JLabel additionalTowels;
  JLabel additionalBeds;
  JLabel breakfastEntries;
  JLabel paymentOption;

  JPanel contentRght;
  JPanel rightTop;
  JLabel paymentSummary;

  JPanel rightBody;
  JLabel roomCostTitle;
  JLabel roomCost;
  JLabel nightsTotalTitle;
  JLabel nightsTotal;
  JLabel totalRoomCostTitle;
  JLabel totalRoomCost;
  JLabel blackBar;
  JLabel bedsCostTitle;
  JLabel bedsCost;
  JLabel breakfastCostTitle;
  JLabel breakfastCost;
  JLabel amenitiesCostTitle;
  JLabel amenitiesCost;
  JLabel blackBar2;
  JLabel totalGrossPriceTitle;
  JLabel totalGrossPrice;
  JLabel serviceFeeTitle;
  JLabel serviceFee;
  JLabel serviceTaxTitle;
  JLabel serviceTax;
  JLabel discountGivenTitle;
  JLabel discountGiven;
  JLabel blackBar3;
  JLabel totalNetPriceTitle;
  JLabel totalNetPrice;
  JButton confirmPayment;

  ImageIcon transactionTitleIcon = new ImageIcon("Components/Transaction/mainTitle.png");
  ImageIcon BookingTitleIcon = new ImageIcon("Components/Transaction/BookingSummary.png");
  ImageIcon PaymentTitleIcon = new ImageIcon("Components/Transaction/PaymentSummary.png");
  ImageIcon previousIcon = new ImageIcon("Components/Catalogue/left.png");
  ImageIcon blackBarIcon = new ImageIcon("Components/selection.png");
  ImageIcon paymentOptionIcon = new ImageIcon("Components/Transaction/PaymentOptions.png");
  ImageIcon selectedPaymentIcon = new ImageIcon("Components/Transaction/selectedComfirm.png");
  ImageIcon unselectedPaymentIcon = new ImageIcon("Components/Transaction/UnselectedConfirm.png");
  ImageIcon GreenTickIcon = new ImageIcon("Components/Transaction/GreenTick.png");

  Transaction(BodyPanel BP, User user, Room room, JFrame frame) {
    LP = new JLayeredPane();
    LP.setOpaque(false);
    LP.setLayout(null);

    beneath = new JPanel();
    beneath.setOpaque(false);
    beneath.setLayout(new BorderLayout());
    beneath.setBounds(0, 0, 1680, 831);

    header = new JPanel();
    header.setOpaque(false);
    header.setLayout(new GridBagLayout());
    header.setPreferredSize(new Dimension(1680, 100));
    title = new JLabel();
    title.setIcon(transactionTitleIcon);
    header.add(title);
    beneath.add(header, BorderLayout.NORTH);

    content = new JPanel();
    content.setOpaque(false);
    content.setLayout(new GridLayout(1, 2));
    content.setPreferredSize(new Dimension(1680, 731));

    contentLeft = new JPanel();
    contentLeft.setOpaque(false);
    contentLeft.setLayout(new BorderLayout());
    leftTop = new JPanel();
    leftTop.setOpaque(false);
    leftTop.setLayout(new GridBagLayout());
    leftTop.setPreferredSize(new Dimension(840, 60));
    bookingSummary = new JLabel();
    bookingSummary.setIcon(BookingTitleIcon);
    leftTop.add(bookingSummary);
    leftBody = new JPanel();
    leftBody.setOpaque(false);
    leftBody.setLayout(null);
    leftBody.setPreferredSize(new Dimension(840, 671));
    previous = new JButton();
    previous.setOpaque(false);
    previous.setBorder(BorderFactory.createEmptyBorder());
    previous.setContentAreaFilled(false);
    previous.setFocusable(false);
    previous.setIcon(previousIcon);
    previous.setBounds(20, 0, 92, 92);
    previous.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        leftBody.add(goBack);
        leftBody.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        leftBody.remove(goBack);
        leftBody.repaint();
      }
    });
    previous.addActionListener((e) -> {
      BP.booking(user, frame);
    });
    leftBody.add(previous);
    goBack = new JLabel("Go back to Edit Booking Details");
    goBack.setFont(ARIAL);
    goBack.setBounds(130, 25, 600, 40);
    roomType = new JLabel("Room Type: " + room.getRoomType());
    roomType.setFont(ARIAL);
    roomType.setBounds(180, 100, 600, 50);
    leftBody.add(roomType);
    nights = new JLabel("Nights: " + room.getNights());
    nights.setFont(ARIAL);
    nights.setBounds(260, 140, 600, 50);
    leftBody.add(nights);
    dateIn = new JLabel("Date Check in: " + room.getDCIn());
    dateIn.setFont(ARIAL);
    dateIn.setBounds(140, 180, 600, 50);
    leftBody.add(dateIn);
    dateOut = new JLabel("Date Check out: " + room.getDCOut());
    dateOut.setFont(ARIAL);
    dateOut.setBounds(117, 220, 600, 50);
    leftBody.add(dateOut);
    additionalTowels = new JLabel("Additional Towels: " + room.getTowels());
    additionalTowels.setFont(ARIAL);
    additionalTowels.setBounds(72, 260, 600, 50);
    leftBody.add(additionalTowels);
    additionalBeds = new JLabel("Additional Beds: " + room.getBeds());
    additionalBeds.setFont(ARIAL);
    additionalBeds.setBounds(105, 300, 600, 50);
    leftBody.add(additionalBeds);
    breakfastEntries = new JLabel("Breakfast Entries: " + room.getPax());
    breakfastEntries.setFont(ARIAL);
    breakfastEntries.setBounds(83, 340, 600, 50);
    leftBody.add(breakfastEntries);
    paymentOption = new JLabel();
    paymentOption.setIcon(paymentOptionIcon);
    paymentOption.setBounds(50, 450, 700, 80);
    leftBody.add(paymentOption);
    contentLeft.add(leftTop, BorderLayout.NORTH);
    contentLeft.add(leftBody, BorderLayout.CENTER);
    content.add(contentLeft);

    contentRght = new JPanel();
    contentRght.setOpaque(false);
    contentRght.setLayout(new BorderLayout());
    rightTop = new JPanel();
    rightTop.setOpaque(false);
    rightTop.setLayout(new GridBagLayout());
    rightTop.setPreferredSize(new Dimension(840, 60));
    paymentSummary = new JLabel();
    paymentSummary.setIcon(PaymentTitleIcon);
    rightTop.add(paymentSummary);
    rightBody = new JPanel();
    rightBody.setOpaque(false);
    rightBody.setLayout(null);
    rightBody.setPreferredSize(new Dimension(840, 671));
    roomCostTitle = new JLabel(room.getRoomType() + " Room Cost:");
    roomCostTitle.setFont(ARIAL);
    roomCostTitle.setBounds(20, 20, 800, 50);
    rightBody.add(roomCostTitle);
    roomCost = new JLabel("RM" + CURRENCY.format(getNativeRoonCost(room)));
    roomCost.setFont(ARIAL);
    roomCost.setBounds(620, 20, 240, 50);
    rightBody.add(roomCost);
    nightsTotalTitle = new JLabel("Total Nights:");
    nightsTotalTitle.setFont(ARIAL);
    nightsTotalTitle.setBounds(20, 60, 800, 50);
    rightBody.add(nightsTotalTitle);
    nightsTotal = new JLabel("*" + room.getNights());
    nightsTotal.setFont(ARIAL);
    if (room.getNights() >= 10) {
      nightsTotal.setBounds(725, 60, 240, 50);
    } else {
      nightsTotal.setBounds(745, 60, 240, 50);
    }
    rightBody.add(nightsTotal);
    totalRoomCostTitle = new JLabel("Total Room Cost:");
    totalRoomCostTitle.setFont(ARIAL);
    totalRoomCostTitle.setBounds(20, 100, 800, 50);
    rightBody.add(totalRoomCostTitle);
    totalRoomCost = new JLabel("RM" + CURRENCY.format(room.getRoomCost()));
    totalRoomCost.setFont(ARIAL);
    if (room.getRoomCost() >= 1000) {
      totalRoomCost.setBounds(600, 100, 240, 50);
    } else {
      totalRoomCost.setBounds(620, 100, 240, 50);
    }
    rightBody.add(totalRoomCost);
    blackBar = new JLabel();
    blackBar.setIcon(Main.iconResizer(blackBarIcon, 760, 6));
    blackBar.setBounds(20, 150, 760, 6);
    rightBody.add(blackBar);
    bedsCostTitle = new JLabel("Additional Beds Cost: " + "RM20/Bed");
    bedsCostTitle.setFont(ARIAL);
    bedsCostTitle.setBounds(20, 160, 800, 50);
    rightBody.add(bedsCostTitle);
    bedsCost = new JLabel("RM" + CURRENCY.format(room.getBedsCost()));
    bedsCost.setFont(ARIAL);
    if (room.getBedsCost() <= 0) {
      bedsCost.setBounds(658, 160, 240, 50);
    } else if (room.getBedsCost() <= 100) {
      bedsCost.setBounds(638, 160, 240, 50);
    } else {
      bedsCost.setBounds(620, 160, 240, 50);
    }
    rightBody.add(bedsCost);
    breakfastCostTitle = new JLabel("Breakfast Entries: RM40/Person");
    breakfastCostTitle.setFont(ARIAL);
    breakfastCostTitle.setBounds(20, 200, 600, 50);
    rightBody.add(breakfastCostTitle);
    breakfastCost = new JLabel("RM" + CURRENCY.format(room.getBreakfastCost()));
    breakfastCost.setFont(ARIAL);
    if (room.getBreakfastCost() <= 0) {
      breakfastCost.setBounds(658, 200, 240, 50);
    } else if (room.getBreakfastCost() <= 100) {
      breakfastCost.setBounds(638, 200, 240, 50);
    } else {
      breakfastCost.setBounds(620, 200, 240, 50);
    }
    rightBody.add(breakfastCost);
    amenitiesCostTitle = new JLabel("Total Amenities Cost:");
    amenitiesCostTitle.setFont(ARIAL);
    amenitiesCostTitle.setBounds(20, 240, 800, 50);
    rightBody.add(amenitiesCostTitle);
    amenitiesCost = new JLabel("RM" + CURRENCY.format(room.getAmenitiesCost()));
    amenitiesCost.setFont(ARIAL);
    if (room.getAmenitiesCost() <= 0) {
      amenitiesCost.setBounds(658, 240, 240, 50);
    } else if (room.getAmenitiesCost() <= 100) {
      amenitiesCost.setBounds(638, 240, 240, 50);
    } else {
      amenitiesCost.setBounds(620, 240, 240, 50);
    }
    rightBody.add(amenitiesCost);
    blackBar2 = new JLabel();
    blackBar2.setIcon(Main.iconResizer(blackBarIcon, 760, 6));
    blackBar2.setBounds(20, 290, 760, 6);
    rightBody.add(blackBar2);
    totalGrossPriceTitle = new JLabel("Total Gross Price:");
    totalGrossPriceTitle.setFont(ARIAL);
    totalGrossPriceTitle.setBounds(20, 300, 800, 50);
    rightBody.add(totalGrossPriceTitle);
    totalGrossPrice = new JLabel("RM" + CURRENCY.format(room.getGrossPrice()));
    totalGrossPrice.setFont(ARIAL);
    if (room.getGrossPrice() >= 1000) {
      totalGrossPrice.setBounds(600, 300, 240, 50);
    } else {
      totalGrossPrice.setBounds(620, 300, 240, 50);
    }
    rightBody.add(totalGrossPrice);
    serviceFeeTitle = new JLabel("Service Fee: 8%");
    serviceFeeTitle.setFont(ARIAL);
    serviceFeeTitle.setBounds(20, 340, 800, 50);
    rightBody.add(serviceFeeTitle);
    serviceFee = new JLabel("RM" + CURRENCY.format(room.getServiceFee()));
    serviceFee.setFont(ARIAL);
    if (room.getServiceFee() <= 100) {
      serviceFee.setBounds(638, 340, 240, 50);
    } else if (room.getServiceFee() <= 1000) {
      serviceFee.setBounds(620, 340, 240, 50);
    } else {
      serviceFee.setBounds(600, 340, 240, 50);
    }
    rightBody.add(serviceFee);
    serviceTaxTitle = new JLabel("Service Tax: 6%");
    serviceTaxTitle.setFont(ARIAL);
    serviceTaxTitle.setBounds(20, 380, 800, 50);
    rightBody.add(serviceTaxTitle);
    serviceTax = new JLabel("RM" + CURRENCY.format(room.getServiceTax()));
    serviceTax.setFont(ARIAL);
    if (room.getServiceTax() <= 100) {
      serviceTax.setBounds(638, 380, 240, 50);
    } else if (room.getServiceTax() <= 1000) {
      serviceTax.setBounds(620, 380, 240, 50);
    } else {
      serviceTax.setBounds(600, 380, 240, 50);
    }
    rightBody.add(serviceTax);
    discountGivenTitle = new JLabel();
    if (user.getHasBooked()) {
      discountGivenTitle.setText("No eligible discount");
    } else {
      discountGivenTitle.setText("First Time Booking Discount: (5%)");
    }
    discountGivenTitle.setFont(ARIAL);
    discountGivenTitle.setBounds(20, 420, 800, 50);
    rightBody.add(discountGivenTitle);
    discountGiven = new JLabel();
    if (user.getHasBooked()) {
      discountGiven.setText("");
    } else {
      discountGiven.setText("(RM" + CURRENCY.format(room.getDiscountRate()) + ")");
    }
    discountGiven.setFont(ARIAL);
    if (room.getDiscountRate() <= 100) {
      discountGiven.setBounds(628, 420, 240, 50);
    } else if (room.getDiscountRate() <= 1000) {
      discountGiven.setBounds(610, 420, 240, 50);
    } else {
      discountGiven.setBounds(590, 420, 240, 50);
    }
    rightBody.add(discountGiven);
    blackBar3 = new JLabel();
    blackBar3.setIcon(Main.iconResizer(blackBarIcon, 760, 6));
    blackBar3.setBounds(20, 470, 760, 8);
    rightBody.add(blackBar3);
    totalNetPriceTitle = new JLabel("Total Net Price:");
    totalNetPriceTitle.setFont(ARIAL);
    totalNetPriceTitle.setBounds(20, 480, 800, 50);
    rightBody.add(totalNetPriceTitle);
    totalNetPrice = new JLabel("RM" + CURRENCY.format(room.getNetPrice(user)));
    totalNetPrice.setFont(ARIAL);
    if (room.getNetPrice(user) >= 1000) {
      totalNetPrice.setBounds(600, 480, 240, 50);
    } else {
      totalNetPrice.setBounds(620, 480, 240, 50);
    }
    rightBody.add(totalNetPrice);
    confirmPayment = new JButton();
    confirmPayment.setOpaque(false);
    confirmPayment.setBorder(BorderFactory.createEmptyBorder());
    confirmPayment.setContentAreaFilled(false);
    confirmPayment.setFocusable(false);
    confirmPayment.setBounds(370, 530, 410, 75);
    confirmPayment.setIcon(unselectedPaymentIcon);
    confirmPayment.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        confirmPayment.setIcon(selectedPaymentIcon);
        confirmPayment.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        confirmPayment.setIcon(unselectedPaymentIcon);
        confirmPayment.repaint();
      }
    });
    confirmPayment.addActionListener((e) -> {
      user.addRoomNumber();
      user.setHasBooked(true);
      room.writeJson(user);
      BP.profile(user, frame);
      paymentConfirmNotification();
      JOptionPane.showMessageDialog(this, "Payment Successful! Thank You for Booking with KNZ Hotel!",
          "KNZ Online Services", JOptionPane.INFORMATION_MESSAGE, Main.iconResizer(GreenTickIcon, 50, 50));
    });
    rightBody.add(confirmPayment);
    contentRght.add(rightTop, BorderLayout.NORTH);
    contentRght.add(rightBody, BorderLayout.CENTER);
    content.add(contentRght);

    beneath.add(content);
    LP.add(beneath, Integer.valueOf(0));

    this.setBackground(new Color(0xfae4e4));
    this.setLayout(new BorderLayout());
    this.add(LP);
  }

  public double getNativeRoonCost(Room room) {
    switch (room.getRoomType()) {
      case "Single":
        nativeRoomCost = 150;
        break;
      case "Double":
        nativeRoomCost = 185;
        break;
      case "Family":
        nativeRoomCost = 320;
        break;
      case "Penthouse":
        nativeRoomCost = 588;
        break;
      default:
        nativeRoomCost = 0;
        break;
    }
    return nativeRoomCost;
  }

  public static void paymentConfirmNotification() {
    try {
      File wavFile = new File("Components/Transaction/ApplePay.wav");
      AudioInputStream audioStream = AudioSystem.getAudioInputStream(wavFile);
      Clip ApplePay = AudioSystem.getClip();
      ApplePay.open(audioStream);
      ApplePay.start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
