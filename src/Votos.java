package src;
public class Votos 
{
    public static void main(String[] args) 
    {
        System.out.println("Eleição!");

        int eleitores = 1000;  
        int votosValidos = 800;
        int votosBrancos = 150;
        int votosNulos = 50;


        System.out.println("\nQuantidade de eleitores:");
        System.out.println("Número de eleitores: " + eleitores);

        System.out.println("\nQuantidade de votos:");
        System.out.println("Número de votos válidos: " + votosValidos);
        System.out.println("Número de votos em branco: " + votosBrancos);
        System.out.println("Número de votos nulos: " + votosNulos);  

        PercentualVotosEleicao calculadora = new PercentualVotosEleicao(); 

        double percentualVotosValidos = calculadora.calcularPercentualValidos(votosValidos, eleitores);
        double percentualVotosBrancos = calculadora.calcularPercentualBrancos(votosBrancos, eleitores);
        double percentualVotosNulos = calculadora.calcularPercentualNulos(votosNulos, eleitores);

        System.out.println("\nEm porcentagem:");
        System.out.println("Percentual de votos válidos: " + percentualVotosValidos + "%");
        System.out.println("Percentual de votos em branco: " + percentualVotosBrancos + "%");
        System.out.println("Percentual de votos nulos: " + percentualVotosNulos + "%");
    }

    static class PercentualVotosEleicao 
    {
        public double calcularPercentualValidos(int votosValidos, int totalEleitores) 
        {
            return (double) votosValidos / totalEleitores * 100;
        }

        public double calcularPercentualBrancos(int votosBrancos, int totalEleitores)
        {
            return (double) votosBrancos / totalEleitores * 100;
        }

        public double calcularPercentualNulos(int votosNulos, int totalEleitores) 
        {
            return (double) votosNulos / totalEleitores * 100;
        }
    }
}