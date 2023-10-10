package Model;

import Conexion.Dao;
import Domain.Pacient;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;
public class PacientDAO implements Dao<Pacient> {

    private static String SQL_INSERT = "INSERT INTO Pacient (dni, nom, cognom1, cognom2, actiu) VALUES (?, ?, ?, ?, ?,)";
    private static String SQL_UPDATE = "UPDATE Pacient SET nom = ?, cognom1 = ?, cognom2 = ?, actiu = ? WHERE dni = ?";

    private static String SQL_DELETE = "DELETE FROM Pacient WHERE dni = ?";
    private static String SQL_SELECTALL = "SELECT * FROM Pacient";
    private static String SQL_SELECT = "SELECT * FROM Pacient WHERE dni = ?";
    private static Pacient getPacient(ResultSet rs) {
        try {
            String dni = rs.getString("dni");
            String nom = rs.getString("nom");
            String cognom1 = rs.getString("cognom1");
            String cognom2 = rs.getString("cognom2");
            Boolean actiu = rs.getBoolean("actiu");
            return new Pacient(dni, nom, cognom1, cognom2, actiu);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean insert(Pacient pacient) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, pacient.getDni());
            stmnt.setString(2, pacient.getNom());
            stmnt.setString(3, pacient.getCognom1());
            stmnt.setString(4, pacient.getCognom2());
            stmnt.setBoolean(5, pacient.getActiu());
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
    public boolean update(Pacient pacient) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, pacient.getNom());
            stmnt.setString(2, pacient.getCognom1());
            stmnt.setString(3, pacient.getCognom2());
            stmnt.setBoolean(4, pacient.getActiu());
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
            stmnt.setString(1, (String) primaryKey);
            stmnt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }

    @Override
    public List<Pacient> selectAll() {
        List<Pacient> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getPacient(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Pacient select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = stmnt.executeQuery();
            return getPacient(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
