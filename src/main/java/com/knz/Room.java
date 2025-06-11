package com.knz;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.io.*;
import java.text.*;

public class Room {

  private ArrayList<Integer> List;

  private Integer roomNumber;
  private String roomType;
  private String DCIn;
  private String DCOut;
  private Date dateCheckIn;
  private Date dateCheckOut;
  private int nights;
  private boolean additionalTowels;
  private int towels;
  private boolean additionalBeds;
  private int beds;
  private boolean breakfast;
  private int pax;
  private double grossPrice;
  private double netPrice;

  private Date currenDate;
  private Date dateNext;

  private File roomJson;
  private HashMap<String, Object> roomData;
  private HashMap<String, Object> defaultRoomData;
  private ObjectMapper mapper = new ObjectMapper();

  private static final DecimalFormat CURRENCY = new DecimalFormat("0.00");

  Room(User user, int index) {
    List = user.getRoomNumbersList();
    this.setroomNumber(List.get(index - 1));
    parseRoom();
  }

  Room(User user) {
    roomData = new HashMap<>();
    this.roomType = user.getRoomChosen();
    this.defaultDates(1);

    String before, after;
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
    before = sdf.format(currenDate);
    after = sdf.format(dateNext);

    this.dateCheckIn = currenDate;
    this.dateCheckOut = dateNext;
    this.DCIn = before;
    this.DCOut = after;

    long timeDifferenceInMS = Math.abs(currenDate.getTime() - dateNext.getTime());
    long dayDifference = TimeUnit.DAYS.convert(timeDifferenceInMS, TimeUnit.MILLISECONDS);
    int defaultNight = (int) dayDifference;

    this.nights = defaultNight;
    this.additionalTowels = false;
    this.towels = 0;
    this.additionalBeds = false;
    this.beds = 0;
    this.breakfast = false;
    this.pax = 0;
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public void defaultDates(int days) {
    this.currenDate = new Date();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(currenDate);
    calendar.add(Calendar.DATE, days);
    dateNext = calendar.getTime();
  }

  public void setroomNumber(Integer i) {
    this.roomNumber = i;
  }

  public void parseRoom() {
    roomJson = new File("Dependencies/Room" + this.roomNumber + ".json");
    try {
      roomData = mapper.readValue(roomJson, new TypeReference<HashMap<String, Object>>() {
      });
      this.convertMapToData();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void convertMapToData() {
    this.roomType = (String) roomData.get("RoomType");
    this.DCIn = (String) roomData.get("DateCheckIn");
    this.DCOut = (String) roomData.get("DateCheckOut");
    convertStringToDate(DCIn, DCOut);
    this.nights = (int) roomData.get("Nights");
    this.additionalTowels = (boolean) roomData.get("AdditionalTowels");
    this.towels = (int) roomData.get("Towels");
    this.additionalBeds = (boolean) roomData.get("AdditionalBeds");
    this.beds = (int) roomData.get("Beds");
    this.breakfast = (boolean) roomData.get("Breakfast");
    this.pax = (int) roomData.get("pax");
    this.netPrice = (double) roomData.get("price");
  }

  public void convertStringToDate(String before, String after) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
      this.dateCheckIn = sdf.parse(before);
      this.dateCheckOut = sdf.parse(after);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void convertDateToString(Date before, Date after) {
    try {
      SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
      this.DCIn = sdf.format(before);
      this.DCOut = sdf.format(after);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public int getNights() {
    convertStringToDate(DCOut, DCIn);
    long timeDiffferenceInMS = Math.abs(this.dateCheckOut.getTime() - this.dateCheckIn.getTime());
    long dayDifference = TimeUnit.DAYS.convert(timeDiffferenceInMS, TimeUnit.MILLISECONDS);
    return this.nights = (int) dayDifference;
  }

  public void setNights(int nights) {
    this.nights = nights;
    this.serialize();
  }

  public double getRoomCost() {
    double cost = 0;
    if (this.roomType.equals("Single")) {
      cost = 150 * this.nights;
    } else if (this.roomType.equals("Double")) {
      cost = 185 * this.nights;
    } else if (this.roomType.equals("Family")) {
      cost = 320 * this.nights;
    } else if (this.roomType.equals("Penthouse")) {
      cost = 588 * this.nights;
    }
    return cost;
  }

  public double getAmenitiesCost() {
    double bedsCost = 0;
    double breakfastCost = 0;
    if (this.additionalBeds == true) {
      bedsCost = 20 * beds;
    }
    if (this.breakfast == true) {
      breakfastCost = 40 * pax;
    }
    return bedsCost + breakfastCost;
  }

  public double getBedsCost() {
    return beds * 20;
  }

  public double getBreakfastCost() {
    return pax * 40;
  }

  public double getGrossPrice() {
    return this.grossPrice = this.getRoomCost() + this.getAmenitiesCost();
  }

  public double getNetPrice(User user) {
    if (!user.getHasBooked()) {
      this.netPrice = this.getGrossPrice() + this.getServiceFee() + this.getServiceTax() - this.getDiscountRate();
    } else {
      this.netPrice = this.getGrossPrice() + this.getServiceFee() + this.getServiceTax();
    }
    return this.netPrice;
  }

  public double getServiceFee() {
    return this.getGrossPrice() * 0.08;
  }

  public double getServiceTax() {
    return this.getGrossPrice() * 0.06;
  }

  public double getDiscountRate() {
    return this.getGrossPrice() * 0.05;
  }

  public void setNetPrice(double netPrice) {
    this.netPrice = netPrice;
    this.serialize();
  }

  public void serialize() {
    roomData.replace("RoomType", this.roomType);
    roomData.replace("DateCheckIn", this.DCIn);
    roomData.replace("DateCheckOut", this.DCOut);
    roomData.replace("Nights", this.nights);
    roomData.replace("AdditionalTowels", this.additionalTowels);
    roomData.replace("Towels", this.towels);
    roomData.replace("AdditionalBeds", this.additionalBeds);
    roomData.replace("Beds", this.beds);
    roomData.replace("Breakfast", this.breakfast);
    roomData.replace("pax", this.pax);
    roomData.replace("price", this.netPrice);
  }

  public void writeJson(User user) {
    List = user.getRoomNumbersList();
    this.setroomNumber(List.get(List.size() - 1));
    roomJson = new File("Dependencies/Room" + this.roomNumber + ".json");
    roomData.put("RoomType", this.roomType);
    roomData.put("DateCheckIn", this.DCIn);
    roomData.put("DateCheckOut", this.DCOut);
    roomData.put("Nights", this.nights);
    roomData.put("AdditionalTowels", this.additionalTowels);
    roomData.put("Towels", this.towels);
    roomData.put("AdditionalBeds", this.additionalBeds);
    roomData.put("Beds", this.beds);
    roomData.put("Breakfast", this.breakfast);
    roomData.put("pax", this.pax);
    roomData.put("price", this.netPrice);

    try {
      mapper.writeValue(roomJson, roomData);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void deleteJson(User user) {
    user.removeRoomNumber(this.roomNumber);
    User.releaseRoomNumber(this.roomNumber);
    roomJson.delete();
  }

  public Integer getRoomNumber() {
    return roomNumber;
  }

  public String getRoomType() {
    return roomType;
  }

  public void setRoomType(String roomType, User user) {
    this.roomType = roomType;
    this.grossPrice = this.getGrossPrice();
    this.netPrice = this.getNetPrice(user);
    this.serialize();
  }

  public String getDCIn() {
    return DCIn;
  }

  public void setDCIn(String dCIn, User user) {
    this.DCIn = dCIn;
    convertStringToDate(DCIn, DCOut);
    this.setNights(getNights());
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public String getDCOut() {
    return DCOut;
  }

  public void setDCOut(String dCOut, User user) {
    this.DCOut = dCOut;
    convertStringToDate(DCIn, DCOut);
    this.setNights(getNights());
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public Date getDateCheckIn() {
    return dateCheckIn;
  }

  public Date getDateCheckOut() {
    return dateCheckOut;
  }

  public boolean isAdditionalTowels() {
    return additionalTowels;
  }

  public void setAdditionalTowels(boolean additionalTowels) {
    this.additionalTowels = additionalTowels;
    this.serialize();
  }

  public int getTowels() {
    return towels;
  }

  public void setTowels(int towels, User user) {
    this.towels = towels;
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public boolean isAdditionalBeds() {
    return additionalBeds;
  }

  public void setAdditionalBeds(boolean additionalBeds, User user) {
    this.additionalBeds = additionalBeds;
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public int getBeds() {
    return beds;
  }

  public void setBeds(int beds, User user) {
    this.beds = beds;
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public boolean isBreakfast() {
    return breakfast;
  }

  public void setBreakfast(boolean breakfast, User user) {
    this.breakfast = breakfast;
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public int getPax() {
    return pax;
  }

  public void setPax(int pax, User user) {
    this.pax = pax;
    this.grossPrice = getGrossPrice();
    this.netPrice = getNetPrice(user);
    this.serialize();
  }

  public void debug() {
    System.out.println(this.roomNumber);
    System.out.println(this.roomType);
    System.out.println(this.DCIn);
    System.out.println(this.DCOut);
    System.out.println(this.dateCheckOut);
    System.out.println(this.dateCheckIn);
    System.out.println(this.nights);
    System.out.println(this.additionalTowels);
    System.out.println(this.towels);
    System.out.println(this.additionalBeds);
    System.out.println(this.beds);
    System.out.println(this.breakfast);
    System.out.println(this.pax);
    System.out.println(this.grossPrice);
    System.out.println(CURRENCY.format(this.netPrice));
  }
}