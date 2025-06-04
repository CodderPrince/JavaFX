#include<stdio.h>
int main()
{
    int t;
    scanf("%d", &t);
    while(t--)
    {
        long long int n,x,y,p,q;
        scanf("%lld", &n);
        if(n==1 || n==2)
        {
            printf("0\n");
        }
        //n==1,2 hole bishops gulo already diagonal thakbe tai print 0 korte hobe
        else
        {
            if(n%2==0)//for even number apply this formula....
            /*ekhane ekta logic use kora hoyese n=6 hole 1st,2nd and last bishof bade baki
            3ta bishof hisab korte hobe */
            {
                 x= n-3;
                 y= x/2;
                printf("%lld\n", (y*2)+(n-y-1));
            }
            else//for odd number apply this formula
            {
                 p= n-2;
                 q= p/2;
                printf("%lld\n", (q*2)+(n-q-1));
            }
        }
    }
    return 0;
}
/*n=6 
 1b 2 3b 4 5b 6
 1 2b 3 4b 5 6b
 1 2 3  4 5 6
 1 2 3 4 5 6
 1 2 3 4 5 6
 1 2 3 4 5 6
 ei bhabe bishop gulo thakbe sekhane 1b alltime fixed and 1st row te je bishop thakbe
 sobgulo 1 ta move dite hobe minimum
 n even hole 1st bishop 1b fixed 2b and last bb (6b) 1ta move dite hobe
 amar ekhon ber korte hobe kotogulo 2 ta move dite hobe ekhane jehetu 1b fixed and 2b and lastb
 minimum 1 ta move ditei hobe tai n theke 3 alltime sub hobe
 x= n-3
 ebar ber korte hobe bakigulor moddhe kongulo 2 ta move dite hobe karon 1st row gulo to 1ta move
 dilei hobe tai bakigulo ke 2 dara divide korbo
 y= x/2
 ebar ber korte hobe total kotogulo move dite hobe er jonno jehetu y holo 2ta move er value
 tai eke y dara multiply korbo ar sum korbo total n theke 2 er sonkha y sub korbo ar 1b jehetu
 fixed alltime tai extra 1 sub korbo
 total move= (y*2) + (n-y-1)
 odd er somoi same process only last er move ta 2 bar move korte hobe tai n theke 2 bad jabe
 
 1b and 2b er jonno*/

 /*
 n=7
 1b 2 3b 4 5b 6 7b 
 1 2b 3 4b 5 6b 7 
 1 2 3 4 5 6 7 
 1 2 3 4 5 6 7 
 1 2 3 4 5 6 7 
 1 2 3 4 5 6 7 
 1 2 3 4 5 6 7 
 ekhane valo kore kheyal dekha jai 2nd row er last 6b er jonno 2 ta move lagbe
 amar sob somai mone rakhte hobe 1st row er jonno 1ta move dite hobe ar 2nd row er
 last bishop er jonno koita move dite hobe 
 jodi n=odd number hoi tokhon alltime 2nd row er last bishop er jonno 2 ta move ditei hobe
 tai 1b and 2b ei ta shudu fixed mane 1b fixed and 2b one move lagbe 
 p= n-2
 jehetu 2 fixed koresi akhon ber korte hobe kotogulor jonno minimum 2 ta move lagbei
 q= p/2
 duita move tader jonnoi lagbe jader ke 2 diye divide kora jabe
 ar total ber korar bistarito agei likhe rekhesi
 total= (q*2) + (n-q-1)
 ekdom simple ekta problem ekhane shudu out of the box thinking korte hobe
 */