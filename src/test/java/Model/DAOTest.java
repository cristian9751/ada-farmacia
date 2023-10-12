package Model;

import Conexion.Dao;
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
import java.util.function.Function;

import static Conexion.Conexion.*;

import static org.junit.jupiter.api.Assertions.*;

class DAOTest<U> {

    private Dao<U> dao;
    private static Connection conexion = getConnection();
    private U itemWithoutId;

    private String[] querys;

    private String tableName;

    private Function<Object, Void> updateItemInstance;

    private Object primaryKey = 1;
    public DAOTest(String tableName, Dao<U> dao, U itemWithoutId, Function<Object, Void> updateItemInstance) {
        this.tableName = tableName;
        this.dao = dao;
        this.itemWithoutId = itemWithoutId;
        this.updateItemInstance = updateItemInstance;
    }
    public DAOTest(String tableName, String[] querys, Dao<U> dao, U itemWithoutId, Function<Object, Void> updateItemInstance) {
        this(tableName, dao, itemWithoutId, updateItemInstance);
        this.querys = querys;
    }

    public DAOTest(String tableName, String[] querys, Dao<U> dao, U itemWithoutId, Function<Object, Void> updateItemInstance, Object primaryKey) {
        this(tableName, querys, dao, itemWithoutId, updateItemInstance);
        this.primaryKey = primaryKey;
    }



    void executeSQL(String query) throws SQLException {
        conexion.prepareStatement(query).execute();
        closeConnection();
    }


    void settingUpSQL() throws SQLException {
        String DELETE = "DELETE FROM " + tableName;
        String RESET_AI = "ALTER TABLE " + tableName + "AUTO_INCREMENT = 1";
        executeSQL(DELETE);
        executeSQL(RESET_AI);
    }

    @BeforeEach
    void setUp() throws SQLException{
        settingUpSQL();
    }


    @Test
    void insert() throws Exception {
       assertTrue(dao.insert(itemWithoutId));
    }

    @Test
    void insertExistent() throws Exception {
        insert();
        try {
            dao.insert(itemWithoutId);
        } catch (Exception e) {
            assertEquals(itemWithoutId.getClass().getName()+  " exists", e.getMessage());
        }
    }


    @Test
    void update() throws Exception {
        insert();
        U itemWithId = dao.select(1);
        updateItemInstance.apply(itemWithId);
        assertTrue(dao.update(itemWithId));
    }

    @Test
    void updateNonExistant() {
        try {
            dao.update(itemWithoutId);
        } catch (Exception e) {
            assertEquals( itemWithoutId.getClass().getName() + " does not exist", e.getMessage());
        }
    }
    @Test
    void delete() throws Exception {
        insert();
        assertTrue(dao.delete(1));
    }

    @Test
    void selectAll() throws Exception {
        assertNotNull(dao.selectAll());
    }

    @Test
    void select() throws Exception {
        insert();
        assertNotNull(dao.select(1));
    }

    @Test
    void selectNonExistant() {
        try {
            dao.select(1);
        } catch (Exception e) {
            assertEquals(itemWithoutId.getClass().getName() + " does not exist", e.getMessage());
        }
    }
    @Test
    void deleteNonExistant(){
        try {
            dao.delete(1);
        } catch (Exception e) {
            assertEquals(itemWithoutId.getClass().getName() + " does not exist", e.getMessage());
        }
    }

    @Test
    void updateWithoutChanges() throws Exception{
        insert();
        U itemWithId = dao.select(1);
        try {
            dao.update(itemWithId);
        } catch (Exception e) {
            assertEquals("Data unchanged", e.getMessage());
        }
    }


    @AfterEach
    void tearDown() throws SQLException {
        settingUpSQL();
    }
}

class TestingDAO {
    private static Adresa adresa = new Adresa("Carrer A", "Provincia A");
    private static Farmacia farmacia = new Farmacia("CifFarmacia1", adresa);

    private static AdresaDao adresaDao = new AdresaDao();

    private static Farmaceutic farmaceutic = new Farmaceutic("DNI FARMACEUTIC 1", "Nombre Farmaceutico", "Primer apellido"
    , "Segundo apellido", new Date(2009), true);

    private static Emplea emplea = new Emplea(farmacia, farmaceutic);

    private static Farmaceutic farmaceutic2 = new Farmaceutic("DNI FARMACEUTIC 1", "Nombre Farmaceutico", "Primer apellido"
            , "Segundo apellido", new Date(2009), false);
    @Test
    void AdresaDAOTest() {
        new DAOTest<>("adresses", new AdresaDao(), adresa, (adresa) -> {
            ((Adresa) adresa).setCiutat("Burgos");
            return null;
        });
    }

    @Test
    void FarmaciaDAOTest() throws Exception {
        try {
            adresaDao.insert(adresa);
        } catch (Exception e) {
            adresaDao.delete(adresa.getId());
        }
        new DAOTest<>("farmacia", new FarmaciaDAO(), farmacia, (farmacia) -> {
            ((Farmacia) farmacia).setCif("CifFarmacia1Modificado");
            return null;
        });

        adresaDao.delete(1);
    }

    @Test
    void FarmaceuticDAOTest(){
        new DAOTest<>("farmaceutic", new FarmaceuticDAO(), farmaceutic, (farmaceutic) -> {
            ((Farmaceutic) farmaceutic).setNom("Rogelio");
            return null;
        });
    }

    @Test
    void EmpleaDAOTest() throws Exception {
        try {
            new FarmaciaDAO().insert(farmacia);
            new FarmaceuticDAO().insert(farmaceutic);
        } catch (Exception e) {
            new FarmaciaDAO().delete(farmacia.getCif());
            new FarmaceuticDAO().delete(farmaceutic.getDni());

        }

        new DAOTest<>("emplea", new EmpleaDAO(), emplea, (emplea) -> {
            ((Emplea) emplea).setFarmaceutic(farmaceutic2);
            return null;
        });

        new FarmaciaDAO().delete(1);
        new FarmaceuticDAO().delete(1);
    }



 }

