package edu.wpi.cs3733.D22.teamU.frontEnd.controllers;

import edu.wpi.cs3733.D22.teamU.BackEnd.Employee.Employee;
import edu.wpi.cs3733.D22.teamU.BackEnd.Location.Location;
import edu.wpi.cs3733.D22.teamU.BackEnd.Request.Request;
import edu.wpi.cs3733.D22.teamU.BackEnd.Udb;
import edu.wpi.cs3733.D22.teamU.frontEnd.javaFXObjects.ComboBoxAutoComplete;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

public class RequestEditController {
  private Request request;
  private ArrayList<Request> requests;
  private ArrayList<String> fields;
  private Pane activePane;

  @FXML TextField ID;
  @FXML TextField name;
  @FXML TextField patientName;
  @FXML TextField status;
  @FXML ComboBox<Location> locations;
  @FXML ComboBox<Employee> employees;

  @FXML TextField service;
  @FXML TextField notes;
  @FXML TextField notesRelig;
  @FXML DatePicker pickUp;
  @FXML DatePicker dropOff;

  @FXML TextField amount;
  @FXML TextField amountMed;
  @FXML TextField typeOfRequest;
  @FXML TextField priority;
  @FXML TextField labType;
  @FXML TextField description;
  @FXML TextField descriptionSec;
  @FXML TextField lethalForce;
  @FXML TextField typeOfMain;
  @FXML TextField message;
  @FXML TextField messageCom;
  @FXML TextField device;
  @FXML TextField dietRest;
  @FXML TextField addNotes;
  @FXML TextField gifts;
  @FXML TextField religion;
  @FXML TextField toLang;
  @FXML TextField amountLab;

  @FXML StackPane specialFields;
  @FXML Pane religiousFields;
  @FXML Pane medicineFields;
  @FXML Pane labFields;
  @FXML Pane laundryFields;
  @FXML Pane giftFields;
  @FXML Pane equipmentFields;
  @FXML Pane securityFields;
  @FXML Pane compServFields;
  @FXML Pane mealFields;
  @FXML Pane translatorFields;
  @FXML Pane maintenanceFields;

  public void setUp(Request request) {
    this.request = request;
    this.fields = new ArrayList<>();
    try {
      locations.setTooltip(new Tooltip());
      locations.getItems().addAll(Udb.getInstance().locationImpl.locations);
      new ComboBoxAutoComplete<Location>(locations, 650, 290);
      employees.setTooltip(new Tooltip());
      employees.getItems().addAll(Udb.getInstance().EmployeeImpl.hList().values());
      new ComboBoxAutoComplete<Employee>(employees, 675, 380);
    } catch (Exception e) {
      e.printStackTrace();
    }

    switch (request.getClass().getSimpleName()) {
      case "LaundryRequest":
        fields.add("patientName");
        fields.add("ID");
        fields.add("employee");
        fields.add("status");
        fields.add("destination");
        fields.add("pickUpDate");
        fields.add("dropOffDate");
        fields.add("services");
        fields.add("time");
        fields.add("notes");
        activePane = laundryFields;
        break;

      case "EquipRequest":
        fields.add("ID");
        fields.add("name");
        fields.add("amount");
        fields.add("typeOfRequest");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("date");
        fields.add("time");
        fields.add("priority");
        activePane = equipmentFields;
        break;

      case "LabRequest":
        fields.add("ID");
        fields.add("name");
        fields.add("amountLab");
        fields.add("patientName");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("date");
        fields.add("time");
        activePane = labFields;
        break;

      case "MedicineRequest":
        fields.add("ID");
        fields.add("name");
        fields.add("amountMed");
        fields.add("patientName");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("date");
        fields.add("time");
        activePane = medicineFields;
        break;

      case "SecurityRequest":
        fields.add("ID");
        fields.add("name");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("employee");
        fields.add("descriptionOfThreat");
        fields.add("leathalForcePermited");
        fields.add("dates");
        fields.add("time");
        activePane = securityFields;
        break;

      case "MealRequest":
        fields.add("ID");
        fields.add("patientName");
        fields.add("dietRest");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("addNotes");
        fields.add("date");
        fields.add("time");
        activePane = mealFields;
        break;

      case "GiftRequest":
        fields.add("ID");
        fields.add("name");
        fields.add("patientName");
        fields.add("gifts");
        fields.add("message");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("date");
        fields.add("time");
        activePane = giftFields;
        break;

      case "ReligiousRequest":
        fields.add("ID");
        fields.add("name");
        fields.add("date");
        fields.add("time");
        fields.add("patientName");
        fields.add("religion");
        fields.add("status");
        fields.add("destination");
        fields.add("employee");
        fields.add("notesRelig");
        activePane = religiousFields;
        break;

      case "TranslatorRequest":
        fields.add("ID");
        fields.add("patientName");
        fields.add("toLang");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("date");
        fields.add("time");
        activePane = translatorFields;
        break;

      case "MaintenanceRequest":
        fields.add("ID");
        fields.add("name");
        fields.add("status");
        fields.add("destination");
        fields.add("employee");
        fields.add("typeOfMaintenance");
        fields.add("description");
        fields.add("date");
        fields.add("time");
        activePane = maintenanceFields;
        break;

      case "CompServRequest":
        fields.add("ID");
        fields.add("messageCom");
        fields.add("status");
        fields.add("employee");
        fields.add("destination");
        fields.add("date");
        fields.add("time");
        fields.add("device");
        activePane = compServFields;
        break;
    }

    switchPane();

    updateFields();
  }

