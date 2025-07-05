#include<stdio.h>
int odd(int n)
{
    if(n<10);
    {
    return n;
    }
    else if(n/10<10)
    {
        return 9;
    }
    else if(n/100 <10)
    {
        return 9+n-99;
    }
    else if(n/1000 <10)
    {
        return 909+n-9999;
    }
    else{
        return 90909;
    }
}
int main()
{
    int n=893;
    printf("odd(n)");
    return 0;
}