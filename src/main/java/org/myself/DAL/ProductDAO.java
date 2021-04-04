package org.myself.DAL;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProductDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public ProductDAO() throws SQLException {
    }

    /**
     * Obtention de tous les produits
     * @return List de Product
     */
    public List<Product> ListAll() {
        List<Product> list = new ArrayList<>();
        try {
            Statement stm = bengreenDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM products");
            while (res.next()) {
                Product product = new Product(
                        res.getInt("id"),
                        res.getString("products_name"),
                        res.getString("products_description"),
                        res.getInt("products_stock"),
                        res.getString("products_picture"),
                        res.getFloat("products_price"),
                        res.getInt("rubrique_id")
                );
                list.add(product);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    /**
     * Liste des produits d'une catégorie
     * @param rubrique_id numéro de la catégorie
     * @return List de Product
     */
    public List<Product> listProductsByCategory(int rubrique_id){
        List<Product> listeProduct = new ArrayList<>();
        try {
            PreparedStatement preparedStatement = bengreenDB.getConnection().prepareStatement("SELECT *  FROM products WHERE rubrique_id = ?");
            preparedStatement.setInt(1, rubrique_id);
            ResultSet res = preparedStatement.executeQuery();
            while (res.next()) {
                Product p = new Product(res.getInt("id"),res.getString("products_name"), res.getString("products_description"),res.getInt("products_stock"), res.getString("products_picture"), res.getFloat("products_price"), res.getInt("rubrique_id"));
                listeProduct.add(p);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ProductDAO.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("c'est dans ProductDAO que ça se passe!!!");
        }
        return listeProduct;
    }

    /**
     * Insertion d'un nouveau Produit
     */
    public void insert(Product product) {
        try{
            PreparedStatement stm = bengreenDB.getConnection().prepareStatement("INSERT INTO products (products_name, products_description, products_stock, products_picture, products_price, rubrique_id) VALUES (?, ?, ?, ?, ?, ?)");

            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setInt(3, product.getStock());
            stm.setString(4, product.getPicture());
            stm.setFloat(5, product.getPrice());
            stm.setInt(6, product.getCategory());

            stm.execute();

            stm.close();
            System.out.println("l'insertion s’est bien effectuée");
        } catch (Exception e){
            System.out.println("Erreur lors de l'insertion du nouveau produit");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Modification d'un produit
     */
    public void update(Product product){
        try {
            PreparedStatement stm = bengreenDB.getConnection().prepareStatement("UPDATE products SET products_name = ?, products_description = ?, products_stock = ?, products_picture = ?, products_price = ?, rubrique_id = ? WHERE id = ?;");

            stm.setString(1, product.getName());
            stm.setString(2, product.getDescription());
            stm.setInt(3, product.getStock());
            stm.setString(4, product.getPicture());
            stm.setFloat(5, product.getPrice());
            stm.setInt(6, product.getCategory());

            stm.execute();

            stm.close();
            System.out.println("la modification s’est bien effectuée");

        }
        catch (Exception e) {
            System.out.println("Erreur lors de la modification du produit");
            System.out.println(e.getMessage());
        }
    }

    /**
     * Suppression d'un Product
     */
    public void delete(Product product) {
        try {
            PreparedStatement stm = bengreenDB.getConnection().prepareStatement("DELETE FROM products WHERE product_id = ?;");

            stm.setInt(1, product.getId());

            stm.execute();

            stm.close();
            System.out.println("La suppression s’est bien effectuée");

        }
        catch (Exception e) {
            System.out.println("Erreur lors de la suppression du produit");
            System.out.println(e.getMessage());
        }
    }

}
