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
import org.myself.DAL.Supplier;
import org.myself.DAL.SupplierDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class SuppliersPageController implements Initializable {
    public Label suppliersPageLabel;
    public Button menuButton;
    public TextField searchField;
    public TableView<Supplier> suppliersList;
    public TableColumn<Supplier, Integer> IdColumn;
    public TableColumn<Supplier, String> NameColumn;
    public TableColumn<Supplier, String> AddressColumn;
    public TableColumn<Supplier, String> zipcodeColumn;
    public TableColumn<Supplier, String> CityColumn;
    public TableColumn<Supplier, String> phoneColumn;

    ObservableList<Supplier> model = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        SupplierDAO repo = null;
        try {
            repo = new SupplierDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert repo != null;
        model.addAll(repo.ListAll());
        suppliersList.setEditable(false);

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        AddressColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
        zipcodeColumn.setCellValueFactory(new PropertyValueFactory<>("zipcode"));
        CityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        FilteredList<Supplier> filteredData = new FilteredList<>(model, p -> true);
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
        SortedList<Supplier> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(suppliersList.comparatorProperty());
        suppliersList.setItems(sortedData);
    }

    public void goBackToMenu(ActionEvent actionEvent) throws IOException {
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent menuParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/menu.fxml"));
        // 2. On l'injecte dans la scene:
        Scene menuScene = new Scene(menuParent);
        // 3. On récupère la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(menuScene);
        window.setTitle("Application desktop BenGreen");
        // 4. On affiche le stage:
        window.show();
    }
}
