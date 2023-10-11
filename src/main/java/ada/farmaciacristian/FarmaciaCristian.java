/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Project/Maven2/JavaApp/src/main/java/${packagePath}/${mainClassName}.java to edit this template
 */

package ada.farmaciacristian;

import com.sun.jdi.VoidType;

import java.util.function.Function;

import static ada.farmaciacristian.Utils.Input.*;

/**
 *
 * @author cripoponc
 */
public class FarmaciaCristian {

    private static final String MAIN_MENU = """
            1. Farmacia
            2. Farmaceutic
            3. Medicament
            4. Metge
            5. Pacient
            6. Prescripcio
            7. Cita o Tractament
            8. Venta de medicaments
            9. Salir
            """;

    private static final String CRUD_MENU = """
            1. Insertar
            2. Esborrar
            3. Modificar informacio
            4. Buscar per id
            5. Veure tots
            """;

    protected void manageCrudMenu(
            Function<Void, Void> insert,
            Function<Void, Void> delete,
            Function<Void, Void> update,
            Function<Void, Void> select,
            Function<Void, Void> selectAll
    ) {
        int selectedOption = getInteger(CRUD_MENU, "Debes introducir una de  las cinco opciones numericas");
        boolean isValid = true;
        do {
            switch (selectedOption) {
                case 1:
                    insert.apply(null);
                    break;
                case 2:
                    delete.apply(null);
                case 3:
                    update.apply(null);
                case 4:
                    select.apply(null);
                case 5:
                    selectAll.apply(null);
                    break;
                default:
                    System.out.println("Has de seleccionar una de les cinc opcions");
                    isValid = false;
            }
        } while(!isValid);

    }


}
