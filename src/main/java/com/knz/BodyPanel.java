package com.knz;

import javax.swing.*;
import java.awt.*;

public class BodyPanel extends JPanel {

  Home home;
  Catalogue catalogue;
  Login login;
  SignUp signUp;
  Profile profile;
  Booking booking;
  Transaction transaction;
  Facilities facilities;
  ContactUs contactUs;
  CardLayout c = new CardLayout();

  BodyPanel(boolean isLogin, User user, JFrame frame) {
    home = new Home(this);
    catalogue = new Catalogue(this, isLogin, user, frame);
    facilities = new Facilities();
    contactUs = new ContactUs();
    login = new Login(this, frame);
    this.setLayout(c);
    this.setPreferredSize(new Dimension(1680, 831));
    this.setOpaque(false);
    this.add(home, "home");
    this.add(catalogue, "catalogue");
    this.add(facilities, "facilities");
    this.add(contactUs, "contact");
    this.add(login, "login");
    c.show(this, "home");
  }

  public void home() {
    c.show(this, "home");
  }

  public void catalogue() {
    c.show(this, "catalogue");
  }

  public void facilities() {
    c.show(this, "facilities");
  }

  public void contact() {
    c.show(this, "contact");
  }

  public void booking(User user, JFrame frame) {
    booking = new Booking(this, user, frame);
    this.add(booking, "booking");
    c.show(this, "booking");
  }

  public void transaction(User user, Room room, JFrame frame) {
    transaction = new Transaction(this, user, room, frame);
    this.add(transaction, "transaction");
    c.show(this, "transaction");
  }

  public void login() {
    c.show(this, "login");
  }

  public void signUp(JFrame frame) {
    signUp = new SignUp(frame);
    this.add(signUp, "signUp");
    c.show(this, "signUp");
  }

  public void profile(User user, JFrame frame) {
    profile = new Profile(user, frame);
    this.add(profile, "profile");
    c.show(this, "profile");
  }

}
