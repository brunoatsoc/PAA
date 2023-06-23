//Essa parte importa as classes que vamos precisar no algoritmo
import java.io.IOException;

/*
 * Alunos: Vitor Coutinho, Bruno Santos Costa
 */
//Classe Principal
public class Main{
    //Metodo main
    public static void main(String[] args) throws IOException{
        PrimMST g = new PrimMST("Graphs.txt");

        g.constructMST(); //Cria a Árvore geradora minima
        g.printMTS(); //Imprime a árvore
    }//Fim main
}//Fim main