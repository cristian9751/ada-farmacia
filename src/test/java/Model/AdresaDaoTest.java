package Model;

import Domain.Adresa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import static Conexion.Conexion.*;

import static org.junit.jupiter.api.Assertions.*;

class AdresaDaoTest {

    private static AdresaDao adresaDAO = new AdresaDao();
    private static Connection conexion = getConnection();

    private static Adresa adresaWithoutId = new Adresa("Carrer A", "Provincia A");
    private static AdresaDao adresaDao = new AdresaDao();


    @BeforeEach
    void setUp() throws SQLException {
        String DELETE = " DELETE FROM Adresses";
        String RESET_AI = "ALTER TABLE Adresses AUTO_INCREMENT=1";
        conexion.prepareStatement(DELETE).execute();
        conexion.prepareStatement(RESET_AI).execute();
        closeConnection();
    }


    @Test
    void insert() {
       assertTrue(adresaDao.insert(adresaWithoutId));
    }

    @Test
    void insertExistent() {
        insert();
        assertFalse(adresaDao.insert(adresaWithoutId));
    }


    @Test
    void update() {
        insert();
        Adresa adresaWithId = adresaDAO.select(1);
        adresaWithId.setCiutat("Burgos");
        assertTrue(adresaDao.update(adresaWithId));
    }

    @Test
    void updateNonExistant() {
        assertFalse(adresaDao.update(adresaWithoutId));
    }
    @Test
    void delete() {
        insert();
        assertTrue(adresaDAO.delete(1));
    }

    @Test
    void selectAll() {
        assertNotNull(adresaDao.selectAll());
    }

    @Test
    void select() {
        insert();
        assertNotNull(adresaDao.select(1));
    }

    @Test
    void selectNonExistant() {
        assertNull(adresaDao.select(1));
    }
    @Test
    void deleteNonExistant() {
        assertFalse(adresaDAO.delete(1));
    }

    @Test
    void updateWithoutChanges() {
        insert();
        adresaWithoutId.setCarrer("Carrer A");
        assertFalse(adresaDao.update(adresaWithoutId));
    }
}