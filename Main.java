///usr/bin/env jbang "$0" "$@" ; exit $?

public class Main 
{
    public static void main(String[] args) 
    {
        System.out.println("Eleitores!");

        int eleitores;  
        int votosValidos;
        int votosBrancos;
        int votosNulos;

        eleitores = 1000;
        votosValidos = 800;
        votosBrancos = 150;
        votosNulos = 50;

        System.out.println("Número de eleitores: " + eleitores);
        System.out.println("Número de votos válidos: " + votosValidos);
        System.out.println("Número de votos em branco: " + votosBrancos);
        System.out.println("Número de votos nulos: " + votosNulos);  
    }
}
