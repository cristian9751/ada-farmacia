/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Conexion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cripoponc
 */
public interface Dao<T> {
     T getEntity(ResultSet rs) throws Exception;
     boolean insert(T objeto) throws Exception;
     boolean update(T objeto) throws Exception;
    boolean delete(Object primaryKey) throws Exception;
    List<T> selectAll() throws Exception;
    T select(Object primaryKey) throws  Exception;
}
