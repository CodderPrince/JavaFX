#include <stdio.h>

int main()
{
    int t;
    scanf("%d", &t);
    while(t--)
    {
        int ara[5] = {6, 28, 496, 8128, 33550336};
        int i, n;
        scanf("%d", &n);
        for(i = 0; i < 5; i++)
        {
            if(ara[i] <= n)
            {
                printf("%d\n", ara[i]);
            }
            else
            {
                break;
            }
        }
        printf("\n");
    }
    return 0;
}
