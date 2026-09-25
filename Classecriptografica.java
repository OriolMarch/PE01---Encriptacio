import java.nio.charset.StandardCharsets;

public class Classecriptografica {

    public static String encripta(String missatge, String clau) {
        comprovaClau(clau);

        byte[] bytesMissatge = missatge.getBytes(StandardCharsets.UTF_8);
        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);
    
    }

}