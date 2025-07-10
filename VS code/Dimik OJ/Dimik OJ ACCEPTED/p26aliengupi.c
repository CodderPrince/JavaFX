#include<stdio.h>
int main()
{
int t, count;
double x;
scanf("%d", &t);
while(t--)
{
    scanf("%lf", &x);
    count = 0;
    while(x>1.00)
    // loop continue untill his food less than 1kg
    {
    x = x/2;
    count++;
    }
    printf("%d days\n", count);
}
return 0;
}
/*x=40
 40/2=20 count=1
 20/2=10 count=2
 10/2=5 count=3
 5/2=2.5 count=4
 2.5/2=1.25 count=5
 1.25/2=.625 count=6
 .625<1.00
 so loop is break
 */
