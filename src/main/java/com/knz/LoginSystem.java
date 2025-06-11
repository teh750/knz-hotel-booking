package com.knz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import java.util.*;
import java.io.*;

public class LoginSystem {

  private String username;
  private String password;

  /*
   * 1 = Login Successfully/ New User Signed In Successfully
   * 2 = Wrong Password
   * 3 = Invalid Username/ Username does not exist
   * 4 = For new user/ Username already exists
   */
  private int loginStatus;

  private File json;
  private ObjectMapper mapper;
  private HashMap<String, String> login;

  LoginSystem(String user, String pass) {
    this.username = user;
    this.password = pass;

    mapper = new ObjectMapper();
    json = new File("Dependencies/Keys.json");

    try {
      login = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {
      });
      validate();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  LoginSystem(boolean isNew, String name, String pass) {
    this.username = name;
    this.password = pass;

    mapper = new ObjectMapper();
    json = new File("Dependencies/Keys.json");

    try {
      login = mapper.readValue(json, new TypeReference<HashMap<String, String>>() {
      });
      if (!login.containsKey(this.username)) {
        loginStatus = 1;
        login.put(name, pass);
        mapper.writeValue(json, login);
      } else {
        loginStatus = 4;
      }
    } catch (Exception e) {

    }
  }

  public void validate() {
    if (!login.containsKey(this.username)) {
      loginStatus = 3;
      return;
    }
    if (login.get(this.username).equals(this.password)) {
      loginStatus = 1;
    } else {
      loginStatus = 2;
    }
  }

  public String getUsername() {
    return this.username;
  }

  public int getAuthorization() {
    return loginStatus;
  }

  public void serializeUsername(String name) {
    login.remove(this.username);
    login.put(name, this.password);
    try {
      mapper.writeValue(json, login);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void serializePassword(String pass) {
    login.replace(this.username, pass);
    try {
      mapper.writeValue(json, login);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
