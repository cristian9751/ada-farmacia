package Model;

import Conexion.Dao;
import Domain.Especialitat;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import static Conexion.Conexion.*;

public class EspecialitatDAO implements Dao<Especialitat> {

    private static final String SQL_INSERT = "INSERT INTO Especialitat (nom) VALUES (?)";
    private static final String SQL_UPDATE = "UPDATE Especialitat SET nom = ?";

    private static final String SQL_DELETE = "DELETE FROM Especialitat WHERE idEspecialitat = ?";
    private static final String SQL_SELECTALL = "SELECT * FROM Especialitat";

    private static final String SQL_SELECT = "SELECT * FROM Especialitat  WHERE idEspecialitat = ?";
    @Override
    public boolean insert(Especialitat especialitat) {
        try {
            PreparedStatement stmnt = getConnection().prepareStatement(SQL_INSERT);
            stmnt.setString(1, especialitat.getNom());
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
    public boolean update(Especialitat objeto) {
        PreparedStatement stmnt = gtg
    }

    @Override
    public boolean delete(Object primaryKey) {
        return false;
    }

    @Override
    public List<Especialitat> selectAll() {
        return null;
    }

    @Override
    public Especialitat select(Object primaryKey) {
        return null;
    }
}
