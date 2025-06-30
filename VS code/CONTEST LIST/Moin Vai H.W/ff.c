#include<stdio.h>
int main()
{
    int a[1000],n,counter,temp,i;
    a[0]=1;//zero index=1
    counter=0;
    printf("Enter the number to Find Factorial: ");
    scanf("%d",&n);
    for(i=2; i<=n; i++)//zehetu 0 index er man 1 tai 2 theke start hoye 1 porjonto cholbe
    {
        temp=0;
        for(i=0; i<=counter; i++)
        {
            temp=(a[i]*n)+temp;
            a[i]=temp%10;
            temp=temp/10;
        }
        while(temp>0)
        {
            a[++counter]=temp%10;
            temp=temp/10;
        }
    }
    for(i=counter; i>=0; i--)
        printf("%d",a[i]);
    return 0;
}