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

    private static final String SQL_INSERT = "INSERT INTO Medicament(nomComercial, formula, actiu) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Medicament SET formula = ?, actiu = ? WHERE nomComercial = ?";
    private static final String SQL_DELETE = "DELETE FROM Medicament WHERE  nomComercial = ?";
    private static final String  SQL_SELECT = "SELECT * FROM Medicament WHERE nomComercial = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Medicament";

    @Override
    public Medicament getEntity(ResultSet rs)  {
        Medicament medicament = null;
        try {
            String nomComercial = rs.getString("nomComercial");
            String formula = rs.getString("formula");
            boolean actiu = rs.getBoolean("actiu");
            medicament = new Medicament(nomComercial, formula, actiu);
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            closeConnection();
        }
        return medicament;
    }

    @Override
    public boolean insert(Medicament medicament)  {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, medicament.getNomComercial());
            stmnt.setBoolean(2, medicament.getActiu());
            res =queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean update(Medicament medicament)  {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, medicament.getFormula());
            stmnt.setBoolean(2, medicament.getActiu());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean delete(Object primaryKey)  {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setString(1, (String) primaryKey);
            res = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public List<Medicament> selectAll()  {
        List<Medicament> list = new ArrayList<>();
         try {
             PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
             ResultSet rs = stmnt.executeQuery();
             while(rs.next()) {
                 list.add(getEntity(rs));
             }
         } catch (SQLException e) {
             e.printStackTrace();
         }

         return list;
    }

    @Override
    public Medicament select(Object primaryKey)  {
        Medicament medicament = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setString(1, (String) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            medicament = getEntity(rs);
        } catch (Exception e) {
            e.printStackTrace(System.err);
        }

        return medicament;
    }
}
