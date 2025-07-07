#include <stdio.h>
#include <string.h>

#define MAX_LEN 100005

int main() {
    
    char s[MAX_LEN];
    //scanf("%s", s);
    //getchar();
    gets(s);

    int len = strlen(s);
    int pos = len;

    while (pos > 0) {
        if (strncmp(&s[pos-5], "dream", 5) == 0) {
            pos -= 5;
            if (pos >= 2 && strncmp(&s[pos-2], "er", 2) == 0 && strncmp(&s[pos-5], "dream", 5) != 0) {
                pos -= 2;
            }
        } else if (strncmp(&s[pos-5], "erase", 5) == 0) {
            pos -= 5;
            if (pos >= 1 && strncmp(&s[pos-1], "r", 1) == 0 && strncmp(&s[pos-6], "eraser", 6) != 0) {
                pos -= 1;
            }
        } else {
            printf("NO\n");
            return 0;
        }
    }

    printf("YES\n");

    return 0;
}
