/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import static Conexion.Conexion.*;
import Conexion.Dao;
import Domain.Entity.Adresa;

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
    private static final String SQL_SELECTALL = "SELECT * FROM Adresses";
    private static final String SQL_SELECT = SQL_SELECTALL + " WHERE id = ?";


    @Override
    public Adresa getEntity(ResultSet rs) {
        Adresa adresa = null;
        try {
            String carrer = rs.getString("carrer");
            String ciutat = rs.getString("ciutat");
            int idAdresa = rs.getInt("id");
            adresa = new Adresa(idAdresa, carrer, ciutat);
        } catch(SQLException e) {
            e.printStackTrace(System.err);
        }
        return adresa;
    }


    @Override
    public boolean insert(Adresa adresa) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, adresa.getCarrer());
            stmnt.setString(2, adresa.getCiutat());
            res = queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean update(Adresa adresa) {
        boolean res = false;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, adresa.getCarrer());
            stmnt.setString(2, adresa.getCiutat());
            stmnt.setInt(3, adresa.getId());
            res = queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return res;
    }

    @Override
    public boolean delete(Object primaryKey){
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
    public List<Adresa> selectAll()  {
        List<Adresa> list = new ArrayList<>();
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
    public Adresa select(Object primaryKey) {
        Adresa adresa = null;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            adresa = getEntity(rs);
        } catch(Exception e) {
            e.printStackTrace(System.err);
        } finally {
            closeConnection();
        }
        return adresa;
    }
}