  // removes request from database
  public void removeRequest() {
    requests.remove(request);
    try {
      Udb.getInstance().remove(request);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    // close pane
  }

  // updates the request
  public void updateRequest() {
    Request newRequest = request;
    for (String field : fields) {
      switch (field) {
        case "patientName":
          newRequest.setPatientName(patientName.getText().trim());
          break;
        case "employee":
          newRequest.setEmployee(employees.getValue());
          break;
        case "status":
          newRequest.setStatus(status.getText().trim());
          break;
        case "destination":
          newRequest.setDestination(locations.getValue().getNodeID());
          break;
        case "pickUpDate":
          newRequest.setPickUpDate(pickUp.getValue().toString());
          break;
        case "dropOffDate":
          newRequest.setDropOffDate(dropOff.getValue().toString());
          break;
        case "services":
          newRequest.setServices(service.getText().trim());
          break;
        case "notes":
          newRequest.setNotes(notes.getText().trim());
          break;
        case "notesRelig":
          newRequest.setNotes(notes.getText().trim());
          break;
        case "name":
          newRequest.setName(name.getText().trim());
          break;
        case "location":
          newRequest.setLocation(locations.getValue());
          break;
        case "amount":
          newRequest.setAmount(Integer.parseInt(amount.getText().trim()));
          break;
        case "amountMed":
          newRequest.setAmount(Integer.parseInt(amountMed.getText().trim()));
          break;

        case "amountLab":
          newRequest.setAmount(Integer.parseInt(amountLab.getText().trim()));
          break;
        case "typeOfRequest":
          newRequest.setTypeOfRequest(typeOfRequest.getText().trim());
          break;
        case "priority":
          newRequest.setPriority(Integer.parseInt(priority.getText().trim()));
          break;
        case "descriptionOfThreat":
          newRequest.setDescriptionOfThreat(descriptionSec.getText().trim());
          break;
        case "leathalForcePermited":
          newRequest.setLeathalForcePermited(lethalForce.getText().trim());
          break;
        case "typeOfMaintenance":
          newRequest.setTypeOfMaintenance(typeOfMain.getText().trim());
          break;
        case "description":
          newRequest.setDescription(description.getText().trim());
          break;
        case "message":
          newRequest.setMessage(message.getText().trim());
          break;
        case "messageCom":
          newRequest.setMessage(messageCom.getText().trim());
          break;
        case "device":
          newRequest.setDevice(device.getText().trim());
          break;
        case "dietRest":
          newRequest.setDietRest(dietRest.getText().trim());
          break;
        case "addNotes":
          newRequest.setAddNotes(addNotes.getText().trim());
          break;
        case "gifts":
          newRequest.setGifts(gifts.getText().trim());
        case "religion":
          newRequest.setReligion(religion.getText().trim());
          break;
        case "toLang":
          newRequest.setToLang(toLang.getText().trim());
          break;
        default:
          break;
      }
    }
    System.out.println(request);

    // close pane?
  }

  // Set fields to edit or remove
  public void updateFields() {
    for (String field : fields) {
      switch (field) {
        case "ID":
          ID.setText(request.getID());
          break;
        case "patientName":
          patientName.setText(request.getPatientName());
          break;
        case "name":
          name.setText(request.getName());
          break;
        case "employee":
          employees.setValue(request.getEmployee());
          break;
        case "status":
          status.setText(request.getStatus());
          break;
        case "destination":
          locations.setValue(request.getLocation());
          break;

        case "pickUpDate":
          pickUp.setValue(LocalDate.parse(request.getPickUpDate()));
          break;
        case "dropOffDate":
          dropOff.setValue(LocalDate.parse(request.getDropOffDate()));
          break;
        case "services":
          service.setText(request.getServices());
          break;
        case "notes":
          notes.setText(request.getNotes());
          break;
        case "notesRelig":
          notesRelig.setText(request.getNotes());
          break;
        case "amount":
          amount.setText(String.valueOf(request.getAmount()));
          break;
        case "amountMed":
          amountMed.setText(String.valueOf(request.getAmount()));
          break;
        case "amountLab":
          amountLab.setText(String.valueOf(request.getAmount()));
          break;
        case "typeOfRequest":
          typeOfRequest.setText(request.getTypeOfRequest());
          break;
        case "priority":
          priority.setText(String.valueOf(request.getPriority()));
          break;
        case "leathalForcePermited":
          lethalForce.setText(request.getLeathalForcePermited());
          break;
        case "typeOfMaintenance":
          typeOfMain.setText(request.getTypeOfMaintenance());
          break;
        case "description":
          description.setText(request.getDescription());
          break;
        case "descriptionOfThreat":
          descriptionSec.setText(request.getDescriptionOfThreat());
          break;

        case "message":
          message.setText(request.getMessage());
          break;
        case "messageCom":
          messageCom.setText(request.getMessage());
          break;
        case "device":
          device.setText(request.getDevice());
          break;
        case "dietRest":
          dietRest.setText(request.getDietRest());
          break;
        case "addNotes":
          addNotes.setText(request.getAddNotes());
          break;
        case "gifts":
          gifts.setText(request.getGifts());
          break;
        case "religion":
          religion.setText(request.getReligion());
          break;
        case "toLang":
          toLang.setText(request.getToLang());
          break;
        default:
          break;
      }
    }
  }

  public Request getRequest() {
    return request;
  }

  public void switchPane() {
    ObservableList<Node> stackNodes = specialFields.getChildren();
    Node active = stackNodes.get(stackNodes.indexOf(activePane));
    for (Node node : stackNodes) {
      node.setVisible(false);
    }
    active.setVisible(true);
  }
}
