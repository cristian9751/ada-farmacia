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
    private static final String SQL_INSERT = "INSERT INTO emplea (farmacia, farmaceutic) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE emplea SET farmacia = ?, farmaceutic = ? WHERE idEmplea = ?";

    private static final String SQL_DELETE = "DELETE FROM emplea WHERE idEmplea = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM emplea";
    private static final String SQL_SELECT = SQL_SELECTALL + " WHERE idEmplea = ?";

    @Override
    public Emplea getEntity(ResultSet rs) throws Exception {
        try {
            int idEmplea = rs.getInt("idEmplea");
            Farmacia farmacia = new FarmaciaDAO().select(rs.getString("farmacia"));
            Farmaceutic farmaceutic = new FarmaceuticDAO().select(rs.getString("farmaceutic"));
            return new Emplea(idEmplea, farmacia, farmaceutic);
        } catch (SQLException e) {
            throw new Exception("There has been an error fetching the data");
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean insert(Emplea emplea) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, emplea.getFarmacia().getCif());
            stmnt.setString(2, emplea.getFarmaceutic().getDni());
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            throw new Exception("Emplea exists");
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(Emplea emplea) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, emplea.getFarmacia().getCif());
            stmnt.setString(2, emplea.getFarmaceutic().getDni());
            stmnt.setInt(3, emplea.getEmpleaId());
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            select(emplea.getEmpleaId());
            throw new Exception("Data unchanged");
        } finally {
            closeConnection();
        }

    }

    @Override
    public boolean delete(Object primaryKey) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setInt(1, (int) primaryKey);
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            throw new Exception("Emplea does not exist");
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Emplea> selectAll() throws Exception {
        List<Emplea> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
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
    public Emplea select(Object primaryKey) throws Exception {
        Emplea emplea;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            emplea = getEntity(rs);
        } catch (SQLException e) {
            throw new Exception("Emplea does not exist");
        } finally {
            closeConnection();
        }
        return emplea;
    }
}
