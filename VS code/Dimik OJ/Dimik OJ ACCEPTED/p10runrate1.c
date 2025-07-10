#include<stdio.h>
int main()
{
    int t;
    scanf("%d",&t);
    while(t--)
    {
        int myteamrun, opponentrun,playedball,unplayedball;
        double currentrunrate,requiredrunrate;
        scanf("%d %d %d",&opponentrun,&myteamrun,&unplayedball);
        playedball= 300-unplayedball;//jehetu odi match 50 over er hoi tai 300 theke minus hobe
        currentrunrate= myteamrun/(playedball/6.0);
        //played ball ke 6 dara divide korle every over er runrate peye jabo ar amar team joto run korese seta dara divide korle amar crr peye jabo
        //ekhane hoi casting korte hobe or manually double korte hobe karon crr er value double e niyesi
        if(opponentrun<myteamrun)
        {
            requiredrunrate= 0.0;//karon already besi run kore felle amar to ar rrr dorkar nei match win kore felesi already
        }
        else
        {
            requiredrunrate= (opponentrun-myteamrun+1)/(unplayedball/6.0);
            /*opponent er run theke amar team run sub kore tar sathe 1 add korte hobe match win
            korar jonno ebar unplayed ball ke manually casting kore 6 dara divide kore crr ber
            koresi tarpor seta amar joto run lagbe seta diye divide koresi tahole rrr peye jabo*/
        }
        printf("%.2lf %.2lf\n", currentrunrate,requiredrunrate); 
    }
    return 0;
}