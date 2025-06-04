#include<stdio.h>
#include<math.h>
#define PI acos(-1)
int main()
{
    int t,i;
    double length,width,radius,area_of_circle, area_or_rentangle;
    while(scanf("%d",&t) ==1)
    //its mean a infinite loop where we input unlimited test case
    //ekhane jotogulo value input nibo == er por toto number dibo
    {
        for(i=1;i<=t;i++)
        {
            scanf("%lf",&length);
            radius=length/5;
            //question er mothe radius alltime length er 5 bhager 1 bhag hobe
            width=(length*6)/10;
            /*jehetu question aunujayi flag ratio 10:6 so we can find the width ratio
            to muliplicate (input_length*6)/10*/
            area_of_circle = PI*radius*radius;
            area_or_rentangle = (length*width)-area_of_circle;
            //rectangle theke circle area sub korlei hoye jabe
            printf("%.2lf %.2lf\n",area_of_circle,area_or_rentangle);
        }
    }
    return 0;
}