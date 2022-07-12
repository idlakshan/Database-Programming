package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class StudentUpdateFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtNic;

    public void txtStudentId(ActionEvent event) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("select * from Student where student_id=?");
        statement.setObject(1, txtId.getText());

        ResultSet resultSet = statement.executeQuery();

        if (resultSet.next()) {
            txtId.setText(resultSet.getString(1));
            txtName.setText(resultSet.getString(2));
            txtEmail.setText(resultSet.getString(3));
            txtContact.setText(resultSet.getString(4));
            txtAddress.setText(resultSet.getString(5));
            txtNic.setText(resultSet.getString(6));
        } else {
            new Alert(Alert.AlertType.WARNING, "Empty results").show();
        }


    }

    public void btnUpdateOnAction(ActionEvent event) throws SQLException {
        String tempId = txtId.getText();
        String tempName = txtName.getText();
        String tempEmail = txtEmail.getText();
        String tempContact = txtContact.getText();
        String tempAddress = txtAddress.getText();
        String tempNic = txtNic.getText();

        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("update Student set student_name=?,email=?,contact=?,address=?,nic=? where student_id=?");
        statement.setObject(1,tempName);
        statement.setObject(2,tempEmail);
        statement.setObject(3,tempContact);
        statement.setObject(4,tempAddress);
        statement.setObject(5,tempNic);
        statement.setObject(6,tempId);

        if(statement.executeUpdate()>0){
            new Alert(Alert.AlertType.CONFIRMATION,"Updated").show();
        }else{
            new Alert(Alert.AlertType.WARNING,"Try Again").show();
        }


    }
}
