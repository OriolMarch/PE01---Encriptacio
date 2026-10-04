import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class ClasseAES {

    public static String encripta(String missatge, String clau) {
        try {
            // la clau en bytes, ha de fer 16, 24 o 32
            SecretKeySpec clauAES = new SecretKeySpec(clau.getBytes(StandardCharsets.UTF_8), "AES");

            // preparem AES en mode xifrar
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, clauAES);

            // el missatge a bytes i el xifrem
            byte[] xifrat = cipher.doFinal(missatge.getBytes(StandardCharsets.UTF_8));

            // ho passem a Base64 per poder-ho tornar com a text
            return Base64.getEncoder().encodeToString(xifrat);

        } catch (Exception e) {
            System.out.println("Error en encriptar: " + e.getMessage());
            return "";
        }
    }

    public static String desencripta(String missatgeXifrat, String clau) {
        try {
            // la mateixa clau que per encriptar
            SecretKeySpec clauAES = new SecretKeySpec(clau.getBytes(StandardCharsets.UTF_8), "AES");

            // ara en mode desxifrar
            Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, clauAES);

            // traiem el Base64 per tenir els bytes xifrats
            byte[] dades = Base64.getDecoder().decode(missatgeXifrat);

            // desxifrem i tornem a text
            byte[] original = cipher.doFinal(dades);
            return new String(original, StandardCharsets.UTF_8);

        } catch (Exception e) {
            System.out.println("Error en desencriptar: " + e.getMessage());
            return "";
        }
    }
}