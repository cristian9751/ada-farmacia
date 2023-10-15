package Model;

import Conexion.Dao;
import Domain.Entity.Especialitat;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;

public class EspecialitatDAO implements Dao<Especialitat> {
    private static final String SQL_INSERT = "INSERT INTO Especialitat(nom) VALUES(?)";
    private static final String SQL_UPDATE = "UPDATE Especialitat SET nom = ? WHERE idEspecialitat = ?";

    private static final String SQL_DELETE = "DELETE * FROM Especialitat WHERE idEspecialitat = ?";

    private static final String SQL_SELECTALL = "SELECT * FROM Especialitat";

    private static final String SQL_SELECT = SQL_SELECTALL + " WHERE idEspecialitat = ?";

    @Override
    public Especialitat getEntity(ResultSet rs) {
        Especialitat especialitat = null;
        try {
            int idEspecialitat = rs.getInt("idEspecialitat");
            String nom = rs.getString("nom");
            especialitat = new Especialitat(idEspecialitat, nom);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return especialitat;
    }

    @Override
    public boolean insert(Especialitat especialitat) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, especialitat.getNom());
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean update(Especialitat especialitat) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, especialitat.getNom());
            stmnt.setInt(2, especialitat.getIdEspecialitat());
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean delete(Object primaryKey) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setInt(1, (int) primaryKey);
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public List<Especialitat> selectAll() {
        List<Especialitat> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next())  {
                list.add(getEntity(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }

        return list;
    }

    @Override
    public Especialitat select(Object primaryKey) {
        Especialitat especialitat = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            especialitat = getEntity(rs);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return especialitat;
    }
}
