package Model;

import Conexion.Dao;
import Domain.Farmaceutic;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import static  Conexion.Conexion.*;

public class FarmaceuticDAO implements Dao<Farmaceutic> {
    private static final String  SQL_INSERT = "INSERT INTO Farmaceutic VALUES(?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Farmaceutic SET ?, ?, ?, ?, ? WHERE dni = ?";
    private static final String SQL_DELETE = "DELETE FROM Farmaceutic WHERE cif = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Farmaceutic ";
    private static final String SQL_SELECT = "SELECT * FROM Farmaceutic WHERE dni = ?";

    private static Farmaceutic getFarmacetic(ResultSet rs ) {
        try {
            String dni = rs.getString("dni");
            String nom = rs.getString("nom");
            String cognom1 = rs.getString("cognom1");
            String cognom2 = rs.getString("cognom2");
            Date anyLicenciatura = rs.getDate("anyLicenciatura");
            Boolean actiu = rs.getBoolean("actiu");
            return new Farmaceutic(dni, nom, cognom1, cognom2, anyLicenciatura, actiu);
        } catch (SQLException e ) {
            e.printStackTrace();
            return null;
        }

    }
    @Override
    public boolean insert(Farmaceutic farmaceutic) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, farmaceutic.getDni());
            stmnt.setString(2, farmaceutic.getNom());
            stmnt.setString(3, farmaceutic.getCognom1());
            stmnt.setDate(4, farmaceutic.getAnyLicenciatura());
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
    public boolean update(Farmaceutic farmaceutic) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, farmaceutic.getDni());
            stmnt.setString(2, farmaceutic.getNom());
            stmnt.setString(3, farmaceutic.getCognom1());
            stmnt.setString(4, farmaceutic.getCognom2());
            stmnt.setDate(5, farmaceutic.getAnyLicenciatura());
            stmnt.setBoolean(6, farmaceutic.getActiu());
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
        } finally {
            closeConnection();
        }

    }

    @Override
    public List<Farmaceutic> selectAll() {
        List<Farmaceutic> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getFarmacetic(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public Farmaceutic select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setString(1, (String) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            return getFarmacetic(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
