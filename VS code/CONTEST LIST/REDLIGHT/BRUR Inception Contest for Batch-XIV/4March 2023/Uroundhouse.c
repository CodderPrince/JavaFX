#include <stdio.h>
int main() 
{
    long long a, b, n, sum, ans;
    while ( scanf("%lld %lld %lld", &n, &a, &b) == 3) 
    {
        b = b % n;
        if (b < 0)
        {
            sum = n + b + a;
        }         
        else
        {
            sum = a + b;
        }
        if (sum % n == 0)
        {
             printf("%lld\n", n);
        }           
        else
        {
            printf("%lld\n", sum % n);
        }     
    }
    return 0;
}
//here i try lot of test case then solve the problem through test case
