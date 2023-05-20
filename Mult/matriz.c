#include <stdio.h>
#include <limits.h>

#define N 3//<- O valor de N deve ser alterado aqui

int m[N][N];

int multMatriz(int* d, int n);
void lerMatriz(FILE *arquivo, int out[N]);

int main(void){
	FILE *entrada = fopen("mat.txt", "r");

    int matriz[N + 1];
    lerMatriz(entrada, matriz);

    fclose(entrada);

    printf("%d\n", multMatriz(matriz, N));

	return 0;
}

int multMatriz(int* d, int n){
	int i, j, I, k, q;

	for(i = 1; i < n; i++){
		m[i][i] = 0;
	}

	for(I = 1; I < n; I++){
		for(i = 1; i < n; i++){
			j = i + I;
			m[i][j] = INT_MAX;

			for(k = i; k < j; k++){
				q = m[i][k] + m[k + 1][j] + d[i - 1] * d[k] * d[j];

				if(q < m[i][j]){
					m[i][j] = q;
				}
			}
		}
	}
	return m[1][n];
}

void lerMatriz(FILE *arquivo, int* out){
    int c, k = 0;

    for(int i = 0; i < N; i++){
        for(int j = 0; j < 2; j++){
            c = fgetc(arquivo);
            
			if(c == EOF){
				return;
			}
            
			if(j == 0 || (i == N - 1)){
				out[k] = c - '0';
				++k;
			}
        }
		
        c = fgetc(arquivo);
        
		if(c != '\n'){
			return;
		}
    }
}