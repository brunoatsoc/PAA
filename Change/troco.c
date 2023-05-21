#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

//Declaração das funções
int coinChange(int* V, int M, int T);
int* readFile(int* v, char* fileName);

//Função principal
int main(void){
    int* v;

    v = readFile(v, "Moedas.txt");

    printf("%d\n", coinChange(v, sizeof(v) / sizeof(v[0]), 20));

    return 0;
}//Fim main

/*
    Função que calcula quantaas moedas vão ser retornadas
    V = vetor com as moedas
    M = A quantidade de moedas(vem da leitura do arquivo)
    T = O troco que será usado para calcular a quantidade de moedas
*/
int coinChange(int* V, int M, int T){
    /*
        Verifica se o resultado T - V[i] == 0
        Se for igual significa que temos uma moeda do
        mesmo valor desse T e recursão para
    */
    if(T == 0){
        return 0;
    }

    /*
        i = contador
        mim = representa o minimo de moedas para o troco, o INT_MAX serve para comparar pois sempre vai ser um número maior que qualquer coisa.
    */
    int i, min = INT_MAX;

    /*
        Vai percorrer o vetor e testar as posições que tiverem um valor menor ou igual que o T
        Se for menor ou igual significa que existe uma moeda que vai servir para o troco
    */
    for(i = 0; i < M; i++){
        //Faz o teste
        if (V[i] <= T) {
            int p = coinChange(V, M, T - V[i]); //Guarda o retorno das chamadas recursivas que vão ter o atual valor minimo

            //Faz outro teste para saber se o valor de p é valido como um valor minimo
            if(p != INT_MAX && p + 1 < min){
                min = p + 1; //Soma +1 na variavel min para retornar para as chamadas recursivas
            }
        }
    }

    return min; //Retorna o valor minimo de moedas para o troco
}//Fim coinChange

//Algoritmo para ler o arquivo com as moedas
int* readFile(int* v, char* fileName){
    FILE* file = fopen(fileName, "r");
    int i, M;

    fscanf(file, "%d", &M);

    v = (int*)malloc(M * sizeof(int));

    for(i = 0; i < M; i++){
        fscanf(file, "%d", &v[i]);
    }

    fclose(file);

    return v;
}//Fim readFile