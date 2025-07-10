#include<stdio.h>
#include<string.h>
int main()
{
    int t;
    scanf("%d",&t);
    getchar();
    while(t--)
    {
        int i,j=0;
        /*j er man start ei manually 0 kore dilam 
        karon string alltime 0 index theke start hoi ar
        string er value reverse korar jonno j er 0 
        index thekei store kora start korbo*/
        char str[1002],re[1002];
        //This is raajkumar! //This   is   raajkumar!
        gets(str);
        for(i=0; i<strlen(str); i++)
        {
            if(str[i]!=' ')
            {
             /* 0 index e check kore dekhbe space ase kina
             jodi space na thake tahole 0 index er value ke
             re[j] string er moddhe store kore rakhbe and j++
             kore count korbe ebhabe se string length porjonto
             check korte thakbe ar space na pelei segulo onno
             string er moddhe store korbe*/
                re[j]= str[i];
                /*this etake receive korte kortei j er man 4 hobe karon
                0 index er moddhe t rakhbe ar last
                3 index er moddhe s rakhbe kintu string 
                er shese null character thake tai j er value                         
                akhon 4 erpor jei space pabe ar if condition 
                er moddhe dhukbe na tokhon niche asebe*/
                j++;
            }
            else if(j>0)
                /*ei loop ta kaj korbe jokhon ekhadik word space diye 
                alada kora thakbe jokhon only single word thakbe 
                tokhon er loop ar kaj korbe na*/
            {
                re[j]='\0';
                /*ekhon j er man 4 tai ei condition er moddhe
                dhukbe tapor j er shesher null character ke 
                null kore dibo manually tahole seta r count 
                hobe na*/
                reverse(re);
                /*ebar reverse name e ekta funtion create kore 
                seta call korbo tarpor j string er moddhe je 
                this value ke store koresilam seta ke reverse kore dibo*/
                j=0;
                /*ebar j je count kore 4 hoyesilo seta ke abar
                0 kore dibo jate abar pore word ta count korte pari*/
                printf(" ");
                /*akhon one word reverse kore print kora shes 
                tai ekta space print kore dibo karon amora 
                space ke reverse korbo na*/
            }
            else
                /*is porjonto ebhabe cholbe kintu is er por 
                ekathik space thakai j=0 hobe karon character
                na pawai j er man increase hobe na tai ei loop 
                er moddhe asbe ar jotokon character na pabe space 
                print korte thakbe*/
            {

                printf(" ");
            }
        }
        if(j>0)
         /*jokhon only single ekta word thakbe tokhon ei loop
          ta kaj korbe ar word reverse korte thakbe*/
        {
            re[j]='\0';
            reverse(re);
        }
        printf("\n");
        /*condition jetai use kori na keno sheshe to ekta 
        new line print korte hobe tai ekhane ekta new line
        print korar jonno bola hoyese*/
    }
    return 0;
}
int reverse (char receiver_re[])
    /*ekhon reverse name e je function ke call 
    koralam seta manually create korte hobe er 
    jonno integer reverse name e ekta function 
    nilam jar kaj holo new function create kore 
    re name e je string er moddhe j er value store 
    koresilam seta ke receive kora tai first bracket 
    er moddhe arekta string nibo karon re nijei ekta 
    string ar ekta string ke shudhu arekta string ei 
    receive korte pare tai ekhane receiver_re er name 
    e arekta string nilam jetar kaj holo re string er 
    moddhe je value gulo store ase segulo receive kora.. 
    ekhon function create kora shesh ebar niche thik korte 
    hobe asole ei function tar kaj ta ki*/
{
    int k;
    /*jehetu ekta loop use kore value store koresi 
    tai arekta loop use korei value print korte hobe*/
    for(k=strlen(receiver_re)-1; k>=0; k--)
     /*amora agei jani string er length theke 1 bad 
     deya mane null character bad jabe ar shesh er 
     character theke count howa start hobe.. jehetu 
     amader reverse korte hobe and shesh theke print 
     korte hobe tai len theke 1 bad diye start korbo 
     and loop ta cholbe string er 0 index porjonto ar 
     shesh theke start hobe tai every time 1 kore 
     decrease hote thakbe*/
    {
        printf("%c",receiver_re[k]);
        /*ekhane every word reverse kore print kora
        hobe shudu tai extra space or new line word
        er majkhane use kora jabe na*/
    }
}
/*new function create kora shesh ar etake jokhoni call 
korbo tokhon eta giyr kaj korbe nahole chupchap bose thakbe*/
