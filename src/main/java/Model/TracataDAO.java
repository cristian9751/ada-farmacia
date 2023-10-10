package Model;

import Conexion.Dao;
import Domain.Metge;
import Domain.Pacient;
import Domain.Tracta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;

public class TracataDAO implements Dao<Tracta> {

    private static final String SQL_INSERT = "INSERT INTO Tracta (metge, pacient) VALUES (?, ?)";
    private  static final String SQL_UPDATE = "UPDATE Tracta SET  metge = ?, pacient = ? WHERE idTracta = ?";
    private static final String SQL_DELETE= "DELETE FROM Tracta WHERE idTracta = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Tracta";

    private static final String SQL_SELECT = "SELECT * FROM Tracta WHERE idTracta = ?";
    private static Tracta getTracata(ResultSet rs) {
        try {
            int idTracta = rs.getInt("idTracata");
            Metge metge = new MetgeDAO().select(rs.getInt("metge"));
            Pacient pacient = new PacientDAO().select(rs.getString("pacient"));
            return new Tracta(metge, pacient);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public boolean insert(Tracta tracta) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setInt(1, tracta.getMetge().getNumColegiat());
            stmnt.setString(2, tracta.getPacient().getDni());
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
    public boolean update(Tracta tracta) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setInt(1, tracta.getMetge().getNumColegiat());
            stmnt.setString(2, tracta.getPacient().getDni());
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
            stmnt.setInt(1, (int) primaryKey);
            stmnt.execute();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Tracta> selectAll() {
        List<Tracta> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getTracata(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Tracta select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = stmnt.executeQuery();
            return getTracata(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }
}
