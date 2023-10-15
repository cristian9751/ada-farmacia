package Model;

import Conexion.Dao;
import Domain.Entity.Pacient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexion.Conexion.*;

public class PacientDAO implements Dao<Pacient> {
    private static final String SQL_INSERT = "INSERT INTO Pacient (dni, nom, cognom1, cognom2, actiu) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Pacient SET nom = ?, cognom1 = ?, cognom2 = ?, actiu = ? WHERE dni = ?";
    private static final String SQL_DELETE = "DELETE FROM Pacient WHERE dni = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Pacient";
    private static final String SQL_SELECT = SQL_SELECTALL + " WHERE dni = ?";

    @Override
    public Pacient getEntity(ResultSet rs) {
        Pacient pacient = null;
        try {
            String dni = rs.getString("dni");
            String nom = rs.getString("nom");
            String cognom1 = rs.getString("cognom1");
            String cognom2 = rs.getString("cognom2");
            Boolean actiu = rs.getBoolean("actiu");
            pacient = new Pacient(dni, nom, cognom1, cognom2, actiu);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }

        return pacient;
    }

    @Override
    public boolean insert(Pacient pacient) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, pacient.getDni());
            stmnt.setString(2, pacient.getNom());
            stmnt.setString(3, pacient.getCognom1());
            stmnt.setString(4, pacient.getCognom2());
            stmnt.setBoolean(5, pacient.getActiu());
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean update(Pacient pacient) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, pacient.getNom());
            stmnt.setString(2, pacient.getCognom1());
            stmnt.setString(3, pacient.getCognom2());
            stmnt.setBoolean(4, pacient.getActiu());
            stmnt.setString(5, pacient.getDni());
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
            stmnt.setString(1, (String) primaryKey);
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public List<Pacient> selectAll() {
        List<Pacient> list = new ArrayList<>();
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
    public Pacient select(Object primaryKey) {
        Pacient pacient = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setString(1, (String) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            pacient = getEntity(rs);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return pacient;
    }
}
