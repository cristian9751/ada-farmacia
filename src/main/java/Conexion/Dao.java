/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Conexion;

import java.util.List;

/**
 *
 * @author cripoponc
 */
public interface Dao<T> {
    public boolean insert(T objeto);
    public boolean update(T objeto);
    public boolean delete(Object primaryKey);
    public List<T> selectAll();
    public T select(Object primaryKey);
}
