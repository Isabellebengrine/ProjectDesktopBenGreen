package org.myself.gui;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MenuController {
    public Label titleLabel;
    public Button dashboardButton;
    public Button suppliersButton;
    public Button employeesButton;
    public Button productsButton;
    public Button customersButton;
    public Button ordersButton;

    public void goToDashboard(ActionEvent actionEvent) throws IOException {
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent dashboardParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/dashboard.fxml"));
        // 2. On l'injecte dans la scene:
        Scene dashboardScene = new Scene(dashboardParent);
        // 3. On récupère la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(dashboardScene);
        window.setTitle("Tableau de bord");
        // 4. On affiche le stage:
        window.show();
    }

    public void goToSuppliersPage(ActionEvent actionEvent) throws IOException {
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent suppliersParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/suppliersPage.fxml"));
        // 2. On l'injecte dans la scene:
        Scene suppliersScene = new Scene(suppliersParent);
        // 3. On récupère la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(suppliersScene);
        window.setTitle("Gestion des fournisseurs");
        // 4. On affiche le stage:
        window.show();
    }

    public void goToEmployeesPage(ActionEvent actionEvent) throws IOException {
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent employeesParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/employeesPage.fxml"));
        // 2. On l'injecte dans la scene:
        Scene employeesScene = new Scene(employeesParent);
        // 3. On récupère la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(employeesScene);
        window.setTitle("Gestion des employés");
        // 4. On affiche le stage:
        window.show();
    }

    public void goToProductsPage(ActionEvent actionEvent) throws IOException {
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent productsParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/productsPage.fxml"));
        // 2. On l'injecte dans la scene:
        Scene productsScene = new Scene(productsParent);
        // 3. On récupère la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(productsScene);
        window.setTitle("Gestion des produits");
        // 4. On affiche le stage:
        window.show();
    }

    public void goToCustomersPage(ActionEvent actionEvent) throws IOException {
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent customersParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/customersPage.fxml"));
        // 2. On l'injecte dans la scene:
        Scene customersScene = new Scene(customersParent);
        // 3. On récupère la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(customersScene);
        window.setTitle("Gestion des clients");
        // 4. On affiche le stage:
        window.show();
    }

    public void goToOrdersPage(ActionEvent actionEvent) throws IOException {
        // 1. On crée le Parent qui contient le FXMLLoader "chargeur" de fxml avec l'adresse du fichier:
        Parent ordersParent = FXMLLoader.load(getClass().getResource("/org/myself/gui/ordersPage.fxml"));
        // 2. On l'injecte dans la scene:
        Scene ordersScene = new Scene(ordersParent);
        // 3. On récupère la stage
        //casting our event as a node, to then get the scene and the window, and then formally cast the window as a stage
        Stage window = (Stage)(((Node)actionEvent.getSource()).getScene().getWindow());
        // assigne la scene au stage:
        window.setScene(ordersScene);
        window.setTitle("Gestion des commandes");
        // 4. On affiche le stage:
        window.show();
    }
}
