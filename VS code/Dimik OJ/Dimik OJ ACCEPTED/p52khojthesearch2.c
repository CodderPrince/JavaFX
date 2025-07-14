#include <stdio.h>
#include <string.h>

int main() {
    int t;
    scanf("%d", &t);
    while (t--) {
        char s1[129], s2[129];
        scanf("%s %s", s1, s2);
        int len1 = strlen(s1);
        int len2 = strlen(s2);
        int count = 0;
        for (int i = 0; i <= len1 - len2; i++) {
            int j;
            for (j = 0; j < len2; j++) {
                if (s1[i + j] != s2[j]) {
                    break;
                }
            }
            if (j == len2) {
                count++;
            }
        }
        printf("%d\n", count);
    }
    return 0;
}
