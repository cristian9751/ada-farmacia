package Model;

import Conexion.Dao;
import Domain.Entity.Emplea;
import Domain.Entity.Farmaceutic;
import Domain.Entity.Farmacia;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;

public class EmpleaDAO implements Dao<Emplea> {
    private static final String SQL_INSERT = "INSERT INTO Emplea(Farmacia, Farmaceutic) VALUES(?, ?)";
    private static final String SQL_DELETE = "DELETE FROM Emplea WHERE farmaceutic = ?";

    private static final String SQL_SELECT = "SELECT * FROM Emplea WHERE idEmplea = ?";

    private static final String SQL_SELECTALL = "SELECT * FROM Emplea";


    public static Emplea getEmplea(ResultSet rs) throws Exception {
        try {
            Farmacia farmacia = new FarmaciaDAO().select(rs.getString("farmacia"));
            Farmaceutic farmaceutic = new FarmaceuticDAO().select(rs.getString("farmaceutic"));
            return new Emplea(farmacia, farmaceutic);
        } catch (SQLException e) {
            throw new Exception("There has been an error fething the data");
        }
    }
    @Override
    public boolean insert(Emplea emplea) throws Exception {
        boolean result = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, emplea.getFarmacia().getCif());
            stmnt.setString(2, emplea.getFarmaceutic().getDni());
            result = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            throw new Exception("Emplea exists");
        } finally {
            closeConnection();
        }
        return result;
    }

    @Override
    public boolean update(Emplea objeto) throws Exception {
        return false;
    }

    @Override
    public boolean delete(Object primaryKey) throws Exception {
        boolean result = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setString(1, (String) primaryKey);
            result = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            throw new Exception("Emplea does not exists");
        }

        return result;
    }

    @Override
    public List<Emplea> selectAll() throws Exception {
        List<Emplea> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getEmplea(rs));
            }
        } catch (SQLException e) {
            throw new Error("There has been an error fetching the data");
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public Emplea select(Object primaryKey) throws Exception {
        Emplea emplea;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setString(1, (String) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            emplea = getEmplea(rs);
        } catch (Exception e) {
            throw new Exception("Emplea does not exist");
        } finally {
            closeConnection();
        }

        return emplea;
    }



}
