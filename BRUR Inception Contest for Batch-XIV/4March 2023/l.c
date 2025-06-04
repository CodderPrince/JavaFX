#include<stdio.h>
#include<math.h>
int main(){
    int h,m;
    double deg;
    scanf("%d %d",&h,&m);
    float x= (60*h) - (11*m);
    deg= x/2;
    if(deg>=180)
    {
    printf("%.7lf",360-deg);
    }
    else
    {
        printf("%.7lf",deg);
    }
    return 0;
}