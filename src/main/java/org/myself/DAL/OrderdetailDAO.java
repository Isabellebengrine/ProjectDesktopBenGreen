package org.myself.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OrderdetailDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public OrderdetailDAO() throws SQLException {
    }

    public List<Orderdetail> ListAll() {
        List<Orderdetail> list = new ArrayList<>();
        try {
            Statement stm = bengreenDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM orderdetail");
            while (res.next()) {
                Orderdetail detail = new Orderdetail(
                        res.getInt("id"),
                        res.getFloat("orderdetail_price"),
                        res.getFloat("orderdetail_quantity")
                );
                list.add(detail);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
