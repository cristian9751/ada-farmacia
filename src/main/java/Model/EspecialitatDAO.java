package Model;

import Conexion.Dao;
import Domain.Especialitat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;
public class EspecialitatDAO implements Dao<Especialitat> {
    private static final String SQL_INSERT = "INSERT INTO Especialitat (nom) VALUES (?) WHERE idEspecialitat = ?";
    private static final String SQL_UPDATE = "UPDATE Especialitat SET nom = ? WHERE idEspecialitat = ?";

    private static final String SQL_DELETE = "DELETE From Especialtat WHERE idEspecialitat = ?";


    private static final String SQL_SELECT = "SELECT * FROM Especialitat WHERE idEspecialitat = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Especialitat";

    private static Especialitat getEspecialtiat(ResultSet rs) {
        try {
            int idEspecialitat = rs.getInt("idEspecialitat");
            String nom = rs.getString("nom");
            return new Especialitat(idEspecialitat , nom);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }
    @Override
    public boolean insert(Especialitat especialitat) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, especialitat.getNom());
            stmnt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(Especialitat especialitat) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, especialitat.getNom());
            stmnt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean delete(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setInt(1, (int) primaryKey);
            stmnt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Especialitat> selectAll() {
        List<Especialitat> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getEspecialtiat(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public Especialitat select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            return getEspecialtiat(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
