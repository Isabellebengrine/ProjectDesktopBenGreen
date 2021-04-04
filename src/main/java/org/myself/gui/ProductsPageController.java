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
import org.myself.DAL.Product;
import org.myself.DAL.ProductDAO;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
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
    public Label errorName;
    public Label errorDescription;
    public Label errorStock;
    public Label errorPicture;
    public Label errorPrice;
    public Label errorCategory;

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
        clearErrorMsgs();
        //stocker nom bouton dans variable :
        nomBouton = ((Button) actionEvent.getSource()).getText();
        //remettre champs input à zéro:
        showDetails(null);
    }

    public void modifier(ActionEvent actionEvent) {
        clearErrorMsgs();
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
            clearErrorMsgs();
            //remplir champs avec infos:
            showDetails(p);
        }
    }

    public void enregistrer(ActionEvent actionEvent) throws SQLException {
        ProductDAO repo = new ProductDAO();
        //if clic sur ajouter alors ajout en bdd:
        if (nomBouton.equals("Ajouter")) {
            //vérification des champs :
            if(verifName() && verifDescription() && verifStock() && verifPicture() && verifPrice() && verifCategory()) {

                Product product = new Product();
                product.setName(inputNom.getText());
                product.setDescription(inputDescription.getText());
                product.setStock(Integer.parseInt(inputStock.getText()));
                product.setPrice(Float.parseFloat(inputPrice.getText()));
                product.setPicture(inputPicture.getText());
                product.setCategory(Integer.parseInt(inputCategory.getText()));
                repo.insert(product);
                //mise à jour de l'affichage :
                detailsForm.setVisible(false);
                searchLabel.setVisible(true);
                searchField.setVisible(true);
                productsList.setVisible(true);
                //mise à jour du tableview pour afficher nvo product :
                product.setId(repo.findIdByNameAndPicture(product));
                model.add(product);
                productsList.setItems(model);
            }

        } else if (nomBouton.equals("Modifier")) {

            Product product = productsList.getSelectionModel().getSelectedItem();
            product.setId(repo.findIdByNameAndPicture(product));

            //vérification des champs :
            if(verifName() && verifDescription() && verifStock() && verifPicture() && verifPrice() && verifCategory()) {

                //création de l'alerte :
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Modification");
                alert.setHeaderText("Modifier le Produit numéro " + product.getId() + " ?");
                alert.setContentText("Etes-vous s\u00FBr de vouloir modifier ce produit ?");

                //si ok modification de la ligne, sinon rien
                Optional<ButtonType> result = alert.showAndWait();

                //noinspection OptionalGetWithoutIsPresent
                if (result.get() == ButtonType.OK) {
                    //modifier dans la bdd :
                    product.setName(inputNom.getText());
                    product.setDescription(inputDescription.getText());
                    product.setStock(Integer.parseInt(inputStock.getText()));
                    product.setPrice(Float.parseFloat(inputPrice.getText()));
                    product.setPicture(inputPicture.getText());
                    product.setCategory(Integer.parseInt(inputCategory.getText()));
                    repo.update(product);//test 04/04 ok

                    //mise à jour de l'affichage :
                    detailsForm.setVisible(false);
                    searchLabel.setVisible(true);
                    searchField.setVisible(true);
                    productsList.setVisible(true);
                    //mise à jour du tableview pour montrer modification :
                    int selectedIndex = productsList.getSelectionModel().getSelectedIndex();
                    productsList.getItems().set(selectedIndex, product);
                }
            }
        } else if (nomBouton.equals("Supprimer")) {

            Product product = productsList.getSelectionModel().getSelectedItem();
            product.setId(repo.findIdByNameAndPicture(product));

            //creation de la boite d'alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Suppression");
            alert.setHeaderText("Supprimer le Produit numéro " + product.getId() + " : " + inputNom.getText() + " de la catégorie " + inputCategory.getText() + " ?");
            alert.setContentText("Etes-vous sûr de vouloir supprmier ce produit ? (Attention, action irréversible)");

            //si ok suppression de la ligne, sinon rien
            Optional<ButtonType> result = alert.showAndWait();

            //noinspection OptionalGetWithoutIsPresent
            if (result.get() == ButtonType.OK) {

                //supprimer de la bdd :
                repo.delete(product);//test 04/04 ok
                //mise à jour du tableview pour montrer suppression :
                model.remove(product);
                productsList.setItems(model);
            }
        }
    }

    //regex name, description, picture
    public boolean CheckString(String check) {
        return check.matches("^[A-Za-z .]+$");
    }
    //regex picture
    public boolean CheckFileName(String check) {
        return check.matches("^[A-Za-z0-9:./]+$");
    }
    //regex stock, category
    public boolean CheckInt(String check) { return check.matches("^[0-9]+$"); }
    //regex price
    public boolean CheckPrice(String check) {return check.matches("^[0-9.]+$"); }

    //verif regex Name
    public boolean verifName() {
        //si ok
        if (CheckString(inputNom.getText())) {
            errorName.setVisible(false);
            errorName.setText("");
            return true;
            //si pas ok
        } else {
            errorName.setText("Le nom ne doit contenir que des lettres, espaces ou .");
            errorName.setVisible(true);
        }
        return false;
    }
    //verif regex Description
    public boolean verifDescription() {
        //si ok
        if (CheckString(inputDescription.getText())) {
            errorDescription.setVisible(false);
            errorDescription.setText("");
            return true;
            //si pas ok
        } else {
            errorDescription.setText("La description ne doit contenir que des lettres, espaces ou .");
            errorDescription.setVisible(true);
        }
        return false;
    }
    //verif regex stock
    public boolean verifStock() {
        //si ok
        if (CheckInt(inputStock.getText())) {
            errorStock.setVisible(false);
            errorStock.setText("");
            return true;
            //si pas ok
        } else {
            errorStock.setText("Le stock est un nombre.");
            errorStock.setVisible(true);
        }
        return false;
    }
    //verif regex Picture
    public boolean verifPicture() {
        //si ok
        if (CheckFileName(inputPicture.getText())) {
            errorPicture.setVisible(false);
            errorPicture.setText("");
            return true;
            //si pas ok
        } else {
            errorPicture.setText("Le nom de la photo ne va pas, revoyez votre saisie");
            errorPicture.setVisible(true);
        }
        return false;
    }
    //verif regex price
    public boolean verifPrice() {
        //si ok
        if (CheckPrice(inputPrice.getText())) {
            errorPrice.setVisible(false);
            errorPrice.setText("");
            return true;
            //si pas ok
        } else {
            errorPrice.setText("Le prix est un nombre (avec . si decimal).");
            errorPrice.setVisible(true);
        }
        return false;
    }
    //verif regex category
    public boolean verifCategory() {
        //si ok
        if (CheckInt(inputCategory.getText())) {
            errorCategory.setVisible(false);
            errorCategory.setText("");
            return true;
            //si pas ok
        } else {
            errorCategory.setText("Ce champ est un nombre.");
            errorCategory.setVisible(true);
        }
        return false;
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
        clearErrorMsgs();
    }

    public void clearErrorMsgs() {
        errorCategory.setText("");
        errorName.setText("");
        errorDescription.setText("");
        errorPicture.setText("");
        errorPrice.setText("");
        errorStock.setText("");
    }

}
