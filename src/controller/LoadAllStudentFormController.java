package controller;

import db.DBConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.StudentTM;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoadAllStudentFormController {

    public TableView<StudentTM> tblAllStudents;
    public TableColumn colId;
    public TableColumn colName;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colAddress;
    public TableColumn colNic;

    public void initialize(){
        try {

            colId.setCellValueFactory(new PropertyValueFactory<>("id"));
            colName.setCellValueFactory(new PropertyValueFactory<>("name"));
            colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
            colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
            colAddress.setCellValueFactory(new PropertyValueFactory<>("address"));
            colNic.setCellValueFactory(new PropertyValueFactory<>("nic"));


            loadAllStudents();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    public void loadAllStudents() throws SQLException {
        PreparedStatement statement = DBConnection.getInstance().getConnection().prepareStatement("select * from Student");
        ResultSet resultSet = statement.executeQuery();
        ObservableList<StudentTM> observableList= FXCollections.observableArrayList();

        while (resultSet.next()){
            observableList.add(new StudentTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            ));
        }
        tblAllStudents.setItems(observableList);
    }
}
