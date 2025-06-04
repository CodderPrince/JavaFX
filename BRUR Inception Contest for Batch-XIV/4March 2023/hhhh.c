#include<stdio.h>
#include<math.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
    float distance;
    int x1,y1,x2,y2;
    scanf("%d %d %d %d", &x1,&y1,&x2, &y2);
    distance= sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
    printf("Distance: %.2f\n",distance);
    }
    return 0;
}