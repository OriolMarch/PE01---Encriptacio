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

}
}
