#include<stdio.h>
int main()
{
    int i,j,n,array[30];
    scanf("%d",&n);
    if(n>0)
    {
         for(i=0; n>0; i++)
         {
            array[i]= n%2;
            n= n/2;
         }
         for(j= i-1; j>=0; j--)
         {
            printf("%d",array[j]);
         }
    }
   
}