#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

//Declaração das funções
int multMatriz(int* d, int n, int** p);
int readIdexs(FILE* file, int* d, int n);
void printParenthesis(int** p, int i, int j, int* d);

//Função principal
int main(void){
	FILE* file = fopen("Dimensoes.txt", "r");
	int n, i;
	int* d;

	fscanf(file, "%d", &n);

	int** p = (int**)malloc((n + 1) * sizeof(int*));

	for(i = 0; i < n + 1; i++){
		p[i] = (int*)malloc((n + 1) * sizeof(int));
	}

	d = malloc((n + 1) * sizeof(int));

	readIdexs(file, d, n);

	printf("%d\n", multMatriz(d, n, p));

	printParenthesis(p, 1, n, d);

	printf("\n");

	fclose(file);

	return 0;
}//Fim main

//Função para calcular o minimo de multiplicações
int multMatriz(int* d, int n, int** p){
	int i, j, k, I, q;
	int m[n + 1][n + 1];

	for(i = 1; i <= n; i++){
		m[i][i] = 0;
	}

	for(I = 1; I < n; I++){
		for(i = 1; i <= n - I; i++){
			j = i + I;
			m[i][j] = INT_MAX;

			for(k = i; k <= j - 1; k++){
				q = m[i][k] + m[k + 1][j] + d[i - 1] * d[k] * d[j];

				if(q < m[i][j]){
					m[i][j] = q;
					p[i][j] = k;
				}
			}
		}
	}

	return m[1][n];
}//Fim multMatriz

//Função para ler os valores do arquivo e colocar no vetor d
int readIdexs(FILE* file, int* d, int n){
	int i;

    int* y = malloc(n * sizeof(int));

    for(i = 0; i < n; i++){
        fscanf(file, "%d %d", &d[i], &y[i]);
    }

    d[i] = y[i - 1];

	return 1;
}//Fim readIndexs

//Função para imprimir os parentesis para a ordem damultiplicação das matrizes
void printParenthesis(int** p, int i, int j, int* d){
	if(i == j){
		printf("%d", i);
	}else{
		printf("(");
		printParenthesis(p, i, p[i][j], d);
		printParenthesis(p, p[i][j] + 1, j, d);
		printf(")");
	}
}//Fim printParenthesis