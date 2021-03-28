package org.myself.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public ProductDAO() throws SQLException {
    }

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
                        res.getFloat("products_price")
                );
                list.add(product);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
