package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Student;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class StudentSaveFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtNic;

    public void btnSaveOnAction(ActionEvent event) throws SQLException {

        Student s1=new Student(
                txtId.getText(),txtName.getText(),
                txtEmail.getText(),
                txtContact.getText(),
                txtAddress.getText(),
                txtNic.getText()
        );

        if(saveStudent(s1)){
            new Alert(Alert.AlertType.CONFIRMATION,"Saved..").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try again.").show();
        }

    }
    boolean saveStudent(Student s) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("insert into Student values (?,?,?,?,?,?)");
        statement.setObject(1,s.getId());
        statement.setObject(2,s.getName());
        statement.setObject(3,s.getEmail());
        statement.setObject(4,s.getContact());
        statement.setObject(5,s.getAddress());
        statement.setObject(6,s.getNic());

        return statement.executeUpdate()>0;

    }

}
