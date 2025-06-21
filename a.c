#include <stdio.h>

int main() {
    int T;
    scanf("%d", &T);

    while (T--) {
        int X, K;
        scanf("%d %d", &X, &K);

        int result = 0;
        long long int p = 1;
        for (int i = 0; i <= K; i++) {
            result += p;
            p *= X;
        }
 
        printf("Result = %d", result);       
        printf("\n");
    }

    return 0;
}
