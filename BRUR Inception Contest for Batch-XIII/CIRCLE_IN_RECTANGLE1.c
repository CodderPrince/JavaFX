#include<stdio.h>
int main()
{
    int w,h,x,y,r;
    scanf("%d %d %d %d %d",&w, &h, &x, &y, &r);
    w= w-r;
    h= h-r;
    if(r<=x && r<=y && x<=w && y<=h)
    {
        printf("Yes\n");
    }
    else
    {
        printf("No\n");
    }
    return 0;
}