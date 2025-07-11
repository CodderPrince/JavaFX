#include <stdio.h>

int main() {
    int t;
    scanf("%d", &t);

    while (t--) {
        unsigned long long a, b, c;
        scanf("%llu %llu %llu", &a, &b, &c);

        unsigned long long start = (a + c - 1) / c * c; // smallest multiple of c >= a
        for (unsigned long long i = start; i <= b; i += c) {
            printf("%llu\n", i);
        }
        printf("\n");
    }

    return 0;
}
