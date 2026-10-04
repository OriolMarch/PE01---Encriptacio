
public class ProgramaPrincipalAES {

    public static void main(String[] args) {
        ProgramaPrincipalAES programa = new ProgramaPrincipalAES();
        programa.inici();
    }

    public void inici() {
        String missatge = "Aquest és un missatge secret.";
        String clau = "1234567890123456";

        // 6.3 el programa bàsic
        System.out.println("Missatge original:  " + missatge);
        String xifrat = ClasseAES.encripta(missatge, clau);
        System.out.println("Missatge xifrat:    " + xifrat);
        String recuperat = ClasseAES.desencripta(xifrat, clau);
        System.out.println("Missatge recuperat: " + recuperat);
        System.out.println("Són iguals? " + recuperat.equals(missatge));

        // 6.4 les proves
        System.out.println("\n--- Prova 1: clau correcta ---");
        System.out.println("Resultat: " + ClasseAES.desencripta(xifrat, clau));

        System.out.println("\n--- Prova 2: clau diferent ---");
        System.out.println("Resultat: " + ClasseAES.desencripta(xifrat, "6543210987654321"));

        System.out.println("\n--- Prova 3: missatge diferent ---");
        String altre = "Demà quedem a les 5 a la porta de l'escola";
        String altreXifrat = ClasseAES.encripta(altre, clau);
        System.out.println("Xifrat:    " + altreXifrat);
        System.out.println("Recuperat: " + ClasseAES.desencripta(altreXifrat, clau));

        System.out.println("\n--- Prova 4: clau de longitud incorrecta ---");
        System.out.println("Resultat: " + ClasseAES.encripta(missatge, "12345"));
    }
}