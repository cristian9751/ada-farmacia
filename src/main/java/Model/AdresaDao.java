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
    private static final String SQL_INSERT = "INSERT INTO Adresses(carrer, ciutat) VALUES(?, ?)";
    private static final String SQL_UPDATE = "UPDATE Adresses SET carrer = ?, ciutat = ? WHERE id = ? ";
    private static final String SQL_DELETE = "DELETE From Adresses WHERE id = ?";
    private static final String SQL_SELECT = "SELECT * FROM Adresses WHERE id = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Adresses";



    private static Adresa getAdresa(ResultSet rs) {
        try {
            String carrer = rs.getString("carrer");
            String ciutat = rs.getString("ciutat");
            int idAdresa = rs.getInt("id");
            return new Adresa(idAdresa, carrer, ciutat);
        } catch(SQLException e) {
            e.printStackTrace(System.out);
            return null;
        }
    }

    @Override
    public boolean insert(Adresa adresa) {
        boolean result = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, adresa.getCarrer());
            stmnt.setString(2, adresa.getCiutat());
            result = queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            closeConnection();
        }
        return result;
    }

    @Override
    public boolean update(Adresa adresa) {
        boolean result = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, adresa.getCarrer());
            stmnt.setString(2, adresa.getCiutat());
            stmnt.setInt(3, adresa.getId());
            result = queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            e.printStackTrace(System.out);
        } finally {
            closeConnection();
        }
        return result;

    }

    @Override
    public boolean delete(Object primaryKey) {
        boolean result = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setInt(1, (int) primaryKey);
            result = queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            closeConnection();
        }
        return result;
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
            e.printStackTrace(System.out);
        } finally {
            closeConnection();
        }

        return list;
    }

    @Override
    public Adresa select(Object primaryKey) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            return getAdresa(rs);
        } catch(SQLException e) {
            e.printStackTrace(System.out);
            return null;
        } finally {
            closeConnection();
        }
    }
}
