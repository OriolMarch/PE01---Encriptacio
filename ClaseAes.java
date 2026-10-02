import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Base64;

// Xifrat i desxifrat de missatges amb AES utilitzant l'API de criptografia de Java.
// La clau ha de tenir 16, 24 o 32 bytes (AES-128, AES-192 o AES-256).
public class ClaseAes {

    // Algorisme AES, mode ECB (cada bloc es xifra per separat)
    // i PKCS5Padding (omple l'últim bloc fins als 16 bytes)
    private static final String ALGORISME = "AES/ECB/PKCS5Padding";

    // 1. Rebre un missatge i una clau
    public static String encripta(String missatge, String clau) throws Exception {

        // 2. Preparar la clau perquè pugui ser utilitzada per AES
        SecretKeySpec clauAES = preparaClau(clau);

        // 3. Crear i configurar el sistema de xifrat (mode ENCRIPTAR amb la clau)
        Cipher cipher = Cipher.getInstance(ALGORISME);
        cipher.init(Cipher.ENCRYPT_MODE, clauAES);

        // 4. Convertir el missatge al format necessari: AES treballa amb bytes, no amb text
        byte[] bytesMissatge = missatge.getBytes(StandardCharsets.UTF_8);

        // 5. Xifrar el missatge
        byte[] bytesXifrats = cipher.doFinal(bytesMissatge);

        // 6. Convertir el resultat a String: els bytes xifrats no es poden mostrar
        //    com a text, així que els passem a Base64 (lletres, números, + / =)
        String missatgeXifrat = Base64.getEncoder().encodeToString(bytesXifrats);

        // 7. Retornar el missatge xifrat
        return missatgeXifrat;
    }

    // 1. Rebre el missatge xifrat i la clau
    public static String desencripta(String missatgeXifrat, String clau) throws Exception {

        // 2. Preparar la clau
        SecretKeySpec clauAES = preparaClau(clau);

        // 3. Crear i configurar el sistema de desxifrat (mode DESENCRIPTAR)
        Cipher cipher = Cipher.getInstance(ALGORISME);
        cipher.init(Cipher.DECRYPT_MODE, clauAES);

        // 4. Recuperar les dades xifrades desfent el Base64
        byte[] bytesXifrats = Base64.getDecoder().decode(missatgeXifrat);

        // 5. Desxifrar-les
        byte[] bytesOriginals = cipher.doFinal(bytesXifrats);

        // 6. Convertir el resultat novament a text
        String missatgeOriginal = new String(bytesOriginals, StandardCharsets.UTF_8);

        // 7. Retornar el missatge original
        return missatgeOriginal;
    }

    // Converteix la clau de text en una clau que AES pot utilitzar.
    // Si la clau no té 16, 24 o 32 bytes, Java llançarà una InvalidKeyException a init().
    private static SecretKeySpec preparaClau(String clau) {
        if (clau == null || clau.isEmpty()) {
            throw new IllegalArgumentException("La clau no pot estar buida.");
        }
        byte[] bytesClau = clau.getBytes(StandardCharsets.UTF_8);
        return new SecretKeySpec(bytesClau, "AES");
    }
}