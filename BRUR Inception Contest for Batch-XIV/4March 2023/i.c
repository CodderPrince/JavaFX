#include<stdio.h>
int main()
{
    int t,a;
    scanf("%d",&t);
    while(t--)
    {
        scanf("%d",&a);
        if(360%(180-a)==0)
        {
            printf("YES\n");
        }
        else{
            printf("NO\n");
        }
    }
}