#include<stdio.h>
#include<math.h>
int main()
{
    int t;
    int n,i,j,min_taxi=0, count[5]={0};
    //array declare where initialize each group 0
    scanf("%d",&t);
    while(t--)
    {
        scanf("%d",&n);
        count[n]++;
        //je number jotobar input sei index totobar count hote thakbe
    }
    min_taxi= count[4];
    //jehetu 4 jon er jonno ekta taxi lagbe
    min_taxi= min_taxi + count[3];
    //3 jon err jonnoo ekta taxi fixed korbo kintu ekta sit faka ase
    count[1]= count[1] - count[3];
    /*count[1]= 4, count[3]=2 tahole 4-2=2 count[1] theke 2 jon manush count[3] taxi te 
    jete parbe kintu jodi reverse hoi count[1]= 2, count[3]=4 negative hobe na tai 
    abar condition dite hobe*/
    if(count[1]<0)
    count[1]=0;
    /*jodi negative  mane 0 theke kom hoi tokhon count[1] er value zero kore dibo 
    karon negative value tokhoni asbe jokhon count[1] er moddhe ar kono value nei*/
    min_taxi= min_taxi + count[2]/2;
    // ebar 2 err count dekhbo count[2]=6,7 jetai hok tar value asbe 3 tai abar condition hobe
    if(count[2]%2 == 0)
    count[2]=0;
    /*count[2]=4,6 group hoi tar mane 4*2=8, 6*2=12 serially 2 and 3 taxis alloted for
    ebar count[2]=0 kore dibo karon oi group e ar keu baki nei sobai taxi peye gese*/
    else
    count[2]=1;
    /*jodi count[2]=3,5 hoi tahole 6,10 jon manush hoi tahole 1 and 2 ta taxi allot
    holeo 2 jon or 1 group count[2] baki theke jai tai setai count kore rakhlam*/
    count[1]= count[1] + count[2]*2;
    /* ebar count[1] er taxi allot korbo jodi count 1 extra theke thake tahole tar jonno amader
    taxi allot korte hobe ekhane count 2 ke 2 dara multiply koresi count[2]=1 or 0 hote pare
    karon er theke besi hole amora alada taxi allot koresi count[2] er jonno*/
    if(count[1]%4 == 0)
    min_taxi = min_taxi + count[1]/4;
    /*ekhane 4 diye divide kore dekhbo reminder dei kina jodi count[1] er moddhe 8 thake tahole
    reminder dibe na 2 ta taxi allot korlei hoye jabe kintu jodi count 1 er moddhe 7 thake 
    tahole reminder 3 dibe*/
    else
    min_taxi = min_taxi + count[1]/4 + 1;
    /*jodi count[1]=7 tahole 7/4=1+1=2 taxi lagbe eibhabe algoritham ta cholte thakbe*/
    printf("%d\n", min_taxi);
    return 0;
}