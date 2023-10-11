package Domain.Controller;

import Domain.Entity.Adresa;
import Model.AdresaDao;

import java.sql.SQLException;
import java.util.List;
import java.util.function.Function;

public class AdresaController {

    private static final AdresaDao adresaDao = new AdresaDao();
    public boolean insertAdressa(String carrer, String ciutat, Function<Adresa, Void> inserted, Function<Exception, Void> error) {
        try {
            Adresa newAdresa = new Adresa(carrer, ciutat);
            adresaDao.insert(newAdresa);
            inserted.apply(newAdresa);
            return true;
        } catch (Exception e) {
            error.apply(e);
            return false;
        }
    }

    public boolean deleteAdresa(int idAdresa, Function<Integer, Void> deleted, Function<Exception, Void> error) {
        try {
            adresaDao.delete(idAdresa);
            deleted.apply(idAdresa);
            return true;
        } catch (Exception e) {
            error.apply(e);
            return false;
        }
    }

    public Adresa getAdresaById(int idAdresa, Function<Exception, Void> error) {
        try {
            return adresaDao.select(idAdresa);
        } catch (Exception e) {
            error.apply(e);
            return null;
        }
    }

    public List<Adresa> getAllAdresses(Function<Exception, Void> error) {
        try {
            return adresaDao.selectAll();
        } catch (Exception e) {
            error.apply(e);
            return null;
        }
    }


    public boolean updateAdressa(
            Adresa updatingAdresa,
            Function<Adresa, Void> wrongUser,
            Function<Adresa, Void> dataUnchanged,
            Function<Exception, Void> error) {
        try {
            return adresaDao.update(updatingAdresa);
        } catch (Exception e) {
            if(e.getMessage().equalsIgnoreCase("User does not exist")) {
                wrongUser.apply(updatingAdresa);
            } else if(e.getMessage().equalsIgnoreCase("Data unchanged")) {
                dataUnchanged.apply(updatingAdresa);
            } else {
                error.apply(e);
            }
            return false;
        }

    }
}
