package edu.wpi.cs3733.D22.teamU.frontEnd.controllers;

import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXHamburger;
import com.jfoenix.controls.JFXTextArea;
import edu.wpi.cs3733.D22.teamU.BackEnd.Employee.Employee;
import edu.wpi.cs3733.D22.teamU.BackEnd.Employee.EmployeeDaoImpl;
import edu.wpi.cs3733.D22.teamU.BackEnd.Location.Location;
import edu.wpi.cs3733.D22.teamU.BackEnd.Request.MedicineRequest.MedicineRequest;
import edu.wpi.cs3733.D22.teamU.BackEnd.Request.MedicineRequest.MedicineRequestDaoImpl;
import edu.wpi.cs3733.D22.teamU.BackEnd.Udb;
import edu.wpi.cs3733.D22.teamU.frontEnd.Uapp;
import edu.wpi.cs3733.D22.teamU.frontEnd.javaFXObjects.ComboBoxAutoComplete;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.SneakyThrows;

public class MedicineDeliveryController extends ServiceController {

  @FXML JFXCheckBox Advil;
  @FXML JFXCheckBox Alprozalam;
  @FXML JFXCheckBox AmphetamineSalt;
  @FXML JFXCheckBox Atorvastatin;
  @FXML JFXCheckBox Lisinopril;
  @FXML JFXCheckBox Metformin;
  @FXML JFXCheckBox specialCheck;
  @FXML Button clearButton;
  @FXML Button submitButton;
  @FXML TextArea specialReq;
  @FXML Button allMedButton;
  @FXML TextField patientName;
  @FXML TextField staffName;
  @FXML TextField advilTxt;
  // @FXML TextField IDtxt;
  // @FXML TextField amount;
  @FXML TextField alproTxt;
  @FXML TextField saltTxt;
  @FXML TextField atorvTxt;
  @FXML TextField lisinTxt;
  @FXML TextField metTxt;
  @FXML TextArea specialReqTxt;
  @FXML Text reset;
  @FXML Text processText;
  @FXML JFXHamburger hamburger;
  @FXML VBox medVbox;
  @FXML VBox nameVbox;
  @FXML VBox vBoxPane;
  @FXML Pane pane;
  @FXML Pane assistPane;
  @FXML AnchorPane bigPane;
  @FXML TabPane tab;
  @FXML TextField destination;

  @FXML Text time;

  @FXML TableColumn<MedicineRequest, String> activeReqID;
  @FXML TableColumn<MedicineRequest, String> activeReqName;
  @FXML TableColumn<MedicineRequest, Integer> activeReqAmount;
  @FXML TableColumn<MedicineRequest, Integer> activeReqPatientName;
  @FXML TableColumn<MedicineRequest, String> activeReqType;
  @FXML TableColumn<MedicineRequest, String> activeReqDestination;
  @FXML TableColumn<MedicineRequest, String> activeDate;
  @FXML TableColumn<MedicineRequest, String> activeReqStatus;
  @FXML TableColumn<MedicineRequest, String> activeReqEmployee;
  @FXML TableColumn<MedicineRequest, String> activeTime;
  @FXML TableColumn<MedicineRequest, Integer> activePriority;

  @FXML TableColumn<MedicineRequest, String> nameCol;
  @FXML TableColumn<MedicineRequest, Integer> inUse;
  @FXML TableColumn<MedicineRequest, Integer> available;
  @FXML TableColumn<MedicineRequest, Integer> total;
  @FXML TableColumn<MedicineRequest, String> location;

  @FXML TableView<MedicineRequest> table;

  @FXML Text requestText;

  @FXML TableView<MedicineRequest> activeRequestTable;
  @FXML VBox requestHolder;

  @FXML StackPane requestsStack;
  @FXML Pane newRequestPane;
  @FXML Pane allMedPane;
  @FXML Pane activeRequestPane;
  @FXML Pane editRequestPane;

