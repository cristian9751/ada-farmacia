package Model;

import Conexion.Dao;
import Domain.Entity.Farmaceutic;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexion.Conexion.*;

public class FarmaceuticDAO implements Dao<Farmaceutic> {
    private static final String SQL_INSERT = "INSERT INTO farmaceutic(dni, nom, cognom1, cognom2, anyLicenciatura, actiu)" +
            " VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE farmaceutic SET nom = ?, cognom1 = ?, cognom2 = ?, anyLicenciatura = ?" +
            ", actiu = ? WHERE dni = ?";
    private static final String SQL_DELETE = "DELETE FROM Farmaceutic WHERE dni = ?";

    private static final String SQL_SELECTALL = "SELECT * FROM Farmaceutic";

    private static final String SQL_SELECT = SQL_SELECTALL +  " WHERE dni = ?";

    @Override
    public Farmaceutic getEntity(ResultSet rs) throws Exception {
        try {
            String dni = rs.getString("dni");
            String nom = rs.getString("nom");
            String cognom1 = rs.getString("cognom1");
            String cognom2 = rs.getString("cognom2");
            Date anyLicenciatura = rs.getDate("anyLicenciatura");
            Boolean actiu = rs.getBoolean("actiu");
            return new Farmaceutic(dni, nom, cognom1, cognom2, anyLicenciatura, actiu);
        } catch (SQLException e) {
            throw new Exception("There has been an error fetching the data");
        }
    }

    @Override
    public boolean insert(Farmaceutic farmaceutic) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, farmaceutic.getDni());
            stmnt.setString(2, farmaceutic.getNom());
            stmnt.setString(3, farmaceutic.getCognom1());
            stmnt.setString(4, farmaceutic.getCognom2());
            stmnt.setDate(5, farmaceutic.getAnyLicenciatura());
            stmnt.setBoolean(6, farmaceutic.getActiu());
            return queryDone(stmnt.executeUpdate());

        } catch (SQLException e) {
            throw new Exception("Farmaceutic exists");
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(Farmaceutic farmaceutic) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, farmaceutic.getNom());
            stmnt.setString(2, farmaceutic.getCognom1());
            stmnt.setString(3, farmaceutic.getCognom2());
            stmnt.setDate(4, farmaceutic.getAnyLicenciatura());
            stmnt.setBoolean(5, farmaceutic.getActiu());
            stmnt.setString(6, farmaceutic.getDni());
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            select(farmaceutic.getDni());
            throw new Exception("Data unchanged");
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean delete(Object primaryKey) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setString(1, (String) primaryKey);
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            throw new Exception("Farmaceutic does not exist");
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Farmaceutic> selectAll() throws Exception {
        List<Farmaceutic> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs  = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getEntity(rs));
            }
        } catch (Exception e) {
            throw new Exception("There has been an error fetching the data");
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public Farmaceutic select(Object primaryKey) throws Exception {
        Farmaceutic farmaceutic;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setString(1, (String) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            farmaceutic = getEntity(rs);
        } catch (Exception e) {
            throw new Exception("Farmaceutic does not exist");
        } finally {
            closeConnection();
        }

        return farmaceutic;
    }
}
