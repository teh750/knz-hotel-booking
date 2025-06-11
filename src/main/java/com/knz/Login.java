package com.knz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Login extends JPanel {

  JLayeredPane LP;

  JPanel container;
  JPanel top;
  JLabel knzText;
  JPanel centre;

  JPanel bottom;
  JLayeredPane subLP;
  JPanel loginPanel;
  JLabel loginBorder;
  JPanel loginField;
  JLabel loginText;
  JLabel whitebar;
  JLabel usernameText;
  JTextField fieldUsername;
  JLabel passwordText;
  JTextField fieldPassword;
  JLabel alwaysText;
  JCheckBox loginCheck;
  JButton loginButton;
  JLabel userX;
  JLabel passX;
  JLabel userNotFound;
  JLabel wrongPassword;

  JPanel cover;
  JLabel board;
  JButton signUp;
  JLabel bubble;

  ImageIcon knzIcon = new ImageIcon("Components/KNZ_Online_Services.png");
  ImageIcon loginBorderIcon = new ImageIcon("Components/loginBorder.png");
  ImageIcon loginTextIcon = new ImageIcon("Components/LoginText.png");
  ImageIcon whitebarIcon = new ImageIcon("Components/whiteBar.png");
  ImageIcon usernameTextIcon = new ImageIcon("Components/userText.png");
  ImageIcon passwordTextIcon = new ImageIcon("Components/passText.png");
  ImageIcon alwaysTextIcon = new ImageIcon("Components/always.png");
  ImageIcon loginButtonIcon = new ImageIcon("Components/LoginButton.png");
  ImageIcon boardIcon = new ImageIcon("Components/DontHaveBoard.png");
  ImageIcon makeIcon = new ImageIcon("Components/make_a_account.png");
  ImageIcon bubbleIcon = new ImageIcon("Components/Statement.png");

  ImageIcon trueIcon = new ImageIcon("Components/CheckTrue.png");
  ImageIcon falseIcon = new ImageIcon("Components/CheckFalse.png");

  ImageIcon KNZLogo = new ImageIcon("Components/KNZ_logo.png");
  ImageIcon xIcon = new ImageIcon("Components/ItalicX.png");
  ImageIcon noUserIcon = new ImageIcon("Components/NoUser.png");
  ImageIcon wrongPasswordIcon = new ImageIcon("Components/WrongPassword.png");

  boolean isLogin;
  String usernameParse;
  String passwordParse;
  LoginSystem LS;
  User user;

  Login(BodyPanel BP, JFrame frame) {
    container = new JPanel();
    container.setBounds(0, 0, 1681, 831);
    container.setBackground(new Color(0x090023));
    container.setLayout(new BorderLayout());
    top = new JPanel();
    top.setPreferredSize(new Dimension(1681, 80));
    top.setLayout(new GridBagLayout());
    top.setBackground(new Color(0x4b5aff));
    knzText = new JLabel();
    knzText.setIcon(knzIcon);
    top.add(knzText);

    bottom = new JPanel();
    bottom.setOpaque(false);
    bottom.setLayout(new GridBagLayout());
    subLP = new JLayeredPane();
    subLP.setLayout(null);
    subLP.setPreferredSize(new Dimension(596, 645));
    loginPanel = new JPanel();
    loginPanel.setOpaque(false);
    loginPanel.setLayout(new BorderLayout());
    loginPanel.setBounds(0, 0, 596, 645);
    loginBorder = new JLabel();
    loginBorder.setIcon(Main.iconResizer(loginBorderIcon, 596, 645));
    loginPanel.add(loginBorder);
    loginField = new JPanel();
    loginField.setOpaque(false);
    loginField.setBounds(0, 0, 596, 645);
    loginField.setLayout(null);

    loginText = new JLabel();
    loginText.setIcon(Main.iconResizer(loginTextIcon, 193, 74));
    loginText.setBounds(205, 20, 193, 74);
    whitebar = new JLabel();
    whitebar.setIcon(Main.iconResizer(whitebarIcon, 512, 6));
    whitebar.setBounds(40, 100, 512, 6);
    usernameText = new JLabel();
    usernameText.setIcon(Main.iconResizer(usernameTextIcon, 196, 28));
    usernameText.setBounds(42, 160, 196, 28);
    fieldUsername = new JTextField("Username");
    fieldUsername.setForeground(Color.GRAY);
    fieldUsername.setFont(new Font("Arial", Font.BOLD, 40));
    fieldUsername.setBounds(40, 195, 512, 55);
    fieldUsername.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        fieldUsername.setForeground(Color.BLACK);
        if (fieldUsername.getText().equals("Username")) {
          fieldUsername.setText("");
        } else if (fieldUsername.getText().equals("")) {
          fieldUsername.setForeground(Color.GRAY);
          fieldUsername.setText("Username");
        }
        fieldUsername.repaint();
      }
    });
    passwordText = new JLabel();
    passwordText.setIcon(Main.iconResizer(passwordTextIcon, 187, 28));
    passwordText.setBounds(42, 295, 187, 28);
    fieldPassword = new JTextField("Password");
    fieldPassword.setForeground(Color.GRAY);
    fieldPassword.setFont(new Font("Arial", Font.BOLD, 40));
    fieldPassword.setBounds(40, 330, 512, 55);
    fieldPassword.addMouseListener(new MouseAdapter() {
      @Override
      public void mousePressed(MouseEvent e) {
        fieldPassword.setForeground(Color.BLACK);
        if (fieldPassword.getText().equals("Password")) {
          fieldPassword.setText("");
        } else if (fieldPassword.getText().equals("")) {
          fieldPassword.setForeground(Color.GRAY);
          fieldPassword.setText("Password");
        }
        fieldPassword.repaint();
      }
    });
    alwaysText = new JLabel();
    alwaysText.setIcon(Main.iconResizer(alwaysTextIcon, 328, 30));
    alwaysText.setBounds(42, 430, 328, 30);
    loginCheck = new JCheckBox();
    loginCheck.setIcon(Main.iconResizer(falseIcon, 70, 40));
    loginCheck.setSelectedIcon(Main.iconResizer(trueIcon, 70, 40));
    loginCheck.setContentAreaFilled(false);
    loginCheck.setBounds(480, 423, 75, 40);
    loginButton = new JButton();
    loginButton.setOpaque(false);
    loginButton.setBorder(BorderFactory.createEmptyBorder());
    loginButton.setContentAreaFilled(false);
    loginButton.setFocusable(false);
    loginButton.setIcon(Main.iconResizer(loginButtonIcon, 205, 75));
    loginButton.setBounds(205, 510, 205, 75);
    loginButton.addActionListener((e) -> {

      usernameParse = fieldUsername.getText();
      passwordParse = fieldPassword.getText();
      loginValidation(usernameParse, passwordParse, frame);

    });
    userX = new JLabel();
    userX.setIcon(xIcon);
    userX.setBounds(40, 255, 25, 22);
    userNotFound = new JLabel();
    userNotFound.setIcon(noUserIcon);
    userNotFound.setBounds(70, 255, 334, 21);
    passX = new JLabel();
    passX.setIcon(xIcon);
    passX.setBounds(40, 390, 25, 22);
    wrongPassword = new JLabel();
    wrongPassword.setIcon(wrongPasswordIcon);
    wrongPassword.setBounds(70, 390, 270, 21);

    loginField.add(loginText);
    loginField.add(whitebar);
    loginField.add(usernameText);
    loginField.add(fieldUsername);
    loginField.add(passwordText);
    loginField.add(fieldPassword);
    loginField.add(alwaysText);
    loginField.add(loginCheck);
    loginField.add(loginButton);
    subLP.add(loginPanel, Integer.valueOf(0));
    subLP.add(loginField, Integer.valueOf(1));
    bottom.add(subLP);

    container.add(bottom, BorderLayout.CENTER);
    container.add(top, BorderLayout.NORTH);

    cover = new JPanel();
    cover.setBounds(0, 0, 1680, 831);
    cover.setOpaque(false);
    cover.setLayout(null);
    board = new JLabel();
    board.setIcon(Main.iconResizer(boardIcon, 436, 599));
    board.setBounds(1200, 160, 436, 599);
    signUp = new JButton();
    signUp.setOpaque(false);
    signUp.setBorder(BorderFactory.createEmptyBorder());
    signUp.setContentAreaFilled(false);
    signUp.setFocusable(false);
    signUp.setIcon(Main.iconResizer(makeIcon, 373, 90));
    signUp.setBounds(1230, 440, 373, 90);
    signUp.addActionListener((e) -> {
      BP.signUp(frame);
    });
    bubble = new JLabel();
    bubble.setIcon(Main.iconResizer(bubbleIcon, 488, 302));
    bubble.setBounds(95, 215, 488, 302);
    cover.add(signUp);
    cover.add(board);
    cover.add(bubble);

    LP = new JLayeredPane();
    LP.setLayout(null);
    LP.add(container, Integer.valueOf(0));
    LP.add(cover, Integer.valueOf(1));

    this.setOpaque(false);
    this.setLayout(new BorderLayout());
    this.add(LP);
  }

  public void loginValidation(String name, String pass, JFrame frame) {
    LS = new LoginSystem(name, pass);
    if (LS.getAuthorization() == 1) {
      initUser(name, frame);
    } else if (LS.getAuthorization() == 2) {
      loginField.remove(userX);
      loginField.remove(userNotFound);
      loginField.add(passX);
      loginField.add(wrongPassword);
      loginField.repaint();
    } else if (LS.getAuthorization() == 3) {
      loginField.remove(passX);
      loginField.remove(wrongPassword);
      loginField.add(userX);
      loginField.add(userNotFound);
      loginField.repaint();
    }
  }

  public void initUser(String name, JFrame frame) {
    user = new User(name);
    isLogin = true;
    user.setStayLogin(loginCheck.isSelected());
    JOptionPane.showMessageDialog(frame, "Welcome Back " + name, "KNZ Hotel Online Services",
        JOptionPane.INFORMATION_MESSAGE, KNZLogo);
    frame.dispose();
    new Homepage(isLogin, user);
  }
}
