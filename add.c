#include <stdio.h>

int main() {
    int T;
    scanf("%d", &T);

    while (T--) 
    {
        long long int X, K;
        scanf("%lld %lld", &X, &K);//5 2

        long long int result = 0;
        int pow = 1;
        for (int i = 0; i <= K; i++) {
            result = result + pow;
            pow = pow * X;
        }

        printf("Result = %lld", result);
        if(T>0)
        printf("\n");
    }

    return 0;
}
