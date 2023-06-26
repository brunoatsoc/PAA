//Essa parte importa as classe que vamos usar no algoritmo
import java.io.File;
import java.io.FileWriter;
import java.util.Scanner;
import java.io.IOException;
import java.io.PrintWriter;

//Classe para o algoritmo de Prim
public class PrimMST{
    private int sizeGraph; //guarda o tamanho da matriz de adjacência
    private int[] parent; //Vai armazenas a arvore geradora que vamos construir
    private int[][] graph; //Grafo
    private String answerFile;

    //Construtor
    public PrimMST(String nameFile, String answerFile) throws IOException{
        File file = new File(nameFile);
        Scanner scn = new Scanner(file);

        this.sizeGraph = scn.nextInt(); //Pega a primeira linha com o tamanho da matriz
        this.parent = new int[sizeGraph];
        this.graph = new int[sizeGraph][sizeGraph];
        this.answerFile = answerFile;

        //Faz a leitura da matriz de adjacência
        for(int i = 0; i < sizeGraph; i++){
            for(int j = 0; j < sizeGraph; j++){
                this.graph[i][j] = scn.nextInt();
            }
        }
    }//Fim construtor

    //Metodo para procurar o vértice com a menor chave
    //Esse metodo vai varrer o grafo e verificar quais
    //os menores valores tem em cada vertice adicionado
    //na arvore geradora minima, se ele ainda não pertence
    //a arvore e tem o menor valor entre os vertices adjacêntes
    //se isso se cumprir ele é considerado o menor valido e pode entrar na arvore
    //no final ele retorna o indice da matirz de adjacência quem tem o menor peso.
    private int findMinKey(int[] k, Boolean[] mstSet){
        int min = Integer.MAX_VALUE;
        int minIndex = -1;

        for(int v = 0; v < sizeGraph; v++){
            if((mstSet[v] == false) && (k[v] < min)){
                min = k[v];
                minIndex = v;
            }
        }

        return minIndex;
    }//Fim findMinKey

    public void constructMST(){
        int[] k = new int[sizeGraph]; //Vai armazenar as arestas que tem o peso menor
        Boolean[] mstSet = new Boolean[sizeGraph]; //Vai representar os verticis incluidos na arvore geradora minima

        //Atribui infinito as chaves e falso aos vertices incluidos na arvore
        for(int i = 0; i < sizeGraph; i++){
            k[i] = Integer.MAX_VALUE;
            mstSet[i] = false;
        }

        k[0] = -1; //O primeiro vertice vai ser escolhido para ser incluido primeiro na arvore geradora minima(outro vertice poderia ser escolhido e a arvore final vai ficar diferente)

        parent[0] = -1; //significa que adicionamos o primeiro vertice a arvore geradora minima

        //Esse laço percorre e grafo encontrando os vertices de menor peso e os coloca no Array parent
        for(int i = 0; i < sizeGraph - 1; i++){
            int u = findMinKey(k, mstSet); //Retorna o indice com menor peso

            mstSet[u] = true; //Indica que esse vertice foi visitado

            for(int v = 0; v < sizeGraph; v++){
                //Se o vertice é adjacênte a alguem
                //se o vertice ainda não foi visitado
                //E se o vertice do grafo tiver um peso menor que a chave de menor peso
                //O algoritmo vai 
                if((graph[u][v] != 0) && (mstSet[v] == false) && (graph[u][v] < k[v])){
                    parent[v] = u; //O vertice sessa posição foi adicionado na arvore geradora minima
                    k[v] = graph[u][v]; //Guardamos na chave o vertice de menor peso
                }
            }
        }
    }//Fim constructMST

    //Esse metodo vai imprimir a arvore geradora minima
    public void printMTS(){
        //Escreve a matriz no arquivo
        try{
            PrintWriter fileout = new PrintWriter(new FileWriter(answerFile));
            
            fileout.printf("ADJACÊNCIA\tPESO\n");

            for(int i = 1; i < sizeGraph; i++){
                fileout.printf("%d -> %d \t\t %d\n", parent[i], i, graph[i][parent[i]]);
            }

            fileout.close();
            System.out.println("success...");
        }catch(Exception e){
            System.out.println(e);
        }
    }
}//Fim classe PrimMST