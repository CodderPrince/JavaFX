#include <stdio.h>

int main() {
    int t;
    scanf("%d", &t);

    while (t--) {
        int n;
        scanf("%d", &n);

        int reverse_n = 0;
        while (n > 0) {
            int remainder = n % 10;
            reverse_n = reverse_n * 10 + remainder;
            n /= 10;
        }

        printf("%d\n", reverse_n);
    }

    return 0;
}
