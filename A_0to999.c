#include<stdio.h>
int main()
{
    int n;
    scanf("%d",&n);
    if(n>=0 && n<=9999)
    {
        if(n/1000>0)
        {
            printf("%d\n",n);//VAG DIYE DEKHBO 4 SONKHA KINA 4 SONKHA HOLE PRINT KORO
        }
        else if(n/1000<=0 && n/100>0)//3 SONKHA THEKE SOTO AND 2 SONKHA THEKE BORO HOLE 1TA 0 PRINT KORTE HOBE
        {
            printf("0%d\n",n);
        }
        else if(n/100<=0 && n/10>0)//2 SONKHA THEKE BORO 1 SONKHA THEKE SOTO HOLE 00 PRINT HOBE
        {
        printf("00%d\n",n);
        }
        else if(n/10<=0 && n/1>0)//1 SONKHA THEKE BORO 0 THEKE BORO HOLE 000 PRINT HOBE
        {
            printf("000%d\n",n);
        }
        else
        {
        printf("0000\n");// SHUDU 0 HOLE 0000 PRINT HOBE
        }
    }
    return 0;
}