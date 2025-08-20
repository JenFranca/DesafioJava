import java.util.Scanner;

public class BubbleSort {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Quantos números você quer ordenar? ");
        int quantidade = scanner.nextInt();

        int[] numeros = new int[quantidade];

        for (int i = 0; i < quantidade; i++) {
            System.out.print("Digite o número " + (i + 1) + ": ");
            numeros[i] = scanner.nextInt();
        }

        System.out.println("\nVetor original:");
        for (int i = 0; i < quantidade; i++) 
        {
            System.out.print(numeros[i] + " ");
        }

        for (int i = 0; i < quantidade - 1; i++) 
        {
            for (int j = 0; j < quantidade - 1 - i; j++) {
                if (numeros[j] > numeros[j + 1]) {
                    // Trocar os valores
                    int temp = numeros[j];
                    numeros[j] = numeros[j + 1];
                    numeros[j + 1] = temp;
                }
            }
        }


        System.out.println("\n\nVetor ordenado:");
        for (int i = 0; i < quantidade; i++) 
        {
            System.out.print(numeros[i] + " ");
        }

        scanner.close();
    }
}
