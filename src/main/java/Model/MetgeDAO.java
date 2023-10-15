package Model;

import Conexion.Dao;
import Domain.Entity.Especialitat;
import Domain.Entity.Metge;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;

public class MetgeDAO implements Dao<Metge> {
    String SQL_INSERT = "INSERT INTO metge (especialitat, nom, cognom1, cognom2, actiu) VALUES (?, ?, ?, ?, ?)";
    String SQL_UPDATE = "UPDATE metge SET especialitat = ?, nom = ?, cognom1 = ?, cognom2 = ?, actiu = ? WHERE numColegiat = ?";
    String SQL_DELETE = "DELETE FROM metge WHERE numColegiat = ?";
    String SQL_SELECTALL = "SELECT * FROM metge";
    String SQL_SELECT = SQL_SELECTALL + " WHERE numColegiat = ?";

    @Override
    public Metge getEntity(ResultSet rs) {
        Metge metge = null;
        try {
            int numColegiat = rs.getInt("numColegiat");
            Especialitat especialitat = new EspecialitatDAO().select(rs.getInt("especialitat"));
            String nom = rs.getString("nom");
            String cognom1 = rs.getString("cognom1");
            String cognom2 = rs.getString("cognom2");
            boolean actiu = rs.getBoolean("actiu");
            metge = new Metge(numColegiat, especialitat, nom, cognom1, cognom2, actiu);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return metge;
    }

    @Override
    public boolean insert(Metge metge) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setInt(1, metge.getEspecialitat().getIdEspecialitat());
            stmnt.setString(2, metge.getNom());
            stmnt.setString(3, metge.getCognom1());
            stmnt.setString(4, metge.getCognom2());
            stmnt.setBoolean(5, metge.isActiu());
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;

    }

    @Override
    public boolean update(Metge metge) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setInt(1, metge.getEspecialitat().getIdEspecialitat());
            stmnt.setString(2, metge.getNom());
            stmnt.setString(3, metge.getCognom1());
            stmnt.setString(4, metge.getCognom2());
            stmnt.setBoolean(5, metge.isActiu());
            stmnt.setInt(6, metge.getNumColegiat());
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
    public List<Metge> selectAll() {
        List<Metge> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                list.add(getEntity(rs));
            }
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }

        return list;
    }

    @Override
    public Metge select(Object primaryKey) {
        Metge metge = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            metge = getEntity(rs);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return metge;
    }
}
