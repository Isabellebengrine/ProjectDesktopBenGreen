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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import org.myself.DAL.Customer;
import org.myself.DAL.CustomerDAO;
import org.myself.DAL.Product;
import org.myself.DAL.ProductDAO;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ProductsPageController implements Initializable {
    public Label productsPageLabel;
    public Button menuButton;
    public TextField searchField;
    public Button btnAjouter;
    public Button btnModifier;
    public Button btnSupprimer;
    public TableView<Product> productsList;
    public TableColumn<Product, Integer> IdColumn;
    public TableColumn<Product, String> NameColumn;
    public TableColumn<Product, String> DescriptionColumn;
    public TableColumn<Product, Integer> StockColumn;
    public TableColumn<Product, String> PictureColumn;
    public TableColumn<Product, Float> PriceColumn;
    public TableColumn<Product, Integer> CategoryColumn;
    public VBox detailsForm;
    public TextField inputNom;
    public TextField inputDescription;
    public TextField inputStock;
    public TextField inputPicture;
    public TextField inputPrice;
    public TextField inputCategory;
    public Button btnOk;
    public Button btnAnnuler;
    public String nomBouton;

    ObservableList<Product> model = FXCollections.observableArrayList();

    @Override
    public void initialize(URL location, ResourceBundle rb) {
        ProductDAO repo = null;
        try {
            repo = new ProductDAO();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        assert repo != null;
        model.addAll(repo.ListAll());
        productsList.setEditable(false);

        IdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        NameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        DescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        StockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));
        PictureColumn.setCellValueFactory(new PropertyValueFactory<>("picture"));
        PriceColumn.setCellValueFactory(new PropertyValueFactory<>("price"));
        CategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        FilteredList<Product> filteredData = new FilteredList<>(model, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(product -> {
                if (newValue == null || newValue.isEmpty()){
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                if(product.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }
                return false;
            });
        });
        SortedList<Product> sortedData = new SortedList<>(filteredData);
        sortedData.comparatorProperty().bind(productsList.comparatorProperty());
        productsList.setItems(sortedData);
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



    public void enregistrer(ActionEvent actionEvent) {
    }

    public void annuler(ActionEvent actionEvent) {
    }
}
