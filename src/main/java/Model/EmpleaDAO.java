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
    public Emplea getEntity(ResultSet rs) {
        Emplea emplea = null;
        try {
            int idEmplea = rs.getInt("idEmplea");
            Farmacia farmacia = new FarmaciaDAO().select(rs.getString("farmacia"));
            Farmaceutic farmaceutic = new FarmaceuticDAO().select(rs.getString("farmaceutic"));
            return new Emplea(idEmplea, farmacia, farmaceutic);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }

        return emplea;
    }

    @Override
    public boolean insert(Emplea emplea)  {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, emplea.getFarmacia().getCif());
            stmnt.setString(2, emplea.getFarmaceutic().getDni());
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }

        return res;
    }

    @Override
    public boolean update(Emplea emplea) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, emplea.getFarmacia().getCif());
            stmnt.setString(2, emplea.getFarmaceutic().getDni());
            stmnt.setInt(3, emplea.getEmpleaId());
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }

        return false;

    }

    @Override
    public boolean delete(Object primaryKey){
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setInt(1, (int) primaryKey);
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public List<Emplea> selectAll() {
        List<Emplea> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
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
    public Emplea select(Object primaryKey)  {
        Emplea emplea = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            emplea = getEntity(rs);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return emplea;
    }
}
