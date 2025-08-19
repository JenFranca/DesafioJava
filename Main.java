///usr/bin/env jbang "$0" "$@" ; exit $?

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Eleição!");

        int eleitores = 1000;  
        int votosValidos = 800;
        int votosBrancos = 150;
        int votosNulos = 50;

         
        System.out.println("");
        System.out.println("Quantidade de eleitores:");
        System.out.println("Número de eleitores: " + eleitores);

        System.out.println("");
        System.out.println("Quantidade de votos:");
        System.out.println("Número de votos válidos: " + votosValidos);
        System.out.println("Número de votos em branco: " + votosBrancos);
        System.out.println("Número de votos nulos: " + votosNulos);  

        double percentualVotosValidos = (double) votosValidos / eleitores * 100;
        double percentualVotosBrancos = (double) votosBrancos / eleitores * 100;
        double percentualVotosNulos = (double) votosNulos / eleitores * 100;

        System.out.println("");
        System.out.println("Em porcentagem:");
        System.out.println("Percentual de votos válidos: " + percentualVotosValidos + "%");
        System.out.println("Percentual de votos em branco: " + percentualVotosBrancos + "%");
        System.out.println("Percentual de votos nulos: " + percentualVotosNulos + "%");
    }
}
