#include <stdio.h>

int main() {
    int T; // Number of test cases
    scanf("%d", &T);

    while (T--) {
        int N; // Size of the matrix
        scanf("%d", &N);

        int moves = 0; // Variable to store the minimum number of moves

        // For each bishop placed at (i, i) where i > 1, we need to move it to (1, i)
        // This requires N-1 moves for the first diagonal
        if (N > 1) {
            moves += (N - 1);
        }

        // For each bishop placed at (i, i) where i > 2, we need to move it to (2, i)
        // This requires N-2 moves for the second diagonal
        if (N > 2) {
            moves += (N - 2);
        }

        printf("%d\n", moves);
    }

    return 0;
}