  @FXML Button newReqButton;
  @FXML Button activeReqButton;
  @FXML Button allEquipButton;
  public ComboBox<Location> locations;
  public ComboBox<Employee> employees;
  public ComboBox<String> patients;
  ArrayList<Location> nodeIDs;
  @FXML VBox inputFields;

  // edit page
  @FXML Button editMedButton;

  @FXML TableColumn<MedicineRequest, String> ETabMed;
  @FXML TableColumn<MedicineRequest, Integer> ETabAmount;
  @FXML TableColumn<MedicineRequest, String> ETabPatient;
  @FXML TableColumn<MedicineRequest, String> ETabStat;
  @FXML TableView<MedicineRequest> editTable;

  @FXML TextField editID;
  @FXML TextField editMed;
  @FXML TextField editAmount;
  @FXML ComboBox<String> editPatient;
  @FXML TextField editStat;
  public ComboBox<Location> editDest;
  public ComboBox<Employee> editEmployee;

  @FXML Button submitEdit;
  @FXML Button removeButton;

  ArrayList<Employee> staff;
  ArrayList<String> staffID;
  ArrayList<Employee> staffUSER;
  ArrayList<String> patientInput = new ArrayList<String>();

  ObservableList<MedicineRequest> medUIRequests = FXCollections.observableArrayList();
  ObservableList<JFXCheckBox> checkBoxes = FXCollections.observableArrayList();
  ObservableList<JFXTextArea> checkBoxesInput = FXCollections.observableArrayList();

  // Udb udb = DBController.udb;

  private static final SimpleDateFormat sdf3 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

  @SneakyThrows
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    if (Udb.admin) {
      editMedButton.setVisible(true);
    } else {
      editMedButton.setVisible(false);
    }

    // super.initialize(location, resources);
    // udb = Udb.getInstance();
    patientInput.add("Harsh");
    patientInput.add("Marko");
    patientInput.add("Marko");
    patientInput.add("Nick");
    patientInput.add("Kody");
    patientInput.add("Deepti");
    patientInput.add("Joselin");
    patientInput.add("Tim");
    patientInput.add("Will");
    patientInput.add("Mike");
    patientInput.add("Belisha");
    patientInput.add("Iain");

    patients.setTooltip(new Tooltip());
    patients.getItems().addAll(patientInput);
    editPatient.setTooltip(new Tooltip());
    editPatient.getItems().addAll(patientInput);

    setUpAllMed();
    setUpActiveRequests();
    setUpEditTable();
    nodeIDs = new ArrayList<>();
    for (Location l : Udb.getInstance().locationImpl.list()) {
      nodeIDs.add(l);
    }
    locations.setTooltip(new Tooltip());
    locations.getItems().addAll(nodeIDs);
    new ComboBoxAutoComplete<Location>(locations, 650, 290);
    editDest.setTooltip(new Tooltip());
    editDest.getItems().addAll(nodeIDs);
    new ComboBoxAutoComplete<Location>(editDest, 151, 387);

    staff = new ArrayList<>();
    staffUSER = new ArrayList<>();
    for (Employee l : Udb.getInstance().EmployeeImpl.hList().values()) {
      staff.add(l);
      staffUSER.add(l);
    }

    employees.setTooltip(new Tooltip());
    employees.getItems().addAll(staff);
    new ComboBoxAutoComplete<Employee>(employees, 675, 380);
    editEmployee.setTooltip(new Tooltip());
    editEmployee.getItems().addAll(staff);
    new ComboBoxAutoComplete<Employee>(editEmployee, 151, 437);

    for (Node checkBox : requestHolder.getChildren()) {
      checkBoxes.add((JFXCheckBox) checkBox);
    }
    for (Node textArea : inputFields.getChildren()) {
      checkBoxesInput.add((JFXTextArea) textArea);
    }

    for (int i = 0; i < checkBoxesInput.size(); i++) {
      int finalI = i;
      checkBoxesInput
          .get(i)
          .disableProperty()
          .bind(
              Bindings.createBooleanBinding(
                  () -> !checkBoxes.get(finalI).isSelected(),
                  checkBoxes.stream().map(CheckBox::selectedProperty).toArray(Observable[]::new)));
    }
    clearButton
        .disableProperty()
        .bind(
            Bindings.createBooleanBinding(
                () -> checkBoxes.stream().noneMatch(JFXCheckBox::isSelected),
                checkBoxes.stream().map(JFXCheckBox::selectedProperty).toArray(Observable[]::new)));

