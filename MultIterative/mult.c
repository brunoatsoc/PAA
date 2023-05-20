#include <stdio.h>
#include <stdlib.h>
#include <limits.h>

int multMatriz(int* d, int n);
int readIdexs(FILE* file, int* d, int n);

int main(void){
	FILE* file = fopen("Dimensoes.txt", "r");
	int n, i;
	int* d;

	fscanf(file, "%d", &n);

	d = malloc((n + 1) * sizeof(int));

	readIdexs(file, d, n);

	printf("%d\n", multMatriz(d, n));

	fclose(file);

	return 0;
}

int multMatriz(int* d, int n){
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
				}
			}
		}
	}
	return m[1][n];
}

int readIdexs(FILE* file, int* d, int n){
	int i;

    int* y = malloc(n * sizeof(int));

    for(i = 0; i < n; i++){
        fscanf(file, "%d %d", &d[i], &y[i]);
    }

    d[i] = y[i - 1];

	return 1;
}