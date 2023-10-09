package Model;

import Conexion.Dao;
import Domain.Emplea;
import Domain.Farmaceutic;
import Domain.Farmacia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;

public class EmpleaDAO implements Dao<Emplea> {


    private static Emplea getEmplea(ResultSet rs) {
        try {
            String cif_Farmacia = rs.getString("farmacia");
            String dni_Farmaceutic = rs.getString("farmaceutic");
            Farmacia farmacia = new FarmaciaDAO().select(rs.getString("farmacia"));
            Farmaceutic farmaceutic = new FarmaceuticDAO().select(rs.getString("farmaceutic"));
            return new Emplea(farmacia, farmaceutic);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    private static final String SQL_INSERT = "INSERT INTO Emplea VALUES(?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Emplea WHERE farmacia = ?";

    private static final String SQL_SELECTFARMACIA =  "SELECT * FROM Emplea WHERE farmacia = ?";
    private static final String SQL_SELECTFARMACEUTIC = "SELECT * FORM Emplea WHERE farmaceutic = ?";

    private static final String SQL_SELECTALL = "SELECT * FROM Emplea";
    @Override
    public boolean insert(Emplea emplea) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, emplea.getFarmacia().getCif());
            stmnt.setString(2, emplea.getFarmaceutic().getDni());
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
    public boolean update(Emplea emplea) {
        return false;
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
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Emplea> selectAll() {
        List<Emplea> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getEmplea(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Emplea select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTFARMACEUTIC);
            ResultSet rs = stmnt.executeQuery();
            return getEmplea(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Emplea selectFromFarmacia(String cif_farmacia) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTFARMACIA);
            ResultSet rs = stmnt.executeQuery();
            return getEmplea(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
