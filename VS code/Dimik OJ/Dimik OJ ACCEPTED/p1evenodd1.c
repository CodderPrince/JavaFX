#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)//test case nilam
    {
        int num;
        scanf("%d",&num);
        if(num%2==0)
        {
            printf("even\n");
        }
        else
        {
            printf("odd\n");
        }
    }
    return 0;
}