#include <stdio.h>
#include <stdbool.h>

#define MAX_N 100001

int main() {
    int t;
    scanf("%d", &t);

    while (t--) {
        int n;
        scanf("%d", &n);

        int a[MAX_N];
        bool present[MAX_N] = { false }; // initialize all elements to false

        for (int i = 0; i < n - 1; i++) {
            scanf("%d", &a[i]);
            present[a[i] - 1] = true; // mark the presence of a[i]
        }

        for (int i = 1; i <= n; i++) {
            if (!present[i - 1]) {
                printf("%d\n", i);
                break;
            }
        }
    }

    return 0;
}