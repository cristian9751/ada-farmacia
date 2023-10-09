package Model;

import Conexion.Dao;
import Domain.Adresa;
import Domain.Farmacia;

import static Conexion.Conexion.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class FarmaciaDAO implements Dao<Farmacia> {
    private static final String SQL_INSERT = "INSERT INTO Farmcia VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Farmacia SET Adresa = ?, Actiu= ? WHERE cif = ?";
    private static final String SQL_SELECT = "SELECT * FROM Farmacia WHERE cif = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Farmacia";
    private static final String SQL_DELETE = "DELETE FROM Farmacia WHERE cif = ?";


    public Farmacia getFarmacia(ResultSet rs) {
        try {
            String cif = rs.getString("cif");
            Boolean actiu = rs.getBoolean("actiu");
            Adresa adresa = new AdresaDao().select(rs.getInt("Adresa"));
            return new Farmacia(adresa, cif, actiu);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }

    }

    @Override
    public boolean insert(Farmacia farmacia) {

        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, farmacia.getCif());
            stmnt.setInt(2, farmacia.getAdresa().getId());
            stmnt.setBoolean(3, farmacia.getAcitu());
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
    public boolean update(Farmacia farmacia) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setInt(1, farmacia.getAdresa().getId());
            stmnt.setBoolean(2, farmacia.getAcitu());
            stmnt.setString(3, farmacia.getCif());
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
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }


    @Override
    public List<Farmacia> selectAll() {
        List<Farmacia> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
                list.add(getFarmacia(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public Farmacia select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = stmnt.executeQuery();
            return getFarmacia(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }
}
