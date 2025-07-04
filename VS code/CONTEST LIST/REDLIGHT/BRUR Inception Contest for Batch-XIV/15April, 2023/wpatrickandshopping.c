#include<stdio.h>
#include<math.h>
int main()
{
    int d1,d2,d3;
    scanf("%d %d %d",&d1, &d2, &d3);
    if(d1>d2+d3)
    {
        printf("%d\n",2*d2+2*d3);
    }
     else if(d2>d1+d3)
    {
        printf("%d\n",2*d1+2*d3);
    }
     else if(d3>d1+d2)
    {
        printf("%d\n",2*d1+2*d2);
    }
    else
    {
        printf("%d\n",d1+d2+d3);//when every distance are same then sum all of them
    }
    return 0;
}