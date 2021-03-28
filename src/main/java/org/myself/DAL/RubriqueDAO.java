package org.myself.DAL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class RubriqueDAO {
    BengreenDB bengreenDB = new BengreenDB();

    public RubriqueDAO() throws SQLException {
    }

    public List<Rubrique> ListAll() {
        List<Rubrique> list = new ArrayList<>();
        try {
            Statement stm = bengreenDB.getConnection().createStatement();
            ResultSet res = stm.executeQuery("SELECT * FROM rubrique");
            while (res.next()) {
                Rubrique rubrique = new Rubrique(
                        res.getInt("id"),
                        res.getString("rubrique_name"),
                        res.getString("rubrique_picture")
                );
                list.add(rubrique);
            }
            stm.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }
}
