package Model;

import Conexion.Dao;
import Domain.Entity.Medicament;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static Conexion.Conexion.*;

public class MedicamentDAO implements Dao<Medicament> {
    private static final String SQL_INSERT = "INSERT INTO medicament VALUES(?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE medicament SET formula = ?, actiu = ? WHERE nomComercial = ?";

    private static final String SQL_DELETE = "DELETE FROM Medicament WHERE nomComercial = ?";

    private static final String SQL_SELECTALL = "SELECT * FROM Medicament";

    private static final String SQL_SELECT = "SELECT * FROM Medicament WHERE nomComercial = ?";


    public static Medicament getMedicament(ResultSet rs) {
        try {
            String nomComercial = rs.getString("nomComercial");
            String formula = rs.getString("formula");
            Boolean actiu = rs.getBoolean("actiu");
            return new Medicament(nomComercial, formula);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }
    @Override
    public boolean insert(Medicament medicament) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, medicament.getNomComercial());
            stmnt.setString(2, medicament.getFormula());
            stmnt.setBoolean(3, medicament.getActiu());
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
    public boolean update(Medicament medicament) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, medicament.getFormula());
            stmnt.setBoolean(2, medicament.getActiu());
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
    public List<Medicament> selectAll() {
        List<Medicament> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getMedicament(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return list;
    }

    @Override
    public Medicament select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = stmnt.executeQuery();
            return getMedicament(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
