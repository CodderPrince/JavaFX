#include <stdio.h>
int main()
{
    int x;   
    scanf("%d", &x);
    int count = 0;
    while (x > 0)
    {
        count++; 
        x = x - 5;
    }
    printf("%d\n", count);
    return 0;
}
