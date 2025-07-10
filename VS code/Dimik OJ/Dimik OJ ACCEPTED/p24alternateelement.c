#include <stdio.h>

int main() {
    int n, i, j;
    scanf("%d", &n);
    for(i=0; i<n; i++) {
        int m;
        scanf("%d", &m);
        int arr[m];
        for(j=0; j<m; j++) {
            scanf("%d", &arr[j]);
        }
        printf("%d", arr[0]);
        for(j=2; j<m; j+=2) {
            printf(" %d", arr[j]);
        }
        printf("\n");
    }
    return 0;
}
