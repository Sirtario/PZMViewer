package com.phe.pmzviewer.controller;

import com.phe.pmzviewer.DBConnector;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.util.Callback;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainViewController {

    private DBConnector dbConnector = new DBConnector();

    @FXML
    private TableView TableOneView;

    @FXML
    private TableView TableTwoView;

    @FXML
    private TableView TableThreeView;
    @FXML
    private void onShowTableOneClick() throws SQLException {

        dbConnector.ConnectToDB();

        ResultSet result = getResultSet(dbConnector.getCon(), "Abteilung");

        GenerateTableHead(TableOneView,result);

        FillTableWithContent(TableOneView,result);

        dbConnector.closeConnection();
    }

    @FXML
    private void onShowTableTwoClick() throws SQLException {

        dbConnector.ConnectToDB();

        ResultSet result = getResultSet(dbConnector.getCon(), "Kunde");

        GenerateTableHead(TableTwoView,result);

        FillTableWithContent(TableTwoView,result);

        dbConnector.closeConnection();
    }

    @FXML
    private void onShowTableThreeClick() throws SQLException {

        dbConnector.ConnectToDB();

        ResultSet result = getResultSet(dbConnector.getCon(), "Mitarbeiter");

        GenerateTableHead(TableThreeView,result);

        FillTableWithContent(TableThreeView,result);

        dbConnector.closeConnection();
    }

    private void FillTableWithContent(TableView view, ResultSet result) throws SQLException {
        ObservableList<ObservableList> data = FXCollections.observableArrayList();
        while(result.next()){
            //Iterate Row
            ObservableList<String> row = FXCollections.observableArrayList();
            for(int i = 1; i<= result.getMetaData().getColumnCount(); i++){
                //Iterate Column
                row.add(result.getString(i));
            }
            data.add(row);
        }
        view.setItems(data);
    }

    private void GenerateTableHead(TableView view, ResultSet result) throws SQLException {
        //get columns
        //Stackoverflow: https://stackoverflow.com/questions/18941093/how-to-fill-up-a-tableview-with-database-data
        for (int i = 0; i < result.getMetaData().getColumnCount(); i++) {
            final int j = i;
            TableColumn col = new TableColumn<>(result.getMetaData().getColumnName(i+1));
            col.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList,String>, ObservableValue<String>>(){
                public ObservableValue<String> call(TableColumn.CellDataFeatures<ObservableList, String> param) {
                    return new SimpleStringProperty(param.getValue().get(j).toString());
                }
            });

            view.getColumns().addAll(col);
        }
    }

    private ResultSet getResultSet(Connection connection, String table) {
        ResultSet result = null;
        try {
            Statement statement = connection.createStatement();
            result = statement.executeQuery("SELECT * FROM "+table);
        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        return result;
    }
}