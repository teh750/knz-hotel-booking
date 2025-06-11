package com.knz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.*;

public class Main {

  File json;
  ObjectMapper mapper = new ObjectMapper();
  HashMap<String, Object> session;
  boolean token;
  String username;
  User blank = new User("blank");

  Main() {
    sessionCheck();
  }

  public static ImageIcon iconResizer(ImageIcon oldIcon, int width, int height) {
    Image transformedImage = oldIcon.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
    ImageIcon newIcon = new ImageIcon(transformedImage);
    return newIcon;
  }

  public void sessionCheck() {
    json = new File("Dependencies/Session-Token.json");

    try {
      session = mapper.readValue(json, new TypeReference<HashMap<String, Object>>() {
      });
      this.token = (boolean) session.get("Token");
      if (token) {
        this.username = (String) session.get("USERNAME");
        User user = new User(username);
        new Homepage(token, user);
      } else {
        new Homepage(token, blank);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void main(String[] args) {
    new Main();
  }
}
