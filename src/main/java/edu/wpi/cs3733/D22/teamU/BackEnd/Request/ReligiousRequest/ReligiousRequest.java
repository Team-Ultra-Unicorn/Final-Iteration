package edu.wpi.cs3733.D22.teamU.BackEnd.Request.ReligiousRequest;

import edu.wpi.cs3733.D22.teamU.BackEnd.Employee.Employee;
import edu.wpi.cs3733.D22.teamU.BackEnd.Request.Request;

public class ReligiousRequest extends Request {

  private String religion;
  private String notes;
  private String employeeName;

  public ReligiousRequest(
      String ID,
      String name,
      String date,
      String time,
      String patient,
      String religion,
      String status,
      String destination,
      Employee employee,
      String notes) {

    this.ID = ID;
    this.name = name;
    this.date = date;
    this.time = time;
    this.patientName = patient;
    this.religion = religion;
    this.status = status;
    this.destination = destination;
    this.employee = employee;
    this.notes = notes;
    this.firstName = employee.getFirstName();
    this.lastName = employee.getLastName();
  }

  public String getPatient() {
    return patientName;
  }

  public void setPatient(String patient) {
    this.patientName = patient;
  }

  public String getReligion() {
    return religion;
  }

  public void setReligion(String religion) {
    this.religion = religion;
  }

  public String getNotes() {
    return notes;
  }

  public void setNotes(String notes) {
    this.notes = notes;
  }

  public String getEmployeeName() {
    return employeeName;
  }

  public void setEmployeeName(String employeeName) {
    this.employeeName = employeeName;
  }
}
