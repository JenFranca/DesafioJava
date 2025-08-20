package src;
import java.util.Scanner;

public class SomaMultiplos {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Digite um número: ");
        int numero = scanner.nextInt();

        int soma = 0;

        System.out.print("Números múltiplos de 3 ou 5 abaixo de " + numero + ": ");

        for (int i = 1; i < numero; i++) {
            if (i % 3 == 0 || i % 5 == 0) {
                System.out.print(i + " "); // Lê os múltiplos
                soma += i;
            }
        }

        System.out.println("\nA soma desses multiplos " + numero + " dá: " + soma);
        
        scanner.close();
    }
}
