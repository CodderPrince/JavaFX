#include<stdio.h>
long long factorial(int num)
{
    if(num==0)
    {
        return 1;
    }
    else{
        return num*factorial(num-1);
    }
}
int main()
{
    int num;
    scanf("%d",&num);
    printf("%d\n",factorial(num));
}