package Model;

import Conexion.Dao;
import Domain.Entity.Medicament;
import Domain.Entity.Metge;
import Domain.Entity.Pacient;
import Domain.Entity.Prescripcio;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexion.Conexion.*;

public class PrescripcioDAO implements Dao<Prescripcio> {

    private static final String SQL_INSERT = "INSERT INTO prescripcio (medicament, methge, pacient, dataPrescripcio)" +
            "VALUES(?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Prescripcio SET medicament = ?, metge = ?, pacient = ? " +
            "dataPrescripcio = ? WHERE idPrescripcio = ?";
    private static final String SQL_DELETE = "DELETE FROM Prescripcio WHERE idPrescripcio = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Prescripcio";
    private static final String SQL_SELECT = "SELECT * FROM Prescripcio WHERE idPrescripcio = ?";

    private static Prescripcio getPrescripcio(ResultSet rs) {
        try {
            int idPrescripcio = rs.getInt("idPrescripcio");
            Medicament medicament = new MedicamentDAO().select(rs.getString("Medicament"));
            Pacient pacient = new PacientDAO().select(rs.getString("pacient"));
            Date data = rs.getDate("dataPrescripcio");
            Metge metge = new MetgeDAO().select(rs.getInt("metge"));
            return new Prescripcio(idPrescripcio, medicament, pacient, metge, data);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Prescripcio prescripcio) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, prescripcio.getMedicament().getNomComercial());
            stmnt.setInt(2, prescripcio.getMetge().getNumColegiat());
            stmnt.setString(3, prescripcio.getPacient().getDni());
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
    public boolean update(Prescripcio prescripcio) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, prescripcio.getMedicament().getNomComercial());
            stmnt.setInt(2, prescripcio.getMetge().getNumColegiat());
            stmnt.setDate(3, prescripcio.getData());
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
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE );
            stmnt.setInt(1, (int) primaryKey);
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
    public List<Prescripcio> selectAll() {
        List<Prescripcio> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getPrescripcio(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public Prescripcio select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            return getPrescripcio(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
