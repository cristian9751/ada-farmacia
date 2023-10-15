package Model;

import Conexion.Dao;
import Domain.Entity.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import static Conexion.Conexion.*;

public class PrescripcioDAO implements Dao<Prescripcio> {
    private static final String SQL_INSERT = "INSERT INTO prescripcio (medicament, metge, pacient) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE prescripcio SET medicament = ?, metge = ?, pacient = ? WHERE idPrescripcio = ?";
    private static final String SQL_DELETE = "DELETE FROM prescripcio WHERE idPrescripcio = ?";

    private static final String SQL_SELECTALL = "SELECT * FROM prescripcio";

    private static final String SQL_SELECT = SQL_SELECTALL + " WHERE idPrescripcio = ?";

    @Override
    public Prescripcio getEntity(ResultSet rs) {
        Prescripcio prescripcio = null;
         //public Prescripcio(int idPrescripcio, Medicament medicament, Pacient pacient, Metge metge, Timestamp data)
        try {
            int idPrescripcio = rs.getInt("idPrescripcio");
            Medicament medicament = new MedicamentDAO().select(rs.getString("medicament"));
            Metge metge = new MetgeDAO().select(rs.getInt("metge"));
            Pacient pacient = new PacientDAO().select(rs.getString("pacient"));
            Timestamp data = rs.getTimestamp("dataPrescripcio");
            prescripcio = new Prescripcio(idPrescripcio, medicament, pacient, metge, data);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return prescripcio;
    }

    @Override
    public boolean insert(Prescripcio prescripcio) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, prescripcio.getMedicament().getNomComercial());
            stmnt.setInt(2, prescripcio.getMetge().getNumColegiat());
            stmnt.setString(3, prescripcio.getPacient().getDni());
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean update(Prescripcio prescripcio) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, prescripcio.getMedicament().getNomComercial());
            stmnt.setInt(2, prescripcio.getMetge().getNumColegiat());
            stmnt.setString(3, prescripcio.getPacient().getDni());
            stmnt.setInt(4, prescripcio.getIdPrescripcio());
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
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
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public List<Prescripcio> selectAll() {
        List<Prescripcio> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while (rs.next()) {
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
    public Prescripcio select(Object primaryKey) {
        Prescripcio prescripcio = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            prescripcio = getEntity(rs);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return prescripcio;
    }
}
