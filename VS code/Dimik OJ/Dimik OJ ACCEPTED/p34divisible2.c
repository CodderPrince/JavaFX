#include<stdio.h>

int gcd();

int main()
{ long long int t,i,j,n1,temp,lcm,g,n2,high;
 scanf("%d",&t);

 for(i=0;i<t;i++)
 {
     scanf("%lld %lld %lld",&n1,&n2,&high);
     g=gcd(n1,n2);
     lcm=(n1*n2)/g;

     for(j=lcm;j<=high;j+=lcm)
     {
         printf("%lld\n",j);
     }
     printf("\n");
 }



    return 0;
}
int gcd(int a,int b)
{
    if(b==0) return a;
    else return gcd(b,a%b);
}