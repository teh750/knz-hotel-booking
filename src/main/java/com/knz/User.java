package com.knz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.*;
import javax.swing.JFrame;
import java.io.*;

public class User {
  private String Username;
  private String Password;
  private boolean stayLogin;
  private boolean hasBooked;
  private ArrayList<Integer> RoomNumbers;

  private File UserDataFile;
  private HashMap<String, Object> UserData;
  private ObjectMapper mapper;

  private File sessionTokenChange;
  private HashMap<String, Object> sessionToken;

  private String roomChosen = "Single";

  public String getRoomChosen() {
    return roomChosen;
  }

  public void setRoomChosen(String roomChosen) {
    this.roomChosen = roomChosen;
  }

  User(String user) {
    UserDataFile = new File("Dependencies/" + user + ".json");
    mapper = new ObjectMapper();

    try {
      UserData = mapper.readValue(UserDataFile, new TypeReference<HashMap<String, Object>>() {
      });
      if (!user.equals("blank")) {
        parseData();
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  User(String user, String password) {
    UserDataFile = new File("Dependencies/" + user + ".json");
    mapper = new ObjectMapper();
    UserData = new HashMap<>();
    this.Username = user;
    this.Password = password;
    this.stayLogin = false;
    this.hasBooked = false;
    this.RoomNumbers = new ArrayList<>();
    this.serializeNew();
  }

  public void parseData() {
    this.Username = (String) UserData.get("Username");
    this.Password = (String) UserData.get("Password");
    this.stayLogin = (boolean) UserData.get("stayLogin");
    this.hasBooked = (boolean) UserData.get("hasBooked");
    this.RoomNumbers = (ArrayList<Integer>) UserData.get("RoomNumbers");
  }

  public void setUsername(String name, LoginSystem LS) {
    this.Username = name;
    LS.serializeUsername(this.Username);
    this.serialize();
  }

  public void setPassword(String pass, LoginSystem LS) {
    this.Password = pass;
    LS.serializePassword(this.Password);
    this.serialize();
  }

  public void setStayLogin(boolean status) {
    this.stayLogin = status;
    sessionTokenChange = new File("Dependencies/Session-Token.json");
    try {
      sessionToken = mapper.readValue(sessionTokenChange, new TypeReference<HashMap<String, Object>>() {
      });
      sessionToken.replace("Token", this.stayLogin);
      sessionToken.replace("USERNAME", this.Username);
      mapper.writeValue(sessionTokenChange, sessionToken);
    } catch (Exception e) {
      e.printStackTrace();
    }

    this.serialize();
  }

  public void setHasBooked(boolean status) {
    this.hasBooked = status;
    this.serialize();
  }

  public void setRoomNumbersList(ArrayList<Integer> list) {
    this.RoomNumbers = list;
    this.serialize();
  }

  public void addRoomNumber() {
    this.RoomNumbers.add(generateNumber());
    this.serialize();
  }

  public void clearRoomNumbersList() {
    this.RoomNumbers.clear();
    this.serialize();
  }

  public void removeRoomNumber(Integer i) {
    this.RoomNumbers.removeIf(element -> element.equals(i));
    this.serialize();
  }

  public String getUsername() {
    return this.Username;
  }

  public String getPassword() {
    return this.Password;
  }

  public boolean getStayLogin() {
    return this.stayLogin;
  }

  public boolean getHasBooked() {
    return this.hasBooked;
  }

  public ArrayList<Integer> getRoomNumbersList() {
    return this.RoomNumbers;
  }

  public Integer getRoomNumber(int index) {
    return this.RoomNumbers.get(index);
  }

  public void serialize() {
    UserData.replace("Username", this.Username);
    UserData.replace("Password", this.Password);
    UserData.replace("stayLogin", this.stayLogin);
    UserData.replace("hasBooked", this.hasBooked);
    UserData.replace("RoomNumbers", this.RoomNumbers);

    try {
      mapper.writeValue(UserDataFile, UserData);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void serializeNew() {
    UserData.put("Username", this.Username);
    UserData.put("Password", this.Password);
    UserData.put("stayLogin", this.stayLogin);
    UserData.put("hasBooked", this.hasBooked);
    UserData.put("RoomNumbers", this.RoomNumbers);

    try {
      mapper.writeValue(UserDataFile, UserData);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void logout(JFrame frame) {
    this.setStayLogin(false);
    try {
      mapper.writeValue(UserDataFile, UserData);
    } catch (Exception e) {
      e.printStackTrace();
    }
    sessionTokenChange = new File("Dependencies/Session-Token.json");
    try {
      sessionToken = mapper.readValue(sessionTokenChange, new TypeReference<HashMap<String, Object>>() {
      });
      sessionToken.replace("USERNAME", "blank");
      sessionToken.replace("Token", false);
      mapper.writeValue(sessionTokenChange, sessionToken);
    } catch (Exception e) {
      e.printStackTrace();
    }
    frame.dispose();
    new Main();
  }

  public void debug() {
    System.out.println(Username);
    System.out.println(Password);
    System.out.println(stayLogin);
    System.out.println(hasBooked);
    System.out.println(RoomNumbers);
  }

  public Integer generateNumber() {
    Random rand = new Random();
    Integer RoomNumber = rand.nextInt(1, 1000);

    HashMap<String, Object> RoomNumbersMap;
    ArrayList<Integer> RoomNumbersList = new ArrayList<>();
    File RoomNumbersJson = new File("Dependencies/RoomNumbers.json");
    ObjectMapper arrayMapper = new ObjectMapper();
    try {
      RoomNumbersMap = arrayMapper.readValue(RoomNumbersJson, new TypeReference<HashMap<String, Object>>() {
      });
      RoomNumbersList = (ArrayList<Integer>) RoomNumbersMap.get("RoomNumbersList");
      RoomNumbersList.remove(RoomNumber);
      RoomNumbersMap.replace("RoomNumberList", RoomNumbersList);
      mapper.writeValue(RoomNumbersJson, RoomNumbersMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return RoomNumber;
  }

  public static void createRoomNumbersList() {
    HashMap<String, Object> RoomNumbersMap = new HashMap<>();
    ArrayList<Integer> RoomNumbersList = new ArrayList<>(1000);
    for (int i = 0; i < 1000; i++) {
      RoomNumbersList.add(i + 1);
    }
    RoomNumbersMap.put("RoomNumbersList", RoomNumbersList);
    File RoomNumbersJson = new File("Dependencies/RoomNumbers.json");
    ObjectMapper arrayMapper = new ObjectMapper();

    try {
      arrayMapper.writeValue(RoomNumbersJson, RoomNumbersMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public static void releaseRoomNumber(Integer i) {
    HashMap<String, Object> RoomNumbersMap = new HashMap<>();
    ArrayList<Integer> RoomNumbersList = new ArrayList<>(1000);
    File RoomNumbersJson = new File("Dependencies/RoomNumbers.json");
    ObjectMapper arrayMapper = new ObjectMapper();
    try {
      RoomNumbersMap = arrayMapper.readValue(RoomNumbersJson, new TypeReference<HashMap<String, Object>>() {
      });
      RoomNumbersList = (ArrayList<Integer>) RoomNumbersMap.get("RoomNumbersList");
      RoomNumbersList.add(i);
      RoomNumbersList.sort((a, b) -> a.compareTo(b));
      RoomNumbersMap.replace("RoomNumbersList", RoomNumbersList);
      arrayMapper.writeValue(RoomNumbersJson, RoomNumbersMap);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
