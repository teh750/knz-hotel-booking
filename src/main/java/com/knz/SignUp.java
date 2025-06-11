package com.knz;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.*;

public class SignUp extends JPanel {

  final Font ARIAL = new Font("Arial", Font.BOLD, 30);
  final LineBorder LB = new LineBorder(Color.BLACK);
  final Color gray = Color.GRAY;
  final Color black = Color.BLACK;

  String usernameParse, passwordParse, confirmParse;

  JPanel northEmpty;
  JPanel southEmpty;
  JPanel eastEmpty;
  JPanel westEmpty;

  JPanel centre;
  JLabel title, blackBar, username, password, confirm, usernameExist, stayLogin, statement;
  JLabel userSpace, passSpace, passNotMatch;
  JTextField usernameField, passwordField, confirmField;
  JCheckBox loginCheck;
  JButton signUp;

  ImageIcon titleIcon = new ImageIcon("Components/SignUp/title.png");
  ImageIcon blackBarIcon = new ImageIcon("Components/selection.png");
  ImageIcon usernameIcon = new ImageIcon("Components/SignUp/Username.png");
  ImageIcon passwordIcon = new ImageIcon("Components/SignUp/password.png");
  ImageIcon confirmIcon = new ImageIcon("Components/SignUp/Confirm.png");
  ImageIcon usernameExistIcon = new ImageIcon("Components/SignUp/UserExist.png");
  ImageIcon userSpaceIcon = new ImageIcon("Components/SignUp/userGotSpace.png");
  ImageIcon passSpaceIcon = new ImageIcon("Components/SignUp/passGotSpace.png");
  ImageIcon passNotMatchingIcon = new ImageIcon("Components/SignUp/passNotMatch.png");
  ImageIcon stayLoginIcon = new ImageIcon("Components/SignUp/stayLogin.png");
  ImageIcon unselectedSignIcon = new ImageIcon("Components/SignUp/UnselectedSignUp.png");
  ImageIcon selectedSignIcon = new ImageIcon("Components/SignUp/selectedSignUp.png");
  ImageIcon statementIcon = new ImageIcon("Components/SignUp/ByClicking.png");
  ImageIcon trueIcon = new ImageIcon("Components/SignUp/true.png");
  ImageIcon falseIcon = new ImageIcon("Components/SignUp/false.png");
  ImageIcon KNZLogo = new ImageIcon("Components/KNZ_logo.png");

