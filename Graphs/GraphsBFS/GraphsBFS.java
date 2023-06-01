import java.util.LinkedList;
import java.util.Queue;

//Classe Busca Em Largura(BFS)
public class GraphsBFS{
    //Busca em largura
    /*
     * adj = Matriz de adjacência
     * n = Tamanho da matriz n x n
     * r = Começo da busca
     * 'B' = Branco
     * 'C' = Cinza
     * 'P' = Preto
     */
    public char[] BFS(int[][] adj, int n, int r){
        char[] color = new char[n]; //Vetor de cores

        //Nenum vertice foi visitado então todos são brancos
        for(int i = 0; i < n; i++){
            color[i] = 'B';
        }

        color[r] = 'C'; //O começo do grafo foi visitado
        Queue<Integer> F = new LinkedList<Integer>(); //Fila para auxiliar nas visitas
        F.add(r); //coloca o começo do grafo na fila

        //Enquanto a fila não estiver vazia o loop continua
        while(!F.isEmpty()){
            int u = F.poll(); //Remove um elemnto da fila

            //Loop para fazer as visitas
            for(int v = 0; v < n; v++){
                /*
                 * Se a posição v no vetor de cores não foi visitada e na matriz de adjacência 
                 * os vertices u, v tiver uma ligação o algoritmo visita essa posição.
                 */
                if((color[v] == 'B') && (adj[u][v] == 1)){
                    color[v] = 'C';
                    F.add(v);
                }
            }
            color[u] = 'P'; //Todos os vizinhos de u foram visitados
        }
        return color; //retorna os vertices visitados
    }//Fim BFS
}//Fim GraphsBFS