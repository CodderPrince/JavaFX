#include <stdio.h>
#include <string.h>
#include <stdlib.h>

#define MAX_N 20
#define MAX_LENGTH 10000

int compare(const void *a, const void *b) {
    return strcmp((const char *) a, (const char *) b);
}

int main() {
    int t;
    scanf("%d", &t);

    while (t--) {
        int n;
        scanf("%d", &n);

        char arr[MAX_N][MAX_LENGTH];
        for (int i = 0; i < n; i++) {
            scanf("%s", arr[i]);
        }

        qsort(arr, n, sizeof(arr[0]), compare);

        for (int i = 0; i < n; i++) {
            printf("%s\n", arr[i]);
        }
    }

    return 0;
}
