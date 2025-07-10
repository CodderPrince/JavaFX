#include<stdio.h>
#include<string.h>
int main()
{
    int t;
    scanf("%d",&t);
    getchar();
    while(t--)
    {
        char sentence[1001], str[1001];
        /*ekhane 2ta string nibo ektate full sentence ta input nibo arekta string e 
        every character store korbo ar hisab kore dekhbo condition match hoi kina
        size question e deya ase null character er jonno 1 size kore extra niyesi*/
        int count=0,j=0;
        //2nd string er j er 0 index theke character store korbo tai initial kore diyesi
        gets(sentence);
        for(int i=0; i<strlen(sentence); i++)
        {
            if(sentence[i]=='a' || sentence[i]=='e' || sentence[i]=='i' || sentence[i]=='o' || sentence[i]=='u')
            {
                str[j] = sentence[i];
                /*akhon str er j er 0 index theke every character check korbo vowel kina ar 
                sekhane store kore count korbo*/
                count++;
            }
        }
        printf("Number of vowels = %d\n",count);
    }
    return 0;
}