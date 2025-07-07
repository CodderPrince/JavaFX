#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int judgeInclude(char *s, char **sSeq, int n) {
    char *sTemp = strdup(s);
    int i;

    while (1) {
        for (i = 0; i < n; i++) {
            int len = strlen(sSeq[i]);
            if (strncmp(sTemp + strlen(sTemp) - len, sSeq[i], len) == 0) {
                if (strcmp(sTemp, sSeq[i]) == 0) {
                    free(sTemp);
                    return 1;
                } else {
                    sTemp[strlen(sTemp) - len] = '\0';
                    break;
                }
            }
        }
        if (i == n) {
            free(sTemp);
            return 0;
        }
    }
}

int main() {
    char s[100001];
    char *sSeq[] = {"dreamer", "eraser", "dream", "erase"};
    int n = 4;

    scanf("%s", s);

    if (judgeInclude(s, sSeq, n)) {
        printf("YES\n");
    } else {
        printf("NO\n");
    }

    return 0;
}
