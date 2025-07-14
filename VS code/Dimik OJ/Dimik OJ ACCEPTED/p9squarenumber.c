#include<stdio.h>
#include<math.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
        int num,x;
        scanf("%d",&num);//16
        x= sqrt(num);//x=4
        if(x*x==num)//4*4=16
        {
            printf("YES\n");
        }
        else
        {
            printf("NO\n");
        }
    }
    return 0;
}