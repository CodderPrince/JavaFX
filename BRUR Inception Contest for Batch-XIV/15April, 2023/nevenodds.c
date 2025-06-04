#include<stdio.h>
int main()
{
    long long int a,s,mid;
    scanf("%lld %lld",&a, &s);//7,7
    mid= (a+1)/2;//4
    if(s <= mid)//7>4
    {//odd
        printf("%lld\n",(2*s)-1);
    }
    else//even
    {
        printf("%lld\n", 2*(s-mid));//6
    }
    return 0;
}
/*7 7
1 3 5 7 2 4 6
here 7 position number is 6*/