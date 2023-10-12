package Model;

import Conexion.Dao;
import static Conexion.Conexion.*;
import Domain.Entity.Adresa;
import Domain.Entity.Farmacia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FarmaciaDAO implements Dao<Farmacia> {
    private static String SQL_INSERT = "INSERT INTO farmacia(cif, Adresa, actiu) VALUES(?, ?, ?)";
    private static String SQL_UPDATE = "UPDATE Farmcia SET Adresa = ?, actiu = ? WHERE cif = ?";

    private static String SQL_DELETE = "DELETE FROM Farmacia WHERE cif = ?";

    private static String SELECT_ALL = "SELECT * FROM Farmacia";

    private static String SQL_SELECT = SELECT_ALL + "WHERE cif = ?";

    @Override
    public Farmacia getEntity(ResultSet rs) throws Exception {
        try {
            String cif = rs.getString("cif");
            Adresa adresa = new AdresaDao().select(rs.getInt("Adresa"));
            Boolean actiu = rs.getBoolean("actiu");
            return new Farmacia(adresa, cif, actiu);
        } catch (SQLException e) {
            throw new Exception("There has been an error fetching the data");
        }
        
    }

    @Override
    public boolean insert(Farmacia farmacia) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, farmacia.getCif());
            stmnt.setInt(2, farmacia.getAdresa().getId());
            stmnt.setBoolean(3, farmacia.getAcitu());
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            throw new Exception("Farmacia exists");
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(Farmacia farmacia) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setInt(1, farmacia.getAdresa().getId());
            stmnt.setBoolean(2, farmacia.getAcitu());
            stmnt.setString(3, farmacia.getCif());
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            select(farmacia.getCif());
            throw new Error("Data unchanged");
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
           throw new Exception("Farmacia does not exist");
       }
    }

    @Override
    public List<Farmacia> selectAll() throws Exception {
        List<Farmacia> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SELECT_ALL);
            ResultSet rs = stmnt.executeQuery();
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
    public Farmacia select(Object primaryKey) throws Exception {
        Farmacia farmacia;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setString(1, (String) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            farmacia = getEntity(rs);
        } catch (Exception e) {
            throw new Exception("Farmacia does not exist");
        } finally {
            closeConnection();
        }
            return farmacia;
    }
}
