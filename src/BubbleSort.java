package src;
import java.util.Scanner;

public class BubbleSort 
    {
        public static void main(String[] args) 
        {
            Scanner scanner = new Scanner(System.in); //leitura dos numeros

            System.out.print("Quantos números você quer ordenar? ");
            System.out.print("\nEles serão informados por você na próxima etapa:");
            int quantidade = scanner.nextInt();

            int[] numeros = new int[quantidade]; //quantidade que o usuario pediu

            for (int i = 0; i < quantidade; i++) {
                System.out.print("Digite o número " + (i + 1) + ": "); //os numeros que o cliente digitou
                numeros[i] = scanner.nextInt();  //salva
        }

         System.out.println("\nVetor original:"); // como o cliente digitou
            for (int i = 0; i < quantidade; i++) 
            {
                System.out.print(numeros[i] + " ");
            }

            int totalTrocas = 0;

            // Bubble Sort
            for (int i = 0; i < quantidade - 1; i++) 
            {
                // compara os números lado a lado
                for (int j = 0; j < quantidade - 1 - i; j++) 
                {
                    if (numeros[j] > numeros[j + 1]) 
                    {
                        // se o da esquerda for maior, troca de lugar
                        int temp = numeros[j];
                        numeros[j] = numeros[j + 1];
                        numeros[j + 1] = temp;

                        totalTrocas++; // conta a troca

                        // print como o vetor ficou depois da troca
                        System.out.print("\nApós troca " + totalTrocas + ": ");
                        for (int k = 0; k < quantidade; k++) 
                        {
                            System.out.print(numeros[k] + " ");
                        }
                    }
                }
            }

            // print o vetor ordenado
            System.out.println("\n\nVetor ordenado:");
            for (int i = 0; i < quantidade; i++) 
            {
                System.out.print(numeros[i] + " ");
            }
            // print quantas trocas foram feitas 
            System.out.println("\n\nTotal de trocas realizadas: " + totalTrocas);

            scanner.close();
        }
    }
