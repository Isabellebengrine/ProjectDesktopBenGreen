package org.myself.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public SupplierDAO() throws SQLException {
    }

    public List<Supplier> ListAll() {
        List<Supplier> list = new ArrayList<>();
        try {
            Statement stm = bengreenDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM suppliers");
            while (res.next()) {
                Supplier supplier = new Supplier(
                        res.getInt("id"),
                        res.getString("suppliers_name"),
                        res.getString("suppliers_address"),
                        res.getString("suppliers_zipcode"),
                        res.getString("suppliers_city"),
                        res.getString("suppliers_phone")
                );
                list.add(supplier);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
