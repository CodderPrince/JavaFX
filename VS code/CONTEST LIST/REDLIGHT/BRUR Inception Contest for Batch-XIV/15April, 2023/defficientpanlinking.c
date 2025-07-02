#include <stdio.h>
#include<string.h>
int main() {
    int t; 
    scanf("%d", &t); 
    getchar();
    while (t--) 
    {
        char num[10001]; 
        // number of applications..here are big number so we declare string
        // input number of applications
        gets(num);
        int officers = 20; // number of officers
        int min_unprocessed = 0; // minimum number of unprocessed applications
       for(int i=0; i<strlen(num); i++)
        /*20 jon officer ase so 20 er theke besi application porle tokhon unprocessed hobe*/
        {
            min_unprocessed =  (min_unprocessed*10 + (num[i] - '0')) % officers;
            /*joto gulo application joma porbe sobgulo officer er moddhe soman bhabe divide
            kore diye extra unprocessed value ta amake ber korte hobe*/
        }        
        printf("%d\n", min_unprocessed);
    }
    return 0;
}
/*ekhane ekta logic use koresi string er jonno input number onek boro hote pare tai
every single number check korbe ar tar remainder ber korbe
num[i]-'0' sub na korle character type er value output hobe kintu amar integer type lagbe 
num=127; n[0]=1; 0*10 + 1=1%20=1
n[1]= 2 ; 1*10 + 2= 12%20=12
n[2]=7; 12*10 + 7= 127%20=7
every single character niye kaj koresi*/