#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

//declaração das funções
int mod(int x, int y);
int trav(int** A, int n, int l, int c);
int** readFile(int** A, char* fileName, int* m);
int min(int x, int y);
int travPd(int** A, int n, int l, int c, int** P);
int** defineAllMax(int** P, int n);

//Função principal
int main(void){
	int** A; //Vetor com a matriz de caminhos
	int** P; //Matriz para guardar os calculos
	int n; //Tamanho da matriz n x n

	A = readFile(A, "deniveis.txt", &n); //Faz a leitura do arquivo
	P = defineAllMax(P, n); //Inicializa a matriz de calculos com infinito

	printf("%d\n", travPd(A, n - 1, n - 1, n - 1, P)); //Imprime o resultado com PD
    printf("%d\n", trav(A, n - 1, n - 1, n - 1)); //Imprime o resultado sem PD

	return 0;
}//Fim main

//Funç]ao para calcular o melhor caminho sem PD
/*
	Entrada: Seja uma matriz de naturais A[0..n][0..n] onde n pertence aos naturais, l e c pertencentes aos naturais
	Saida: O menor caminho da posição A[0][0] até A[n][n]
*/
int trav(int** A, int n, int l, int c){
	//Condição de parada
	if(l == 0 && c == 0){
		return 0;
	}

	//Caso o algorimo chegue na primeira linha
	if(l == 0){
		return trav(A, n, 0, c - 1) + mod(A[0][c - 1], A[0][c]);
	}
	
	//Caso o algoritmo chage na primeira coluna
	if(c == 0){
		return trav(A, n, l - 1, 0) + mod(A[l - 1][0], A[l][0]);
	}

	//Recursão principal
	int x = trav(A, n, l - 1, c) + mod(A[l - 1][c], A[l][c]);
    int y = trav(A, n, l, c - 1) + mod(A[l][c - 1], A[l][c]);

	//Retorno
	return min(x, y);
}//Fim trav

//Função para calcular o melhor caminho com PD
/*
	Entrada: Seja uma matriz de naturais A[0..n][0..n] onde n pertence aos naturais
	E uma matriz P[0..n][0..n] preenchida com infinito, l e c pertencentes aos naturais
	Saida: O menor caminho da posição A[0][0] até A[n][n]
*/
int travPd(int** A, int n, int l, int c, int** P){
	//Verifica se o calculo ja foi feito
	if(P[l][c] != INT_MAX){
		return P[l][c];
	}

	//Condição de parada
	if(l == 0 && c == 0){
		return 0;
	}

	//Se o algoritmo chegar na primeira linha
	if(l == 0){
		return travPd(A, n, 0, c - 1, P) + mod(A[0][c - 1], A[0][c]);
	}
	
	//Se o algoritmo chagar na primeira coluna
	if(c == 0){
		return travPd(A, n, l - 1, 0, P) + mod(A[l - 1][0], A[l][0]);
	}

	//recursão principal
	int x = travPd(A, n, l - 1, c, P) + mod(A[l - 1][c], A[l][c]);
    int y = travPd(A, n, l, c - 1, P) + mod(A[l][c - 1], A[l][c]);
	
	//Guarda o recalculo
	P[l][c] = min(x, y);

	//Retorna
	return P[l][c];
}//Fim travPd

//Calcula o modulo da subtração de dois números
int mod(int x, int y){
	if(x >= y){
		return x - y;
	}else{
		return y - x;
	}
}//Fim mod

//Retorna o menor entre dois números
int min(int x, int y){
	if(x < y){
		return x;
	}else{
		return y;
	}
}//Fim min

//Função para fazer a leitura do arquivo
int** readFile(int** A, char* fileName, int* m){
    FILE* file = fopen(fileName, "r"); //Abre o arquivo
    int i, j, n; //i = contador, j = contador, n = tamanho da matriz

    fscanf(file, "%d", &n); //Pega o tamanho da matriz na primeira linha

	*m = n; //"Retorna" o tamanho da matriz

    A = (int**)malloc(n * sizeof(int*)); //aloca memória

	//Cria a matriz dinamicamente alocando mais memória
	for(i = 0; i < n; i++){
		A[i] = (int*)malloc(n * sizeof(int));
	}

	//Faz a leitura do arquivo
    for(i = 0; i < n; i++){
		for(j = 0; j < n; j++){
			fscanf(file, "%d", &A[i][j]);
		}
    }

    fclose(file); //fecha o arquivo

    return A; //Retorna a matriz
}//Fim readFile

//Função que define todos os indices da matriz com infinito
int** defineAllMax(int** P, int n){
	int i, j; //i = contador, j = contador

	P = (int**)malloc(n * sizeof(int*)); //Aloca memória

	//Cria a matriz dinamicamente
	for(i = 0; i < n; i++){
		P[i] = (int*)malloc(n * sizeof(int));
	}

	//Atribui a todos os indices da matriz o infinito
	for(i = 0; i < n; i++){
		for(j = 0; j < n; j++){
			P[i][j] = INT_MAX;
		}
	}
	return P; //Retorna a matriz
}//Fim defineAllMax