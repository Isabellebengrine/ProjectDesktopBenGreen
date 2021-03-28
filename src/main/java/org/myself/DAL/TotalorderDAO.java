package org.myself.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class TotalorderDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public TotalorderDAO() throws SQLException {
    }

    public List<Totalorder> ListAll() {
        List<Totalorder> list = new ArrayList<>();
        try {
            Statement stm = bengreenDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM totalorders");
            while (res.next()) {
                Totalorder order = new Totalorder(
                        res.getInt("id"),
                        res.getDate("totalorder_date"),
                        res.getString("totalorder_billaddress"),
                        res.getString("totalorder_deliveryaddress"),
                        res.getFloat("totalorder_discount"),
                        res.getInt("totalorder_invoicenb"),
                        res.getDate("totalorder_invoicedate"),
                        res.getString("status"),
                        res.getDate("updated_at")
                );
                list.add(order);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
