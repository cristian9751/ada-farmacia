package Model;

import Conexion.Dao;
import Domain.Entity.Farmaceutic;
import Domain.Entity.Farmacia;
import Domain.Entity.Medicament;
import Domain.Entity.Venta;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import static Conexion.Conexion.*;
public class VentaDAO implements Dao<Venta> {

    private static final String SQL_INSERT = "INSERT INTO Venta (dataVenta, preu, farmacia, farmaceutic, medicament)" +
            "VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE Venta SET dataVenta = ?, preu = ?. farmacia = ?, farmaceutic = ?, " +
            "medicament = ? WHERE idVenta = ?";
    private static final String SQL_DELETE = "DELETE FROM Venta WHERE idVenta = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Venta";
    private static final String SQL_SELECT = "SELECT * FROM Venta WHERE idVenta = ?";
    private static Venta getVenta(ResultSet rs) {
        try {
            int idVenta = rs.getInt("idVenta");
            Timestamp dataVenta = rs.getTimestamp("dataVenta");
            Double preu = rs.getDouble("preu");
            Farmacia farmacia = new FarmaciaDAO().select(rs.getString("farmacia"));
            Farmaceutic farmaceutic = new FarmaceuticDAO().select(rs.getString("farmaceutic"));
            Medicament medicament = new MedicamentDAO().select(rs.getString("medicament"));
            return new Venta(dataVenta, preu, farmacia, farmaceutic, medicament);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean insert(Venta venta) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setTimestamp(1, venta.getDataVenta());
            stmnt.setDouble(2, venta.getPreu());
            stmnt.setString(3, venta.getFarmacia().getCif());
            stmnt.setString(4, venta.getFarmaceutic().getDni());
            stmnt.setString(5, venta.getMedicament().getNomComercial());
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
    public boolean update(Venta venta) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setTimestamp(1,  venta.getDataVenta());
            stmnt.setDouble(2, venta.getPreu());
            stmnt.setString(3, venta.getFarmacia().getCif());
            stmnt.setString(4, venta.getFarmaceutic().getDni());
            stmnt.setString(5, venta.getMedicament().getNomComercial());
            stmnt.setInt(6, venta.getIdVenta());
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
    public List<Venta> selectAll() {
        List<Venta> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getVenta(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    @Override
    public Venta select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = stmnt.executeQuery();
            return getVenta(rs);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
