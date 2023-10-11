package ada.farmaciacristian.Utils;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Function;
import java.util.regex.Pattern;

public class Input {
    public static Object getObject(String showText, Function<Scanner, Object> getType, Function<InputMismatchException, Object> error) {
        Scanner in = new Scanner(System.in);
        Object response = null;
        Boolean isValid = true;
        do {
            System.out.println(showText);
            try {
                response = getType.apply(in);
            } catch(InputMismatchException e) {
                error.apply(e);
                isValid = false;
            }
        }  while(!isValid);

        return response;
    }


    public static String getString(String showText) {
        return (String) getObject(showText, (in) -> in.nextLine(), (e) -> "Debes introducir una cadena de caracteres alfanumerica");
    };

    public static Integer getInteger(String showText) {
        return (Integer) getObject(showText, (in) -> in.nextInt(), (e) -> "Debes introducir un numero entero");
    };

    public static Double getDouble(String showText) {
        return (Double) getObject(showText, (in) -> in.nextDouble(), (e) -> "Invalido. Por favor, introduce un numero con decimales(Ejemplo: 3.14)");
    }

    public static boolean checkRegex(Expressions expression, Object value, String showText) {
        boolean isValid = false;
        while(!isValid) {
            System.out.println(showText);
            Pattern pattern = Pattern.compile(expression.getPattern());
            isValid = pattern.matcher(value.toString()).find();
        }
        return isValid;
    };

}

