package Model;

import Conexion.Dao;
import Domain.Entity.*;
import Domain.Entity.Tracta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexion.Conexion.*;

public class TractaDAO implements Dao<Tracta> {
    private static final String SQL_INSERT = "INSERT INTO Tracta (metge, pacient) VALUES (?, ?)";
    private static final String SQL_UPDATE = "UPDATE Tracta SET metge = ?, SET pacient = ? WHERE idTracta = ?";
    private static final String SQL_DELETE = "DELETE FROM Tracta WHERE idTracta = ?";

    private static final String SQL_SELECTALL = "SELECT * FROMA Tracta";

    private static final String SQL_SELECT = SQL_SELECTALL + " WHERE idTracta = ?";


    @Override
    public Tracta getEntity(ResultSet rs) {
        Tracta tracta = null;
        try {
            int idTracta = rs.getInt("idTracta");
            Metge metge = new MetgeDAO().select(rs.getInt("metge"));
            Pacient pacient = new PacientDAO().select(rs.getString("pacient"));
            tracta = new Tracta(idTracta ,metge, pacient);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return tracta;
    }

    @Override
    public boolean insert(Tracta tracta) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setInt(1, tracta.getMetge().getNumColegiat());
            stmnt.setString(2, tracta.getPacient().getDni());
            res = queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean update(Tracta tracta) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setInt(1, tracta.getMetge().getNumColegiat());
            stmnt.setString(2, tracta.getPacient().getDni());
            stmnt.setInt(3, tracta.getIdTracta());
            res = queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
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
            stmnt.setInt(1, (int) primaryKey);
            res= queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public List<Tracta> selectAll() {
        List<Tracta> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getEntity(rs));
            }
        } catch(Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }

        return list;
    }

    @Override
    public Tracta select(Object primaryKey) {
        Tracta tracta = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            tracta = getEntity(rs);
        } catch(Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return tracta;
    }
}
