package edu.wpi.cs3733.D22.teamU.frontEnd.controllers;

import edu.wpi.cs3733.D22.teamU.frontEnd.Uapp;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.text.Text;

public class EmployeeInfoController extends ServiceController {

  @FXML Text time;
  @FXML Text date;

  private static final SimpleDateFormat sdf3 = new SimpleDateFormat("MM-dd-yyyy HH:mm:ss");

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    handeDateTime();
  }

  private void handeDateTime() {
    Thread timeThread =
        new Thread(
            () -> {
              while (Uapp.running) {
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                String timeStampdate = sdf3.format(timestamp).substring(0, 10);
                String timeStampTime = sdf3.format(timestamp).substring(11);
                time.setText(timeStampTime);
                date.setText(timeStampdate);
              }
            });
    timeThread.start();
    masterThread = timeThread;
  }

  public void toSettings(ActionEvent actionEvent) {
    System.out.println("Going to settings");
  }

  public void logOut(ActionEvent actionEvent) {
    System.out.println("Logging out");
  }

  @Override
  public void addRequest() throws SQLException, IOException {}

  @Override
  public void removeRequest() {}

  @Override
  public void updateRequest() {}
}
