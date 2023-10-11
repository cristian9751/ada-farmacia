/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Conexion;

import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author cripoponc
 */
public interface Dao<T> {
    public boolean insert(T objeto) throws SQLException, Exception;
    public boolean update(T objeto) throws Exception;
    public boolean delete(Object primaryKey) throws Exception;
    public List<T> selectAll() throws Exception;
    public T select(Object primaryKey) throws SQLException, Exception;
}
