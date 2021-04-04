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
    public Label searchLabel;
    public Label msgErreur;

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

    /**
     * Fills all text fields to show details about a product.
     * If the specified product is null, all text fields are cleared.
     *
     * @param product the product or null
     */
    private void showDetails(Product product) {
        if (product != null) {
            // Fill the labels with info from the product object.
            inputNom.setText(product.getName());
            inputDescription.setText(product.getDescription());
            inputStock.setText(String.valueOf(product.getStock()));
            inputPicture.setText(product.getPicture());
            inputPrice.setText(String.valueOf(product.getPrice()));
            inputCategory.setText(String.valueOf(product.getCategory()));

        } else {
            // product is null, so remove all the text.
            inputNom.setText("");
            inputDescription.setText("");
            inputStock.setText("");
            inputPrice.setText("");
            inputCategory.setText("");
            inputPicture.setText("");
        }
    }

    public void ajouter(ActionEvent actionEvent) {
        searchLabel.setVisible(false);
        searchField.setVisible(false);
        productsList.setVisible(false);
        detailsForm.setVisible(true);
        //stocker nom bouton dans variable :
        nomBouton = ((Button) actionEvent.getSource()).getText();
        //remettre champs input à zéro:
        showDetails(null);
    }

    public void modifier(ActionEvent actionEvent) {

        nomBouton = ((Button) actionEvent.getSource()).getText();
        Product p = productsList.getSelectionModel().getSelectedItem();
        //afficher message erreur si rien de sélectionné dans le tableau :
        if (productsList.getSelectionModel().getSelectedItem() == null) {
            msgErreur.setVisible(true);
        } else {
            msgErreur.setVisible(false);
            searchLabel.setVisible(false);
            searchField.setVisible(false);
            productsList.setVisible(false);
            detailsForm.setVisible(true);
            //remplir champs avec infos:
            showDetails(p);
        }
    }

    public void supprimer(ActionEvent actionEvent) {
        nomBouton = ((Button) actionEvent.getSource()).getText();
        Product p = productsList.getSelectionModel().getSelectedItem();
        //afficher message erreur si rien de sélectionné dans le tableau :
        if (productsList.getSelectionModel().getSelectedItem() == null) {
            msgErreur.setVisible(true);
        } else {
            msgErreur.setVisible(false);
            searchLabel.setVisible(false);
            searchField.setVisible(false);
            productsList.setVisible(false);
            detailsForm.setVisible(true);
            //remplir champs avec infos:
            showDetails(p);
        }
    }

    public void enregistrer(ActionEvent actionEvent) throws SQLException {
        detailsForm.setVisible(false);
        searchLabel.setVisible(true);
        searchField.setVisible(true);
        productsList.setVisible(true);
        ProductDAO repo = new ProductDAO();

        //if clic sur ajouter alors ajout en bdd:
            if (nomBouton.equals("Ajouter")) {

            Product product = new Product();
            product.setName(inputNom.getText());
            product.setDescription(inputDescription.getText());
            product.setStock(Integer.parseInt(inputStock.getText()));
            product.setPrice(Float.parseFloat(inputPrice.getText()));
            product.setPicture(inputPicture.getText());
            product.setCategory(Integer.parseInt(inputCategory.getText()));
            repo.insert(product);
            //mise à jour du tableview pour afficher nvo product :
            model.add(product);
            productsList.setItems(model);

        } else if (nomBouton.equals("Modifier")) {
            Product product = productsList.getSelectionModel().getSelectedItem();
            //modifier dans la bdd :
            product.setName(inputNom.getText());
            product.setDescription(inputDescription.getText());
            product.setStock(Integer.parseInt(inputStock.getText()));
            product.setPrice(Float.parseFloat(inputPrice.getText()));
            product.setPicture(inputPicture.getText());
            product.setCategory(Integer.parseInt(inputCategory.getText()));
            repo.update(product);

            //mise à jour du tableview pour montrer modification :
            int selectedIndex = productsList.getSelectionModel().getSelectedIndex();
            productsList.getItems().set(selectedIndex, product);

        } else if (nomBouton.equals("Supprimer")) {

            Product product = productsList.getSelectionModel().getSelectedItem();
            //supprimer de la bdd :
            repo.delete(product);
            //mise à jour du tableview pour montrer suppression :
            model.remove(product);
            productsList.setItems(model);
        }
    }

    public void annuler(ActionEvent actionEvent) {
        detailsForm.setVisible(false);
        searchLabel.setVisible(true);
        searchField.setVisible(true);
        productsList.setVisible(true);
        inputNom.clear();
        inputDescription.clear();
        inputStock.clear();
        inputCategory.clear();
        inputPrice.clear();
        inputPicture.clear();
    }


}
