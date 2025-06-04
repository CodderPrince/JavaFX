#include <stdio.h>

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        int n;
        scanf("%d", &n);
        if (n % 2 == 1) {
            // Bishops are already in final position
            printf("0\n");
            continue;
        }
        int moves = 0;
        for (int i = 1; i <= n / 2; i++) {
            // Calculate moves required for each bishop
            int row = i * 2 - 1;
            int col = i * 2;
            if ((row != i || col != i * 2) && (row <= n && col <= n)) {
                moves += 2;
            }
        }
        printf("%d\n", moves);
    }
    return 0;
}
