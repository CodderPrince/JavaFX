#include<stdio.h>
#include<math.h>
#define PI acos(-1)
int main()
{
    int t,i;
    double length,width,radius,area_of_circle, area_or_rentangle;
    while(scanf("%d",&t) ==1)
    {
        for(i=1;i<=t;i++)
        {
            scanf("%lf",&length);
            radius=length/5;
            width=(length*6)/10;
            area_of_circle = PI*radius*radius;
            area_or_rentangle = (length*width)-area_of_circle;
            printf("%.2lf %.2lf\n",area_of_circle,area_or_rentangle);
        }
    }
    return 0;
}