/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static Conexion.Conexion.*;
import Conexion.Dao;
import Domain.Adresa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author cripoponc
 */
public class AdresaDao implements Dao<Adresa> {
    private static final String SQL_INSERT = "INSERT INTO Adresses(carrer, provincia) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE Adresses SET provincia = ?, carrer = ? WHERE id = ? ";
    private static final String SQL_DELETE = "DELETE From Adresses WHERE id = ?";
    private static final String SQL_SELECT = "SELECT * FROM Adresses WHERE id = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Adresses";
    private static final Connection conexion = getConnection();



    public static Adresa getAdresa(ResultSet rs) {
        Adresa adresa = null;
        try {
            String carrer = rs.getString("carrer");
            String provincia = rs.getString("provincia");
            adresa = new Adresa(carrer, provincia);
        } catch(SQLException e) {
            e.printStackTrace();
        }
        return adresa;
    }

    @Override
    public boolean insert(Adresa adresa) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, adresa.getCarrer());
            stmnt.setString(2, adresa.getProvincia());
            stmnt.execute();
            return true;
        } catch(SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(Adresa adresa) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setInt(3, adresa.getId());
            stmnt.setString(2, adresa.getCarrer());
            stmnt.setString(1, adresa.getProvincia());
            stmnt.execute();
            return true;
        } catch(SQLException e) {
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
        } finally {
            closeConnection();
        }
    }

    @Override
    public List<Adresa> selectAll() {
        List<Adresa> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getAdresa(rs));
            }
        } catch(SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }

        return list;
    }

    @Override
    public Adresa select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            ResultSet rs = stmnt.executeQuery();
            return getAdresa(rs);
        } catch(SQLException e) {
            e.printStackTrace();
            return null;
        } finally {
            closeConnection();
        }
    }
}
