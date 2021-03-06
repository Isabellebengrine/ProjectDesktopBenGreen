package org.myself.gui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import org.myself.DAL.Customer;
import org.myself.DAL.CustomerDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Locale;
import java.util.ResourceBundle;

public class CustomersPageController implements Initializable {
    public Label customersPageLabel;
    public Button menuButton;
    public TextField searchField;
    public TableView<Customer> customersList;
    public TableColumn<Customer, Integer> IdColumn;
    public TableColumn<Customer, String> NameColumn;
    public TableColumn<Customer, String> AddressColumn;
    public TableColumn<Customer, String> zipcodeColumn;
    public TableColumn<Customer, String> CityColumn;
    public TableColumn<Customer, String> phoneColumn;

    ObservableList<Customer> model = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        CustomerDAO repo = null;
        try {
            repo = new CustomerDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert repo != null;
        model.addAll(repo.ListAll());
        customersList.setEditable(false);

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        zipcodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        FilteredList<Customer> filteredData = new FilteredList<>(model, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(person.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Customer> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(customersList.comparatorProperty());
        customersList.setItems(sortedData);
    }

    public void goBackToMenu(ActionEvent actionEvent) throws IOException {
        // 1. On cr??e le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent menuParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/menu.fxml"));
        // 2. On l'injecte dans la scene:
        Scene menuScene = new Scene(menuParent);
        // 3. On r??cup??re la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(menuScene);
        window.setTitle("Application desktop BenGreen");
        // 4. On affiche le stage:
        window.show();
    }
}
