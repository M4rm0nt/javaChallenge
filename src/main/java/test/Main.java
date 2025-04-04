package test;

public class Main {

    public static void main(String[] args) {
        System.out.println("Einfaches Command-Line Tool");

        // Überprüfen ob Parameter übergeben wurden
        if (args.length == 0) {
            System.out.println("Keine Parameter übergeben.");
            System.out.println("Verwendung: java SimpleCLI <Parameter1> <Parameter2> ...");
            return;
        }

        // Ausgabe der übergebenen Parameter
        System.out.println("Übergebene Parameter:");
        for (int i = 0; i < args.length; i++) {
            System.out.println((i+1) + ": " + args[i]);
        }
    }
}
