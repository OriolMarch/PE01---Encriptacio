public class ClasseCriptografica {

    public static String encripta(String missatge, String clau) {

        // XOR + CLAU + POSICIÓ + HEXADECIMAL
        validaDades(missatge, clau);

        // StringBuilder abans de bucle.
        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < missatge.length(); i++) {
            char lletra = missatge.charAt(i);
            char lletraClau = obteLletraClau(clau, i);

            // 1: XOR  entre la lletra i la clau.
            // Compara els dos números bit/bit. 0-1 = 0
            int valor = lletra ^ lletraClau;

            // 2: sumem la clau i la posició.
            // El "% 256" 2 xifres hexadecimals.
            valor = (valor + lletraClau + i) % 256;

            // 3: passem el número a hexadecimal amb 2 xifres.
            resultat.append(String.format("%02X", valor));
        }

        return resultat.toString();
    }

    public static String desencripta(String missatgeXifrat, String clau) {
        validaDades(missatgeXifrat, clau);

        // La longitud es parella si no fora.
        if (missatgeXifrat.length() % 2 != 0) {
            throw new IllegalArgumentException("El missatge xifrat no és vàlid.");
        }

        StringBuilder resultat = new StringBuilder();

        for (int i = 0; i < missatgeXifrat.length() / 2; i++) {
            // 1: agafem les 2 xifres hexadecimals d'aquesta lletra i les passem a número.
            // Exemple: "AA" = 170
            String hex = missatgeXifrat.substring(i * 2, i * 2 + 2);
            int valor = Integer.parseInt(hex, 16);

            char lletraClau = obteLletraClau(clau, i);

            // 2: desfem la suma restant la clau i la posició.
            // Math.floorMod mai dona 2 números negatius.
            valor = Math.floorMod(valor - lletraClau - i, 256);

            // 3: desfem el XOR tornant a fer XOR amb la mateixa clau.
            // Fer XOR dues vegades amb el mateix valor el cancel·la.
            valor = valor ^ lletraClau;

            resultat.append((char) valor);
        }

        return resultat.toString();
    }

    // Retorna la lletra de la clau que toca a cada posició. 
    // El "%" fa que la posicio 3 torna a ser 1.
    private static char obteLletraClau(String clau, int posicio) {
        return clau.charAt(posicio % clau.length());
    }

    // Comprova que les dades siguin vàlides abans de xifrar o desxifrar
    private static void validaDades(String missatge, String clau) {
        if (missatge == null) {
            throw new IllegalArgumentException("El missatge no pot ser null.");
        }
        // Si la clau fos buida, el "%" divideix per zero
        if (clau == null || clau.isEmpty()) {
            throw new IllegalArgumentException("La clau no pot estar buida.");
        }
    }
}