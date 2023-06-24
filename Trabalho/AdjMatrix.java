/*import java.util.Random;

public class AdjMatrix{
    private Random R = new Random();
    private int[][] ADJ;
    private int size;

    public AdjMatrix(int size){
        this.size = size;
        ADJ = new int[size][2];
    }

    public int[][] constructMatix(){
        for(int i = 0; i < size; i++){
            for(int j = 0; j < 2; j++){
                ADJ[i][j] = R.nextInt(10000) + 1;
            }
        }

        return ADJ;
    }
}*/

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

//Cria a matriz de adjacência
public class AdjMatrix {
    public static void main(String[] args){
        //Cria o arquivo
        try {
            File myObj = new File("Graphs.txt");
            if(myObj.createNewFile()){
                System.out.println("File created: " + myObj.getName());
            }else{
                System.out.println("File already exists.");
            }
        }catch(IOException e){
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        //Cria a matriz
        int numVertices = 100;
        int[][] adjacencyMatrix = generateRandomWeightedAdjacencyMatrix(numVertices);

        //Escreve a matriz no arquivo
        try{
            PrintWriter fileout = new PrintWriter(new FileWriter("Graphs.txt"));
            fileout.printf("%d\n", numVertices);
            for(int i = 0; i < numVertices; i++){
                for(int j = 0; j < numVertices; j++){
                    fileout.printf("%d ", adjacencyMatrix[i][j]);
                }
                fileout.println();
            }

            fileout.close();
            System.out.println("success...");
        }catch(Exception e){
            System.out.println(e);
        }
    }
    
    //Gera a matriz de adjacência
    public static int[][] generateRandomWeightedAdjacencyMatrix(int numVertices){
        int[][] adjacencyMatrix = new int[numVertices][numVertices];
        Random R = new Random();

        for(int i = 0; i < numVertices; i++){
            for (int j = i + 1; j < numVertices; j++) {
                int weight = R.nextInt(100);
                adjacencyMatrix[i][j] = weight;
                adjacencyMatrix[j][i] = weight;
            }
        }
        
        return adjacencyMatrix;
    }
}
