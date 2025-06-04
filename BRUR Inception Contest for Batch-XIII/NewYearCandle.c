#include<stdio.h>
int main()
{
    int n,m,i,b,a,count=0;
    scanf("%d %d", &n,&m);
    /*n=ager mombati jotokhon cholbe
    m= ager jalano mombatir shesh yongso
    diye make kora new momnbati jotokhon cholbe*/
    i=n;
    while(i>=m)
    /*ager mombati new mombati theke obossoi
    besi somoi cholbe*/
    {
        a= i/m;
        b= i%m;
        count= count +a;
        i= a+b;
    }
    printf("%d\n",n+count);
    return 0;
}