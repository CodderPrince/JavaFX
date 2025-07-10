#include<stdio.h>
#include<string.h>
int main()
{
    int t;
    scanf("%d",&t);
    getchar();
    while(t--)
    {
        char str[1001];
        //string size limit deya nei tai mon moto ekta niyesi
        int i;
        gets(str);
        for(i=0; i<strlen(str); i++)
        {
            if(str[i]=='a' || str[i]=='e' || str[i]=='i' || str[i]=='o' || str[i]=='u')
            {
             printf("%c",str[i]);
            }
            /*ekhane vowel print korar condition set kore diyesi ar for loop theke ber hoye
            giye ekta new line print korbe.. string ta cholbe string length porjonto..
            keu jodi condition set kore null character porjonto cholbe setao sothik karon
            duita condition ei ek*/
            
        }
        printf("\n");
        for(i=0; i<strlen(str); i++)
        {
            if(str[i]!='a' && str[i]!='e' && str[i]!='i' && str[i]!='o' && str[i]!='u' && str[i]!=' ')
            /*vowel er sathe consonant er ekta difference ase ekhane shese ekta space condition add
            kore diyesi karon vowel er condition e sob or diye attached silo but here we attached 
            and condition .. so if we avoid space condition it means when loop search every word
            by word then print consonant defferent different... mane "hello prince" ekhane print 
            korbe hll prnc ekhane space print korbe jehetu and condition use koresi ar output format
            thik rakhar jonno space condition use koresi*/
            {
                printf("%c",str[i]);
            }
        }
        printf("\n");
    }
    return 0;
}