  SignUp(JFrame frame) {
    northEmpty = new JPanel();
    northEmpty.setOpaque(false);
    northEmpty.setPreferredSize(new Dimension(1680, 40));

    southEmpty = new JPanel();
    southEmpty.setOpaque(false);
    southEmpty.setPreferredSize(new Dimension(1680, 60));

    eastEmpty = new JPanel();
    eastEmpty.setOpaque(false);
    eastEmpty.setPreferredSize(new Dimension(525, 831));

    westEmpty = new JPanel();
    westEmpty.setOpaque(false);
    westEmpty.setPreferredSize(new Dimension(525, 831));

    centre = new JPanel();
    centre.setBackground(Color.WHITE);
    centre.setLayout(null);
    centre.setPreferredSize(new Dimension(630, 750));
    title = new JLabel();
    title.setIcon(titleIcon);
    title.setBounds(55, 20, 522, 26);
    centre.add(title);
    blackBar = new JLabel();
    blackBar.setIcon(Main.iconResizer(blackBarIcon, 517, 6));
    blackBar.setBounds(55, 60, 517, 6);
    centre.add(blackBar);
    username = new JLabel();
    username.setIcon(usernameIcon);
    username.setBounds(50, 170, 170, 25);
    centre.add(username);
    usernameField = new JTextField("Username");
    usernameField.setFont(ARIAL);
    usernameField.setForeground(gray);
    usernameField.setBorder(LB);
    usernameField.setBounds(48, 200, 520, 40);
    usernameField.addMouseListener(new TextFieldBehaviour(usernameField, "Username", gray, black));
    centre.add(usernameField);
    password = new JLabel();
    password.setIcon(passwordIcon);
    password.setBounds(50, 290, 170, 26);
    centre.add(password);
    passwordField = new JTextField("Password");
    passwordField.setFont(ARIAL);
    passwordField.setForeground(gray);
    passwordField.setBorder(LB);
    passwordField.setBounds(48, 320, 520, 40);
    passwordField.addMouseListener(new TextFieldBehaviour(passwordField, "Password", gray, black));
    centre.add(passwordField);
    confirm = new JLabel();
    confirm.setIcon(confirmIcon);
    confirm.setBounds(50, 410, 320, 26);
    centre.add(confirm);
    confirmField = new JTextField("Confirm Password");
    confirmField.setFont(ARIAL);
    confirmField.setForeground(gray);
    confirmField.setBorder(LB);
    confirmField.setBounds(48, 440, 520, 40);
    confirmField.addMouseListener(new TextFieldBehaviour(confirmField, "Confirm Password", gray, black));
    centre.add(confirmField);
    stayLogin = new JLabel();
    stayLogin.setIcon(stayLoginIcon);
    stayLogin.setBounds(50, 530, 128, 26);
    centre.add(stayLogin);
    loginCheck = new JCheckBox();
    loginCheck.setIcon(falseIcon);
    loginCheck.setSelectedIcon(trueIcon);
    loginCheck.setContentAreaFilled(false);
    loginCheck.setBounds(500, 525, 65, 35);
    centre.add(loginCheck);
    signUp = new JButton();
    signUp.setOpaque(false);
    signUp.setBorder(BorderFactory.createEmptyBorder());
    signUp.setContentAreaFilled(false);
    signUp.setFocusable(false);
    signUp.setIcon(unselectedSignIcon);
    signUp.setBounds(190, 560, 250, 75);
    signUp.addMouseListener(new MouseAdapter() {
      @Override
      public void mouseEntered(MouseEvent e) {
        signUp.setIcon(selectedSignIcon);
        signUp.repaint();
      }

      @Override
      public void mouseExited(MouseEvent e) {
        signUp.setIcon(unselectedSignIcon);
        signUp.repaint();
      }
    });
    signUp.addActionListener((e) -> {
      usernameParse = usernameField.getText();
      passwordParse = passwordField.getText();
      confirmParse = confirmField.getText();
      if (!passwordParse.equals(confirmParse)) {
        centre.add(passNotMatch);
        centre.repaint();
      } else if (usernameParse.matches(".*\\s.*")) {
        centre.remove(usernameExist);
        centre.add(userSpace);
        centre.repaint();
      } else if (passwordParse.matches(".*\\s.*")) {
        centre.add(passSpace);
        centre.repaint();
      } else {
        centre.remove(userSpace);
        centre.remove(passSpace);
        centre.remove(passNotMatch);
        centre.repaint();
        signInValidation(frame);
      }
    });
    centre.add(signUp);
    statement = new JLabel();
    statement.setIcon(statementIcon);
    statement.setBounds(25, 660, 585, 60);
    centre.add(statement);
    usernameExist = new JLabel();
    usernameExist.setIcon(usernameExistIcon);
    usernameExist.setBounds(245, 245, 325, 30);
    userSpace = new JLabel();
    userSpace.setIcon(userSpaceIcon);
    userSpace.setBounds(115, 245, 460, 30);
    passSpace = new JLabel();
    passSpace.setIcon(passSpaceIcon);
    passSpace.setBounds(115, 365, 460, 30);
    passNotMatch = new JLabel();
    passNotMatch.setIcon(passNotMatchingIcon);
    passNotMatch.setBounds(240, 485, 330, 30);

    this.setOpaque(false);
    this.setLayout(new BorderLayout());
    this.add(northEmpty, BorderLayout.NORTH);
    this.add(southEmpty, BorderLayout.SOUTH);
    this.add(eastEmpty, BorderLayout.EAST);
    this.add(westEmpty, BorderLayout.WEST);
    this.add(centre, BorderLayout.CENTER);
  }

  public void signInValidation(JFrame frame) {
    LoginSystem LS = new LoginSystem(true, usernameParse, passwordParse);
    if (LS.getAuthorization() == 4) {
      centre.add(usernameExist);
      centre.repaint();
    } else {
      initUser(frame);
    }
  }

  public void initUser(JFrame frame) {
    User user = new User(usernameParse, passwordParse);
    boolean isLogin = true;
    user.setStayLogin(loginCheck.isSelected());
    JOptionPane.showMessageDialog(frame,
        "Welcome " + usernameParse + "! New Customer are entitled to 5% Discount for First Time Booking",
        "KNZ Hotel Online Services",
        JOptionPane.INFORMATION_MESSAGE, KNZLogo);
    frame.dispose();
    new Homepage(isLogin, user);
  }

  class TextFieldBehaviour extends MouseAdapter {
    private JTextField textField;
    private String placeHolderText;
    private Color placeHolderColor;
    private Color textColor;

    TextFieldBehaviour(JTextField TF, String pText, Color pColor, Color tColor) {
      this.textField = TF;
      this.placeHolderText = pText;
      this.placeHolderColor = pColor;
      this.textColor = tColor;
    }

    @Override
    public void mousePressed(MouseEvent e) {
      this.textField.setForeground(textColor);
      if (textField.getText().equals(placeHolderText)) {
        textField.setText("");
      } else if (textField.getText().isEmpty()) {
        textField.setForeground(placeHolderColor);
        textField.setText(placeHolderText);
      }
      textField.repaint();
    }
  }
}
