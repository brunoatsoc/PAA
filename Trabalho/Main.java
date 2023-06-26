//Essa parte importa as classes que vamos precisar no algoritmo
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

/*
 * Alunos: Vitor Coutinho, Bruno Santos Costa
 */
//Classe Principal
public class Main{
    //Metodo main
    public static void main(String[] args) throws IOException{
        //Cria as matrizes de adjacência
        AdjMatrix m1 = new AdjMatrix("Graphs/Graphs1.txt", 5);
        AdjMatrix m2 = new AdjMatrix("Graphs/Graphs2.txt", 10);
        AdjMatrix m3 = new AdjMatrix("Graphs/Graphs3.txt", 20);
        AdjMatrix m4 = new AdjMatrix("Graphs/Graphs4.txt", 30);
        AdjMatrix m5 = new AdjMatrix("Graphs/Graphs5.txt", 40);
        AdjMatrix m6 = new AdjMatrix("Graphs/Graphs6.txt", 50);
        AdjMatrix m7 = new AdjMatrix("Graphs/Graphs7.txt", 100);
        //Objetos para o algoritmo de Prim
        PrimMST g1 = new PrimMST("Graphs/Graphs1.txt", "Resposta/Resposta1.txt");
        PrimMST g2 = new PrimMST("Graphs/Graphs2.txt", "Resposta/Resposta2.txt");
        PrimMST g3 = new PrimMST("Graphs/Graphs3.txt", "Resposta/Resposta3.txt");
        PrimMST g4 = new PrimMST("Graphs/Graphs4.txt", "Resposta/Resposta4.txt");
        PrimMST g5 = new PrimMST("Graphs/Graphs5.txt", "Resposta/Resposta5.txt");
        PrimMST g6 = new PrimMST("Graphs/Graphs6.txt", "Resposta/Resposta6.txt");
        PrimMST g7 = new PrimMST("Graphs/Graphs7.txt", "Resposta/Resposta7.txt");
        //Vetores para calcular o tempo de execução
        long[] timeExecutionStart = new long[7];
        long[] timeExecutionFinal = new long[7];
        
        //Primeiro
        timeExecutionStart[0] = System.currentTimeMillis();

        g1.constructMST(); //Cria a Árvore geradora minima
        g1.printMTS(); //Imprime a árvore

        timeExecutionFinal[0] = System.currentTimeMillis();

        //Segundo
        timeExecutionStart[1] = System.currentTimeMillis();

        g2.constructMST(); //Cria a Árvore geradora minima
        g2.printMTS(); //Imprime a árvore

        timeExecutionFinal[1] = System.currentTimeMillis();

        //Terceiro
        timeExecutionStart[2] = System.currentTimeMillis();

        g3.constructMST(); //Cria a Árvore geradora minima
        g3.printMTS(); //Imprime a árvore

        timeExecutionFinal[2] = System.currentTimeMillis();

        //Quarto
        timeExecutionStart[3] = System.currentTimeMillis();

        g4.constructMST(); //Cria a Árvore geradora minima
        g4.printMTS(); //Imprime a árvore

        timeExecutionFinal[3] = System.currentTimeMillis();

        //Quinto
        timeExecutionStart[4] = System.currentTimeMillis();

        g5.constructMST(); //Cria a Árvore geradora minima
        g5.printMTS(); //Imprime a árvore

        timeExecutionFinal[4] = System.currentTimeMillis();

        //Sexto
        timeExecutionStart[5] = System.currentTimeMillis();

        g6.constructMST(); //Cria a Árvore geradora minima
        g6.printMTS(); //Imprime a árvore

        timeExecutionFinal[5] = System.currentTimeMillis();

        //Setimo
        timeExecutionStart[6] = System.currentTimeMillis();

        g7.constructMST(); //Cria a Árvore geradora minima
        g7.printMTS(); //Imprime a árvore

        timeExecutionFinal[6] = System.currentTimeMillis();

        //Imprime os tempos de execução
        try{
            PrintWriter fileout = new PrintWriter(new FileWriter("Resposta/TimeExecution.txt"));

            fileout.printf("Time Execution Graph1: %d\n", timeExecutionFinal[0] - timeExecutionStart[0]);
            fileout.printf("Time Execution Graph2: %d\n", timeExecutionFinal[1] - timeExecutionStart[1]);
            fileout.printf("Time Execution Graph3: %d\n", timeExecutionFinal[2] - timeExecutionStart[2]);
            fileout.printf("Time Execution Graph4: %d\n", timeExecutionFinal[3] - timeExecutionStart[3]);
            fileout.printf("Time Execution Graph5: %d\n", timeExecutionFinal[4] - timeExecutionStart[4]);
            fileout.printf("Time Execution Graph6: %d\n", timeExecutionFinal[5] - timeExecutionStart[5]);
            fileout.printf("Time Execution Graph7: %d\n", timeExecutionFinal[6] - timeExecutionStart[6]);

            fileout.close();
            System.out.println("success...");
        }catch(Exception e){
            System.out.println(e);
        }
    }//Fim main
}//Fim main