#include <stdio.h>
#include <string.h>

int main()
{
    char str[100];
    gets(str);
    int i;
    for (i = 0; i < strlen(str); i++)
    {
        if (str[i] >= 'a' && str[i] <= 'z')
            str[i] = str[i] - 32;
        if (str[i] == ' ')
        {
            str[i] = '\n';
        }
    }
    printf("%s\n", str);
}
// Dekh to hoilo kina?