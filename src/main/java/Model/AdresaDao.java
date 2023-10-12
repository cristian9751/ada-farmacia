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
    public Adresa getEntity(ResultSet rs) throws Exception {
        try {
            String carrer = rs.getString("carrer");
            String ciutat = rs.getString("ciutat");
            int idAdresa = rs.getInt("id");
            return new Adresa(idAdresa, carrer, ciutat);
        } catch(SQLException e) {
            throw new Exception("There has been an error fetching the data");
        }
    }


    @Override
    public boolean insert(Adresa adresa) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, adresa.getCarrer());
            stmnt.setString(2, adresa.getCiutat());
            return queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            throw new Exception("User exists");
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean update(Adresa adresa) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_UPDATE);
            stmnt.setString(1, adresa.getCarrer());
            stmnt.setString(2, adresa.getCiutat());
            stmnt.setInt(3, adresa.getId());
            return queryDone(stmnt.executeUpdate());
        } catch(SQLException e) {
            select(adresa.getId());
            throw new Exception("Data unchanged");
        } finally {
            closeConnection();
        }
    }

    @Override
    public boolean delete(Object primaryKey) throws Exception {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_DELETE);
            stmnt.setInt(1, (int) primaryKey);
            return queryDone(stmnt.executeUpdate());
        } catch (SQLException e) {
            throw new Exception("User does not exist");
        } finally {
            closeConnection();
        }

    }

    @Override
    public List<Adresa> selectAll() throws Exception {
        List<Adresa> list = new ArrayList<>();
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECTALL);
            ResultSet rs = stmnt.executeQuery();
            while(rs.next()) {
                list.add(getEntity(rs));
            }
        } catch(Exception e) {
            throw new Exception("There has been an error fetching the data");
        } finally {
            closeConnection();
        }

        return list;
    }

    @Override
    public Adresa select(Object primaryKey) throws Exception {
        Adresa adresa;
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_SELECT);
            stmnt.setInt(1, (int) primaryKey);
            ResultSet rs = stmnt.executeQuery();
            rs.next();
            adresa = getEntity(rs);
        } catch(Exception e) {
            throw new Exception("User does not exist");
        } finally {
            closeConnection();
        }
        return adresa;
    }
}
