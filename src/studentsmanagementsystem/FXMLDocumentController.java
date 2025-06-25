/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXML2.java to edit this template
 */
package studentsmanagementsystem;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
//import java.sql.*;

/**
 *
 * @author thlavlu
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private TableColumn<Students, Integer> colID;
    @FXML
    private TableColumn<Students, String> colName;
    @FXML
    private TableColumn<Students, String> colDept;
    @FXML
    private TableColumn<Students, Integer> colAge;
    @FXML
    private TableColumn<Students, String> colEmail;
    @FXML
    private TextField tfID;
    @FXML
    private TextField tfName;
    @FXML
    private TextField tfDept;
    @FXML
    private TextField tfAge;
    @FXML
    private TextField tfEmail;
    @FXML
    private Button btnInsert;
    @FXML
    private Button btnUpdate;
    @FXML
    private Button btnDelete;
    @FXML
    private TableView<Students> tvStudents;

    @FXML
    private void handleButtonAction(ActionEvent event) {
        System.out.println("You clicked me!");
        if (event.getSource() == btnInsert) {
            instert();
            System.out.println("Data inserted");
        } else if (event.getSource() == btnUpdate) {
            update();
            System.out.println("Data updated");
        } else if (event.getSource() == btnDelete) {
            delete();
            System.out.println("Data deleted");
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        showStudents();
    }
    public Connection getConnection() {
        Connection conn;
        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/university", "root", "");
             System.out.println("DB Conneceted");
            return conn;
          
        } catch (Exception e) {
            System.out.println("DB not Conneceted");
            System.err.println("Error: " + e.getMessage());
            return null;
        }
    }
    public ObservableList<Students> getStudentList() {
        ObservableList<Students> studentList = FXCollections.observableArrayList();
        Connection conn = getConnection();

        String query = "SELECT * FROM students";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Students student = null;
            while (rs.next()) {
                student = new Students(rs.getInt("id"), rs.getString("name"), rs.getString("dept"), rs.getInt("age"), rs.getString("email"));
                studentList.add(student);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return studentList;
    }

    public void showStudents() {
        ObservableList<Students> list = getStudentList();
        colID.setCellValueFactory(new PropertyValueFactory<Students, Integer>("id"));
        colName.setCellValueFactory(new PropertyValueFactory<Students, String>("name"));
        colDept.setCellValueFactory(new PropertyValueFactory<Students, String>("dept"));
        colAge.setCellValueFactory(new PropertyValueFactory<Students, Integer>("age"));
        colEmail.setCellValueFactory(new PropertyValueFactory<Students, String>("email"));
        tvStudents.setItems(list);
    }

    public void executeQuery(String query) {
        Connection conn = getConnection();

        Statement st;
        try {
            st = conn.createStatement();
            st.executeUpdate(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void instert() {
        String query = "INSERT INTO students VALUES(" + tfID.getText() + ",'" + tfName.getText() + "','" + tfDept.getText() + "'," + tfAge.getText() + ",'" + tfEmail.getText() + "')";
        executeQuery(query);
        showStudents();
    }

    public void update() {
        String query = "UPDATE students SET id = " + tfID.getText() + ",name = '" + tfName.getText() + "',dept='" + tfDept.getText() + "',age = " + tfAge.getText() + ",email = '" + tfEmail.getText() + "' WHERE id = " + tfID.getText() + "";

        executeQuery(query);
        showStudents();
    }

    public void delete() {
        String query = "DELETE FROM students WHERE id = " + tfID.getText() + "";
        executeQuery(query);
        showStudents();

    }

    @FXML
    private void onMouseClick(MouseEvent event) {
        Students students = tvStudents.getSelectionModel().getSelectedItem();
        tfID.setText(""+students.getId());
        tfName.setText(""+students.getName());
        tfDept.setText(""+students.getDept());
        tfAge.setText(""+students.getAge());
        tfEmail.setText(""+students.getEmail());
        
    }

}
