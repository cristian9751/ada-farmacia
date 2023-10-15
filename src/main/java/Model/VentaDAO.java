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

public class VentaDAO implements Dao<Venta> {
    private static final String SQL_INSERT = "INSERT INTO venta(preu, farmacia, farmaceutic, medicament) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE  = "UPDATE Venta SET preu = ?, farmacia = ?, farmaceutic = ?, medicament = ? WHERE idVenta = ?";
    private static final String SQL_DELETE  = "DELETE FROM venta WHERE idVenta = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM venta";
    private static final String SQL_SELECT = SQL_SELECTALL + " WHERE idVenta = ?";

    @Override
    public Venta getEntity(ResultSet rs) {
        Venta venta = null;
        try {
            int idVenta = rs.getInt("idVenta");
            Timestamp dataVenta = rs.getTimestamp("dataVenta");
            double preu = rs.getDouble("preu");
            Farmacia farmacia = new FarmaciaDAO().select(rs.getString("farmacia"));
            Farmaceutic farmaceutic = new FarmaceuticDAO().select(rs.getString("farmaceutic"));
            Medicament medicament = new MedicamentDAO().select(rs.getString("medicament"));
            venta = new Venta(dataVenta, preu, farmacia, farmaceutic, medicament);
        } catch (SQLException e) {
            e.printStackTrace(System.err);
        }
        return venta;
    }

    @Override
    public boolean insert(Venta venta) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setDouble(1, venta.getPreu());
            stmnt.setString(2, venta.getFarmacia().getCif());
            stmnt.setString(3, venta.getFarmaceutic().getDni());
            stmnt.setString(4, venta.getMedicament().getNomComercial());
            res = queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean update(Venta venta) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setDouble(1, venta.getPreu());
            stmnt.setString(2, venta.getFarmacia().getCif());
            stmnt.setString(3, venta.getFarmaceutic().getDni());
            stmnt.setString(4, venta.getMedicament().getNomComercial());
            stmnt.setInt(5, venta.getIdVenta());
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
    public List<Venta> selectAll() {
        List<Venta> list = new ArrayList<>();
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
    public Venta select(Object primaryKey) {
        Venta venta = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            venta = getEntity(rs);
        } catch(Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return venta;
    }
}
