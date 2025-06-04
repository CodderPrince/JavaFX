#include<stdio.h>
int main()
{
   int a,i;
   for(i=1; ; i++)
   {
    scanf("%d",&a);
    if(a==0)
    {
        break;
    }
    else
    {
        printf("Case %d: %d\n",i,a);
    }
   }
    return 0;
}