package Model;

import Domain.Entity.Adresa;
import Domain.Entity.Emplea;
import Domain.Entity.Farmaceutic;
import Domain.Entity.Farmacia;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

import static Conexion.Conexion.closeConnection;
import static Conexion.Conexion.getConnection;
import static org.junit.jupiter.api.Assertions.*;

class EmpleaDAOTest {
    private static Connection conexion = getConnection();
    private static EmpleaDAO empleaDAO = new EmpleaDAO();

   private static Adresa adresa = new Adresa("Carrer A", "Provincia A");

   private static Farmacia farmacia = new Farmacia(adresa, "cifFarmacia", true);

   private static Farmacia farmacia_dos = new Farmacia(adresa, "cifSegundaFarmacia", true);

   private static Farmaceutic farmaceutic = new Farmaceutic("1661882E", "ElFarmaceutic" ,"Cognom1", "Cognom2" , new Date(2001), true);
    private static Emplea empleaWithoutId = new Emplea(farmacia, farmaceutic);
    void settings() throws SQLException {
        String INSERT_FARMACIA = "INSERT INTO Farmacia VALUES VALUES('cifFarmacia', 1, true)";
        String INSERT_FARMACEUTIC = "INSERT INTO Farmaceutic VALUES"
        String DELETE = " DELETE FROM Emplea";
        String RESET_AI = "ALTER TABLE Emplea AUTO_INCREMENT=1";
        conexion.prepareStatement(INSERT_FARMACIA).execute();
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
        assertTrue(empleaDAO.insert(empleaWithoutId));
    }

    @Test
    void update() throws Exception {
        insert();
        Emplea empleaWithId = empleaDAO.select(1);
        empleaWithId.setFarmacia(farmacia_dos);
        assertTrue(empleaDAO.update(empleaWithId));
    }

    @Test
    void delete() throws Exception {
        insert();
        assertTrue(empleaDAO.delete(1));
    }

    @Test
    void selectAll() throws Exception {
        insert();
        assertNotNull(empleaDAO.selectAll());
    }

    @Test
    void select() throws Exception {
        insert();
        assertNotNull(empleaDAO.select(1));
    }

    @AfterEach
    void tearDown() throws Exception {
        settings();
    }
}