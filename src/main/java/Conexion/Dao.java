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
     T getEntity(ResultSet rs) ;
     boolean insert(T objeto);
     boolean update(T objeto);
    boolean delete(Object primaryKey);
    List<T> selectAll() ;
    T select(Object primaryKey);
}
