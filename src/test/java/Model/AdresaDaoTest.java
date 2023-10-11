package Model;

import Domain.Entity.Adresa;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static Conexion.Conexion.*;

import static org.junit.jupiter.api.Assertions.*;

class AdresaDaoTest {

    private static AdresaDao adresaDAO = new AdresaDao();
    private static Connection conexion = getConnection();

    private static Adresa adresaWithoutId = new Adresa("Carrer A", "Provincia A");
    private static AdresaDao adresaDao = new AdresaDao();

    void settings() throws SQLException {
        String DELETE = " DELETE FROM Adresses";
        String RESET_AI = "ALTER TABLE Adresses AUTO_INCREMENT=1";
        conexion.prepareStatement(DELETE).execute();
        conexion.prepareStatement(RESET_AI).execute();
        closeConnection();
    }

    @BeforeEach
    void setUp() throws SQLException{
       settings();
    }


    @Test
    void insert() throws Exception {
       assertTrue(adresaDao.insert(adresaWithoutId));
    }

    @Test
    void insertExistent() throws Exception {
        insert();
        try {
            adresaDao.insert(adresaWithoutId);
        } catch (Exception e) {
            assertEquals("User exists", e.getMessage());
        }
    }


    @Test
    void update() throws Exception {
        insert();
        Adresa adresaWithId = adresaDAO.select(1);
        adresaWithId.setCiutat("Burgos");
        assertTrue(adresaDao.update(adresaWithId));
    }

    @Test
    void updateNonExistant() {
        try {
            adresaDao.update(adresaWithoutId);
        } catch (Exception e) {
            assertEquals("User does not exist", e.getMessage());
        }
    }
    @Test
    void delete() throws Exception {
        insert();
        assertTrue(adresaDAO.delete(1));
    }

    @Test
    void selectAll() throws Exception {
        assertNotNull(adresaDao.selectAll());
    }

    @Test
    void select() throws Exception {
        insert();
        assertNotNull(adresaDao.select(1));
    }

    @Test
    void selectNonExistant() {
        try {
            adresaDao.select(1);
        } catch (Exception e) {
            assertEquals("User does not exist", e.getMessage());
        }
    }
    @Test
    void deleteNonExistant(){
        try {
            adresaDAO.delete(1);
        } catch (Exception e) {
            assertEquals("User does not exist", e.getMessage());
        }
    }

    @Test
    void updateWithoutChanges() throws Exception{
        insert();
        adresaWithoutId.setCarrer("Carrer A");
        try {
            adresaDao.update(adresaWithoutId);
        } catch (Exception e) {
            assertEquals("Data unchanged", e.getMessage());
        }
    }


    @AfterEach
    void tearDown() throws SQLException {
        settings();
    }
}