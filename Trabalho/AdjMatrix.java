import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

//Cria a matriz de adjacência
public class AdjMatrix {
    private String fileName;
    private int matrixSize;

    public AdjMatrix(String fileName, int matrixSize){
        this.fileName = fileName;
        this.matrixSize = matrixSize;
        createMatrixFile();
    }

    private void createMatrixFile(){
        //Cria o arquivo
        try {
            File myObj = new File(fileName);
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
        int[][] adjacencyMatrix = generateRandomWeightedAdjacencyMatrix(matrixSize);

        //Escreve a matriz no arquivo
        try{
            PrintWriter fileout = new PrintWriter(new FileWriter(fileName));
            fileout.printf("%d\n", matrixSize);
            for(int i = 0; i < matrixSize; i++){
                for(int j = 0; j < matrixSize; j++){
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
    private int[][] generateRandomWeightedAdjacencyMatrix(int numVertices){
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
