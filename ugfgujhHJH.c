#include <stdio.h>
#include <string.h>

int main(void) {
    char *str[41];
    char odd[21];
    char even[21];
    int i = 0;
    int j = 0;
    int k = 0;

    printf("Enter a string (40 characters maximum): ");
    scanf("%s", &str);

    while (&str[i] < 41) {
        if (i % 2 == 0) {
            odd[j++] = *str[i];
        } else {
            even[k++] = *str[i];
        }
        i++;
    }

    printf("The even string is:%s\n ", even);
    printf("The odd string is:%s\n ", odd);

    return 0;
}
