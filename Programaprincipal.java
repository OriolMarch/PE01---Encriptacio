// Programa principal: utilitza la ClasseCriptografica i executa els tests de la pràctica
public class Programaprincipal {

    public static void main(String[] args) {

        System.out.println("===== SISTEMA DE XIFRAT XOR-Pos =====\n");

        // Tests 1, 2 i 3: encriptar i desencriptar diferents missatges
        provaCompleta("Test 1 - Missatge curt", "HOLA", "123");

        provaCompleta("Test 2 - Missatge amb espais", "HOLA MÓN", "123");

        provaCompleta("Test 3 - Missatge llarg",
                "Aquest missatge té més de trenta caràcters", "123");

        // Test 4: amb una clau diferent el resultat ha de canviar
        System.out.println("--- Test 4 - Clau diferent ---");
        String xifrat3 = ClasseCriptografica.encripta("HOLA", "3");
        String xifrat7 = ClasseCriptografica.encripta("HOLA", "7");
        System.out.println("HOLA + clau 3 -> " + xifrat3);
        System.out.println("HOLA + clau 7 -> " + xifrat7);
        mostraResultat(!xifrat3.equals(xifrat7));

        // Test 5: s'ha de recuperar exactament el missatge original
        System.out.println("--- Test 5 - Encriptar i desencriptar ---");
        String original = "HOLA";
        String recuperat = ClasseCriptografica.desencripta(
                ClasseCriptografica.encripta(original, "123"), "123");
        System.out.println("Desencripta(Encripta(\"HOLA\", \"123\"), \"123\") = " + recuperat);
        mostraResultat(original.equals(recuperat));

        // Test 6: amb una clau incorrecta no es recupera el missatge
        System.out.println("--- Test 6 - Clau incorrecta ---");
        String xifrat = ClasseCriptografica.encripta("HOLA", "123");
        String ambClauDolenta = ClasseCriptografica.desencripta(xifrat, "999");
        System.out.println("Xifrat amb \"123\":             " + xifrat);
        System.out.println("Desencriptat amb \"999\":       " + ambClauDolenta);
        System.out.println("No es recupera HOLA, surten caràcters sense sentit.");
        mostraResultat(!"HOLA".equals(ambClauDolenta));

        // Prova extra: la mateixa lletra repetida dona valors diferents
        System.out.println("--- Prova extra - Lletres repetides ---");
        System.out.println("AAAA + clau 123 -> " + ClasseCriptografica.encripta("AAAA", "123"));
        System.out.println("Cada A dona un valor diferent gràcies a la posició.\n");

        // Prova extra: mateix missatge i mateixa clau donen el mateix resultat
        System.out.println("--- Prova extra - Resultat repetible ---");
        boolean igual = ClasseCriptografica.encripta("HOLA", "123")
                .equals(ClasseCriptografica.encripta("HOLA", "123"));
        System.out.println("Encriptar dues vegades HOLA amb 123 dona el mateix resultat: " + igual);
        mostraResultat(igual);
    }

    // Encripta, desencripta i comprova que es recupera el missatge original
    private static void provaCompleta(String nomTest, String missatge, String clau) {
        System.out.println("--- " + nomTest + " ---");
        String xifrat = ClasseCriptografica.encripta(missatge, clau);
        String recuperat = ClasseCriptografica.desencripta(xifrat, clau);

        System.out.println("Original:     " + missatge);
        System.out.println("Clau:         " + clau);
        System.out.println("Encriptat:    " + xifrat);
        System.out.println("Desencriptat: " + recuperat);
        mostraResultat(missatge.equals(recuperat));
    }

    // Mostra CORRECTE o INCORRECTE segons el resultat del test
    private static void mostraResultat(boolean correcte) {
        System.out.println("Resultat: " + (correcte ? "CORRECTE" : "INCORRECTE") + "\n");
    }
}