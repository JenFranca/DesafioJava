///usr/bin/env jbang "$0" "$@" ; exit $?

import java.util.Scanner;

public class Main 
{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcao;

        do {
            System.out.println("\nMenu:");
            System.out.println("1 - Eleição");
            System.out.println("2 - BubbleSort");
            System.out.println("3 - Fatorial");
            System.out.println("0 - Sair");
            System.out.print("Escolha uma opção: ");

            opcao = scanner.nextInt();

            switch (opcao) {
                case 1:
                    Votos.main(args);
                    break;
                case 2:
                    BubbleSort.main(args);
                    break;
                case 3:
                    Fatorial.main(args);
                    break;
                case 0:
                    System.out.println("Saindo...");
                    break;
                default:
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);

        scanner.close();
    }
}