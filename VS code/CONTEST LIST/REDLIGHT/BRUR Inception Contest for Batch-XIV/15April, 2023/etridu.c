#include <stdio.h>
int main()
{
    int a,b;
    while(scanf("%d %d", &a, &b) ==2)
    //eof=end of file .. this is infinite loof with unlimited test case
    {
        if(a==b)
        {
            printf("%d\n", a);
        }
        else if(a>b)
        {
            printf("%d\n", a);
        }
        else if(a<b)
        {
            printf("%d\n", b);
        }
    }
    return 0;
}