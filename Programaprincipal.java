import java.util.Scanner;

// Programa principal: utilitza la ClasseCriptografica
public class Programaprincipal {

    public static void main(String[] args) {
        Scanner teclat = new Scanner(System.in);


        String clau = "123";

        // Test 1: missatge curt
        String missatge1 = "HOLA";
        String xifrat1 = ClasseCriptografica.encripta(missatge1, clau);
        System.out.println("Test 1");
        System.out.println("Original: " + missatge1);
        System.out.println("Encriptat: " + xifrat1);
        System.out.println("Desencriptat: " + ClasseCriptografica.desencripta(xifrat1, clau));
        System.out.println();

        // Test 2: missatge amb espais
        String missatge2 = "HOLA MÓN";
        String xifrat2 = ClasseCriptografica.encripta(missatge2, clau);
        System.out.println("Test 2");
        System.out.println("Original: " + missatge2);
        System.out.println("Encriptat: " + xifrat2);
        System.out.println("Desencriptat: " + ClasseCriptografica.desencripta(xifrat2, clau));
        System.out.println();

        // Test 3: missatge de més de 30 caràcters
        String missatge3 = "Aquest missatge té més de trenta caràcters";
        String xifrat3 = ClasseCriptografica.encripta(missatge3, clau);
        System.out.println("Test 3");
        System.out.println("Original: " + missatge3);
        System.out.println("Encriptat: " + xifrat3);
        System.out.println("Desencriptat: " + ClasseCriptografica.desencripta(xifrat3, clau));
        System.out.println();

        // Test 4: amb una clau diferent el resultat canvia
        System.out.println("Test 4");
        System.out.println("HOLA amb clau 3: " + ClasseCriptografica.encripta("HOLA", "3"));
        System.out.println("HOLA amb clau 7: " + ClasseCriptografica.encripta("HOLA", "7"));
        System.out.println();

        // Test 5: es recupera exactament el missatge original
        String recuperat = ClasseCriptografica.desencripta(xifrat1, clau);
        System.out.println("Test 5");
        System.out.println("Missatge recuperat: " + recuperat);
        System.out.println("És igual que l'original? " + missatge1.equals(recuperat));
        System.out.println();

        // Test 6: amb una clau incorrecta no es recupera el missatge
        System.out.println("Test 6");
        System.out.println("Desencriptat amb clau abc: " + ClasseCriptografica.desencripta(xifrat1, "abc"));
        System.out.println();


        System.out.println("Prova amb les teves dades");
        System.out.print("Escriu el missatge: ");
        String missatgeUsuari = teclat.nextLine();
        System.out.print("Escriu la clau: ");
        String clauUsuari = teclat.nextLine();

        try {
            String xifratUsuari = ClasseCriptografica.encripta(missatgeUsuari, clauUsuari);
            System.out.println("Encriptat: " + xifratUsuari);
            System.out.println("Desencriptat: " + ClasseCriptografica.desencripta(xifratUsuari, clauUsuari));
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }

        teclat.close();
    }
}