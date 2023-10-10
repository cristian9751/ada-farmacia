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


    public static String getString(String showText, String errorText) {
        return (String) getObject(showText, (in) -> in.nextLine(), (e) -> errorText);
    };

    public static Integer getInteger(String showText, String errorText) {
        return (Integer) getObject(showText, (in) -> in.nextInt(), (e) -> errorText);
    };

    public static Double getDouble(String showText, String errorText) {
        return (Double) getObject(showText, (in) -> in.nextDouble(), (e) -> errorText);
    }

    public static boolean checkRegex(Expressions expression, Object value) {
        Pattern pattern = Pattern.compile(expression.getPattern());
        return pattern.matcher(value.toString()).find();
    };

}

