import java.util.Scanner;

public class Fatorial {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // criar o scanner

        System.out.print("Digite um número: ");
        int numero = scanner.nextInt(); // ler número do usuário

        scanner.close(); 

        // Verificar se o número é negativo
        if (numero < 0) {
            System.out.println("Não existe fatorial de número negativo.");
        } else {
            long fatorial = 1;

            for (int i = 1; i <= numero; i++) {
                fatorial *= i; // multiplica o fatorial por i
            }

            System.out.println("O fatorial de " + numero + " é: " + fatorial);
        }
    }
}
