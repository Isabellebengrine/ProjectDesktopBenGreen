package org.myself.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class PurchaseDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public PurchaseDAO() throws SQLException {
    }

    public List<Purchase> ListAll() {
        List<Purchase> list = new ArrayList<>();
        try {
            Statement stm = bengreenDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM purchases");
            while (res.next()) {
                Purchase purchase = new Purchase(
                        res.getInt("id"),
                        res.getString("purchases_suppliersref"),
                        res.getDate("purchases_date"),
                        res.getFloat("purchases_price"),
                        res.getInt("purchases_quantity")
                );
                list.add(purchase);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
