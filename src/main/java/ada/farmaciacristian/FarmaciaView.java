package ada.farmaciacristian;
import static ada.farmaciacristian.Utils.Input.*;
import static ada.farmaciacristian.Utils.Expressions.*;

import Domain.Controller.AdresaController;
import Domain.Controller.AdresaController.*;
public class FarmaciaView {

    AdresaController adresaController = new AdresaController();
    private void createAdresa() {
        boolean isCreated = false;
        String ciutat = getString("Indica la ciutat en la cual esta ubicada la farmacia");
        String carrer = getString("Indica el carer en el cual esta ubicada la farmacia");
        if(checkRegex(STREET, carrer, "La direccion debe incluir al inicio el codigo postal de la farmacia seguido de la dirección"))
            adresaController.insertAdressa(carrer, ciutat, (adresa) -> {
                return null;
            }, (e) -> {
                return null;
            });
    }


}
