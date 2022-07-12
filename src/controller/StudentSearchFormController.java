package controller;

import db.DBConnection;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

import java.sql.*;

public class StudentSearchFormController {

    public TextField txtId;
    public TextField txtName;
    public TextField txtEmail;
    public TextField txtContact;
    public TextField txtAddress;
    public TextField txtNic;

    public void txtStudentIdOnAction(ActionEvent event) {
    }

    public void btnSearchOnAction(ActionEvent event) throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("select * from Student where student_id=?");
        statement.setObject(1,txtId.getText());

        ResultSet resultSet = statement.executeQuery();

        if(resultSet.next()){
            txtId.setText(resultSet.getString(1));
            txtName.setText(resultSet.getString(2));
            txtEmail.setText(resultSet.getString(3));
            txtContact.setText(resultSet.getString(4));
            txtAddress.setText(resultSet.getString(5));
            txtNic.setText(resultSet.getString(6));

        }else{
            new Alert(Alert.AlertType.WARNING,"Empty results").show();
        }





    }
}
