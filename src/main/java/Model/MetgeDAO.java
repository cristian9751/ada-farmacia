package Model;

import Conexion.Dao;
import Domain.Especialitat;
import Domain.Metge;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class MetgeDAO implements Dao<Metge> {

    private static Metge getMetge(ResultSet rs) {
        try {
            int numColegiat = rs.getInt("numColegiat");
            Especialitat especialitat = new EspecialitatDAO().select(rs.getInt("especialitat"));
            String nom = rs.getString("nom");
            String cognom1 = rs.getString("cognom1");
            String cognom2 = rs.getString("cognom2");
            Boolean actiu = rs.getBoolean("actiu");
            return new Metge(numColegiat, especialitat, nom, cognom1, cognom2, actiu);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    @Override
    public boolean insert(Metge objeto) {
        return false;
    }

    @Override
    public boolean update(Metge objeto) {
        return false;
    }

    @Override
    public boolean delete(Object primaryKey) {
        return false;
    }

    @Override
    public List<Metge> selectAll() {
        return null;
    }

    @Override
    public Metge select(Object primaryKey) {
        return null;
    }
}
