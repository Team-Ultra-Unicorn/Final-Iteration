package edu.wpi.cs3733.D22.teamU.frontEnd.services.lab;

public class LabUI {
  private String id;
  private String patientName;
  private String staffName;
  private int activeReqAmount;
  private String labType;
  private String requestDate;
  private String requestTime;
  private String location;

  public String getLocation() {
    return location;
  }

  public void setLocation(String location) {
    this.location = location;
  }

  public int getActiveReqAmount() {
    return activeReqAmount;
  }

  public void setActiveReqAmount(int activeReqAmount) {
    this.activeReqAmount = activeReqAmount;
  }

  public LabUI(
      String id,
      String patientName,
      String staffName,
      int amountOfLabEquipment,
      String labType,
      String location,
      String requestDate,
      String requestTime) {
    this.id = id;
    this.patientName = patientName;
    this.staffName = staffName;
    this.labType = labType;
    this.requestDate = requestDate;
    this.requestTime = requestTime;
    this.activeReqAmount = amountOfLabEquipment;
    this.location = location;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPatientName() {
    return patientName;
  }

  public void setPatientName(String patientName) {
    this.patientName = patientName;
  }

  public String getStaffName() {
    return staffName;
  }

  public void setStaffName(String staffName) {
    this.staffName = staffName;
  }

  public String getLabType() {
    return labType;
  }

  public void setLabType(String labType) {
    this.labType = labType;
  }

  public String getRequestDate() {
    return requestDate;
  }

  public void setRequestDate(String requestDate) {
    this.requestDate = requestDate;
  }

  public String getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(String requestTime) {
    this.requestTime = requestTime;
  }
}