    submitButton
        .disableProperty()
        .bind(
            Bindings.createBooleanBinding(
                () -> checkBoxes.stream().noneMatch(JFXCheckBox::isSelected),
                checkBoxes.stream().map(JFXCheckBox::selectedProperty).toArray(Observable[]::new)));
    handleTime();

    // BooleanBinding submit =locations.idProperty().isEmpty().and(
    // Bindings.createBooleanBinding(checkBoxes.stream().noneMatch(JFXCheckBox::isSelected)));
    // handleTime();
  }

  private void handleTime() {
    Thread timeThread =
        new Thread(
            () -> {
              while (Uapp.running) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String timeStampTime = sdf3.format(timestamp).substring(11);
                time.setText(timeStampTime);
              }
            });
    timeThread.start();
  }

  private void setUpActiveRequests() throws SQLException, IOException {
    activeReqID.setCellValueFactory(new PropertyValueFactory<>("ID"));
    activeReqName.setCellValueFactory(new PropertyValueFactory<>("name"));
    activeReqAmount.setCellValueFactory(new PropertyValueFactory<>("amount"));
    activeReqPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));
    activeReqStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
    activeReqEmployee.setCellValueFactory(new PropertyValueFactory<>("employee"));
    activeReqDestination.setCellValueFactory(new PropertyValueFactory<>("destination"));
    activeDate.setCellValueFactory(new PropertyValueFactory<>("date"));
    activeTime.setCellValueFactory(new PropertyValueFactory<>("time"));
    activeRequestTable.setItems(getActiveRequestList());
  }

  private void setUpEditTable() throws SQLException, IOException {
    ETabMed.setCellValueFactory(new PropertyValueFactory<MedicineRequest, String>("name"));
    ETabAmount.setCellValueFactory(new PropertyValueFactory<MedicineRequest, Integer>("amount"));
    ETabPatient.setCellValueFactory(
        new PropertyValueFactory<MedicineRequest, String>("patientName"));
    ETabStat.setCellValueFactory(new PropertyValueFactory<MedicineRequest, String>("status"));
    editTable.setItems(getActiveRequestList());
  }

  private ObservableList<MedicineRequest> newRequest(
      String id,
      String name,
      int amount,
      String patientName,
      String status,
      Employee employee,
      String destination,
      String date,
      String time,
      int priority) {
    medUIRequests.add(
        new MedicineRequest(
            id, name, amount, patientName, status, employee, destination, date, time));
    return medUIRequests;
  }

  private void setUpAllMed() throws SQLException, IOException {
    nameCol.setCellValueFactory(new PropertyValueFactory<MedicineRequest, String>("medicineName"));
    inUse.setCellValueFactory(new PropertyValueFactory<MedicineRequest, Integer>("amountInUse"));
    available.setCellValueFactory(
        new PropertyValueFactory<MedicineRequest, Integer>("amountAvailable"));
    total.setCellValueFactory(new PropertyValueFactory<MedicineRequest, Integer>("totalAmount"));
    location.setCellValueFactory(new PropertyValueFactory<MedicineRequest, String>("location"));
    // table.setItems(getEquipmentList());
  }

  private ObservableList<MedicineRequest> getActiveRequestList() throws SQLException, IOException {
    for (MedicineRequest request : MedicineRequestDaoImpl.List.values()) {
      medUIRequests.add(
          new MedicineRequest(
              request.getID(),
              request.getName(),
              request.getAmount(),
              request.getPatientName(),
              request.getStatus(),
              request.getEmployee(),
              request.getDestination(),
              request.getDate(),
              request.getTime()));
    }
    return medUIRequests;
  }

  public void switchToNewRequest(ActionEvent actionEvent) {
    ObservableList<Node> stackNodes = requestsStack.getChildren();
    Node newReq = stackNodes.get(stackNodes.indexOf(newRequestPane));
    for (Node node : stackNodes) {
      node.setVisible(false);
    }
    newReq.setVisible(true);
    newReq.toBack();
    activeReqButton.setUnderline(false);
    newReqButton.setUnderline(true);
    allMedButton.setUnderline(false);
    editMedButton.setUnderline(false);
  }

  public void switchToActive(ActionEvent actionEvent) {
    ObservableList<Node> stackNodes = requestsStack.getChildren();
    Node active = stackNodes.get(stackNodes.indexOf(activeRequestPane));
    for (Node node : stackNodes) {
      node.setVisible(false);
    }
    active.setVisible(true);
    active.toBack();
    activeReqButton.setUnderline(true);
    newReqButton.setUnderline(false);
    allMedButton.setUnderline(false);
    editMedButton.setUnderline(false);
  }

  public void switchToMedicine(ActionEvent actionEvent) {
    ObservableList<Node> stackNodes = requestsStack.getChildren();
    Node active = stackNodes.get(stackNodes.indexOf(allMedPane));
    for (Node node : stackNodes) {
      node.setVisible(false);
    }
    active.setVisible(true);
    active.toBack();
    activeReqButton.setUnderline(false);
    newReqButton.setUnderline(false);
    allMedButton.setUnderline(true);
    editMedButton.setUnderline(false);
  }

  public void switchToEdit(ActionEvent actionEvent) {
    ObservableList<Node> stackNodes = requestsStack.getChildren();
    Node active = stackNodes.get(stackNodes.indexOf(editRequestPane));
    for (Node node : stackNodes) {
      node.setVisible(false);
    }
    active.setVisible(true);
    active.toBack();
    activeReqButton.setUnderline(false);
    newReqButton.setUnderline(false);
    allMedButton.setUnderline(false);
    editMedButton.setUnderline(true);
  }

  public Employee checkEmployee(String employee) throws NullPointerException {
    if (EmployeeDaoImpl.List.get(employee) != null) {
      return EmployeeDaoImpl.List.get(employee);
    } else {
      Employee empty = new Employee("N/A");
      return empty;
    }
  }

  public void mouseHovered(MouseEvent mouseEvent) {
    Button button = (Button) mouseEvent.getSource();
    button.setStyle("-fx-border-color: #E6F6F7");
  }

  public void mouseExit(MouseEvent mouseEvent) {
    Button button = (Button) mouseEvent.getSource();
    button.setStyle("-fx-border-color: transparent");
  }

  @Override
  public void addRequest() {
    StringBuilder startRequestString = new StringBuilder("Your request for : ");

    String endRequest = " has been placed successfully";
    Timestamp timestamp = new Timestamp(System.currentTimeMillis());

    int requestAmount = 0;
    for (int i = 0; i < checkBoxes.size(); i++) {
      if (checkBoxes.get(i).isSelected()) {
        String inputString = "";

        if (checkBoxesInput.get(i).getText().trim().equals("")) {
          inputString = "0";
        } else {
          inputString = checkBoxesInput.get(i).getText().trim();
        }
        String room = locations.getValue().getNodeID();
        String staff = employees.getValue().getEmployeeID();

        requestAmount = Integer.parseInt(inputString);

        startRequestString
            .append(requestAmount)
            .append(" ")
            .append(checkBoxes.get(i).getText())
            .append("(s) to room ")
            .append(room)
            .append(", ");

        double rand = Math.random() * 10000;
        // String patient = "BRUH";

        MedicineRequest request =
            new MedicineRequest(
                (int) rand + "",
                checkBoxes.get(i).getText(),
                requestAmount,
                patients.getValue().toString(),
                "Ordered",
                checkEmployee(employees.getValue().toString()),
                room,
                sdf3.format(timestamp).substring(0, 10),
                sdf3.format(timestamp).substring(11));

        activeRequestTable.setItems(
            newRequest(
                request.getID(),
                request.getName(),
                request.getAmount(),
                request.getPatientName(),
                request.getStatus(),
                request.getEmployee(),
                request.getDestination(),
                request.getDate(),
                request.getTime()));
        try {
          Udb.getInstance()
              .add( // TODO Have random ID and enter Room Destination
                  new MedicineRequest(
                      request.getID(),
                      request.getName(),
                      request.getAmount(),
                      request.getPatientName(),
                      "sent",
                      checkEmployee(staff),
                      request.getDestination(),
                      request.getDate(),
                      request.getTime()));

        } catch (IOException e) {
          e.printStackTrace();
        } catch (SQLException e) {
          e.printStackTrace();
        }
      }
    }

    requestText.setText(startRequestString + endRequest);
    requestText.setVisible(true);
    new Thread(
            () -> {
              try {
                Thread.sleep(3500); // milliseconds
                Platform.runLater(
                    () -> {
                      requestText.setVisible(false);
                    });
              } catch (InterruptedException ie) {
              }
            })
        .start();
  }

  public void enableTxt() {
    if (Advil.isSelected()) {
      advilTxt.setDisable(false);
    }
    if (Alprozalam.isSelected()) {
      alproTxt.setDisable(false);
    }
    if (AmphetamineSalt.isSelected()) {
      saltTxt.setDisable(false);
    }
    if (Atorvastatin.isSelected()) {
      atorvTxt.setDisable(false);
    }
    if (Lisinopril.isSelected()) {
      lisinTxt.setDisable(false);
    }
    if (Metformin.isSelected()) {
      metTxt.setDisable(false);
    }
    if (specialCheck.isSelected()) {
      specialReqTxt.setDisable(false);
    }
  }

  public void clear() {
    Advil.setSelected(false);
    Alprozalam.setSelected(false);
    AmphetamineSalt.setSelected(false);
    Atorvastatin.setSelected(false);
    Lisinopril.setSelected(false);
    Metformin.setSelected(false);
    specialCheck.setSelected(false);
    patientName.setText("");
    staffName.setText("");
    // IDtxt.setText("");
    advilTxt.setText("");
    alproTxt.setText("");
    saltTxt.setText("");
    atorvTxt.setText("");
    lisinTxt.setText("");
    metTxt.setText("");
    specialReqTxt.setText("");
    reset.setText("Cleared requests!");
    reset.setVisible(true);
    new Thread(
            () -> {
              try {
                Thread.sleep(1500); // milliseconds
                Platform.runLater(
                    () -> {
                      reset.setVisible(false);
                    });
              } catch (InterruptedException ie) {
              }
            })
        .start();
  }

  //    lisinTxt.equals("") && metTxt.equals("") && specialReqTxt.equals(""))
  public void reqFields() {
    if (staffName.getText().equals("")
        || patientName.getText().equals("")
        // IDtxt.getText().equals("")
        || (advilTxt.getText().equals("")
                && alproTxt.getText().equals("")
                && saltTxt.getText().equals(""))
            && atorvTxt.getText().equals("")
            && lisinTxt.getText().equals("")
            && metTxt.getText().equals("")
            && specialReqTxt.getText().equals("")) {
      processText.setText("Please fill out all required fields!");
      processText.setVisible(true);
    } else {
      process();
    }
  }

  public void process() {
    processText.setText("Processing...");
    processText.setVisible(true);
    new Thread(
            () -> {
              try {
                Thread.sleep(2000); // milliseconds
                Platform.runLater(
                    () -> {
                      processText.setText(
                          "Staff Name: "
                              + staffName.getText()
                              + "\n"
                              + "Patient Name: "
                              + patientName.getText()
                              + "\n"
                              + "Order ID: "
                              // IDtxt.getText()
                              + "\n"
                              + ""
                              + "\n"
                              + "Medicine Order: "
                              + "\n"
                              + ""
                              + "\n"
                              + "Advil: "
                              + advilTxt.getText()
                              + "\n"
                              + "Alprozalam: "
                              + alproTxt.getText()
                              + "\n"
                              + "Amphetamine Salt: "
                              + saltTxt.getText()
                              + "\n"
                              + "Atorvastatin: "
                              + atorvTxt.getText()
                              + "\n"
                              + "Lisinopril: "
                              + lisinTxt.getText()
                              + "\n"
                              + "Metformin: "
                              + metTxt.getText()
                              + "\n"
                              + "Special Request: "
                              + request());
                    });
              } catch (InterruptedException ie) {
              }
            })
        .start();
  }

  private String request() {
    String request = "";
    if (specialReqTxt.equals("")) {
      request = "No response";
    } else {
      request = specialReqTxt.getText();
    }
    return request;
  }

  public void toMedHelp(ActionEvent actionEvent) throws IOException {
    Scene scene = Uapp.getScene("edu/wpi/cs3733/D22/teamU/views/medHelp.fxml");
    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
    appStage.setScene(scene);
    appStage.show();
  }

  private ObservableList<MedicineRequest> newRequest(
      String id,
      String name,
      int amount,
      String patientName,
      String status,
      Employee employee,
      String location,
      String date,
      String time) {
    medUIRequests.add(
        new MedicineRequest(id, name, amount, patientName, status, employee, location, date, time));
    return medUIRequests;
  }

  @Override
  public void removeRequest() {
    MedicineRequest request = editTable.getSelectionModel().getSelectedItem();
    medUIRequests.remove(request);
    try {
      Udb.getInstance().remove(request);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    clearUpdate();
  }

  @Override
  public void updateRequest() {
    MedicineRequest oldRequest = editTable.getSelectionModel().getSelectedItem();
    medUIRequests.remove(oldRequest);
    String ID = oldRequest.getID();
    String prescription = editMed.getText().trim();
    String patient = editPatient.getValue();
    String oldDate = oldRequest.getDate();
    String oldTime = oldRequest.getTime();
    int requestAmount = Integer.parseInt(editAmount.getText().trim());
    String status = editStat.getText().trim();
    String room = editDest.getValue().getNodeID();
    String staff = editEmployee.getValue().getEmployeeID();

    MedicineRequest request =
        new MedicineRequest(
            ID,
            prescription,
            requestAmount,
            patient,
            status,
            checkEmployee(staff),
            room,
            oldDate,
            oldTime);

    activeRequestTable.setItems(
        newRequest(
            request.getID(),
            request.getName(),
            request.getAmount(),
            request.getPatientName(),
            request.getStatus(),
            request.getEmployee(),
            request.getDestination(),
            request.getDate(),
            request.getTime()));
    try {
      Udb.getInstance().remove(oldRequest);
      Udb.getInstance().add(request);
    } catch (IOException e) {
      e.printStackTrace();
    } catch (SQLException e) {
      e.printStackTrace();
    }
    clearUpdate();
  }

  public void updateFields() {
    MedicineRequest temp = editTable.getSelectionModel().getSelectedItem();
    String amount = String.valueOf(temp.getAmount());
    editID.setText(temp.getID());
    editMed.setText(temp.getName());
    editAmount.setText(amount);
    editPatient.setValue(temp.getPatientName());
    editStat.setText(temp.getStatus());
    editEmployee.setValue(temp.getEmployee());
    editDest.setValue(temp.getLocation());
  }

  public void clearUpdate() {
    editID.setText("");
    editMed.setText("");
    editAmount.setText("");
    editPatient.setValue(null);
    editStat.setText("");
    editEmployee.setValue(null);
    editDest.setValue(null);
  }

  public void clearRequest() {
    for (int i = 0; i < checkBoxes.size(); i++) {
      checkBoxes.get(i).setSelected(false);
      checkBoxesInput.get(i).clear();
    }
    requestText.setText("Cleared Requests!");
    requestText.setVisible(true);
    new Thread(
            () -> {
              try {
                Thread.sleep(1500); // milliseconds
                Platform.runLater(
                    () -> {
                      requestText.setVisible(false);
                    });
              } catch (InterruptedException ie) {
              }
            })
        .start();
  }
}
