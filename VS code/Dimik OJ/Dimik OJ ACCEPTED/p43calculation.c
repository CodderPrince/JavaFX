#include <stdio.h>

int mod_pow(int base, int exp, int mod) {
    int res = 1;
    while (exp > 0) {
        if (exp % 2 == 1) {
            res = (res * base) % mod;
        }
        base = (base * base) % mod;
        exp = exp / 2;
    }
    return res;
}

int main() {
    int t, p, q, c;
    scanf("%d", &t);
    while (t--) {
        scanf("%d %d %d", &p, &q, &c);
        int result = mod_pow(p, q, c);
        printf("Result = %d\n", result);
    }
    return 0;
}
