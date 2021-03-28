package org.myself.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public CustomerDAO() throws SQLException {
    }

    public List<Customer> ListAll() {
        List<Customer> list = new ArrayList<>();
        try {
            Statement stm = bengreenDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM customers");
            while (res.next()) {
                Customer customer = new Customer(
                        res.getInt("id"),
                        res.getString("customers_name"),
                        res.getString("customers_address"),
                        res.getString("customers_zipcode"),
                        res.getString("customers_city"),
                        res.getString("customers_phone")
                );
                list.add(customer);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
