import java.util.Scanner;
import java.io.*;

//Classe principal
public class Main{
    //Metodo principal
    public static void main(String[] args) throws IOException{
        GraphsBFS g = new GraphsBFS(); //Declaração do objeto da classe GraphBFS
        File file = new File("Graphs.txt"); //Declara um objeto do tipo File para ler o arquivo Graphs.txt
        Scanner scn = new Scanner(file); //Scanner para ler os valores do arquivo
        int n = scn.nextInt(); //Pega o tamanho da matriz
        int[][] adj = new int[n][n]; //Matriz de adjacência
    
        readFile(scn, n, adj); //Faz a leitura do arquivo
        char[] color = g.BFS(adj, n, 0); //Executa o BFS e retorna as cores dos vertices visitados

        //Imprime as cores do vetor
        for(int i = 0; i < n; i++){
            System.out.printf("%c\n", color[i]);
        }
    }//Fim main

    //Metodo para ler o arquivo
    public static void readFile(Scanner scn, int n, int[][] adj){
        for(int i = 0; i < n; i++){
            for(int j = 0; j < n; j++){
                adj[i][j] = scn.nextInt();
            }
        }
    }//Fim readFile
}