#include <stdio.h>

int main() {
    int T, n, i, j, arr[21], ascending = 1, descending = 1;
    scanf("%d", &T);
    while(T--) {
        scanf("%d", &n);
        for(i = 0; i < n; i++)
            scanf("%d", &arr[i]);
        ascending = 1;
        descending = 1;
        for(i = 1; i < n; i++) {
            if(arr[i] < arr[i-1]) {
                ascending = 0;
            }
            if(arr[i] > arr[i-1]) {
                descending = 0;
            }
        }
        if(ascending) {
            printf("YES\n");
        } else if(descending) {
            printf("YES\n");
        } else {
            printf("NO\n");
        }
    }
    return 0;
}
