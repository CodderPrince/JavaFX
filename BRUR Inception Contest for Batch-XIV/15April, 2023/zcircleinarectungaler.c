#include<stdio.h>
int main()
{
    int w,h,x,y,r;
    scanf("%d %d %d %d %d",&w, &h, &x, &y, &r);
    w= w-r;//w dara rectangle er x axis bujasse
    h= h-r;//h dara rectangle er y axis bujasse
    if(r<=x && r<=y && x<=w && y<=h)
    /*ekhane x axis and y axis theke circle er radius sub kore diye check koresi
    karon radius circle er center theke boro hote parbe na abar circle er center
    x axis and y axis thekeo boro hote parbe na*/
    {
        printf("Yes\n");
    }
    else
    {
        printf("No\n");
    }
    return 0;
}