#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
    long long int a,b,tmp,i;//a=3,b=6
    scanf("%lld %lld",&a,&b);//a=5 b=7
    if(b>a)
    {
        tmp=b;
        b=a;
        a=tmp;
    }
    //a and b sort kore nibe here a>b
    //a and b sort kore nibe a=7, b=5
    if(a%b==0)
    {
        printf("LCM = %lld\n",a);//6
    }
    //condition false tai niche jabe
    else
    {
    for(i=a; ; i= i+a)
    /*ekhabe condition dei ni eta korar karon hosse loop ta totokhon cholbe jotokhon i%b==0
    na hoi
    i=7, b=5, 7%5!=0
    i=7+7=14, b=5, 14%5!=0
    i=7+7=14+7=21, b=5, 21%5!=0
    i=7+7=14+7=21+7=28, b=5, 28%5!=0
    i=7+7=14+7=21+7=28+7=35, b=5, 35%5==0
    EBAR LCM = 35 print hobe
    etar mane holo boro sonkhatar gunitok porjonto loop cholbe jotokhon small number diye 
    divide hoi
    */
    {
        if(i%b==0)
        {
            break;
        }
    }
    printf("LCM = %lld\n",i);
    }
    }
    return 0;
}