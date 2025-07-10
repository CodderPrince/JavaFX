#include<stdio.h>
#include<string.h>
int main()
{
    int t;
    scanf("%d",&t);
    getchar();
    while(t--)
    {
        char str[10001];
        int wordcount=0;
        gets(str);
        for(int i=0; i<strlen(str); i++)
        //loop ta cholbe sentence er shes porjonto
        {
            if (str[i] == ' ' && str[i + 1] != ' ')
            /*at first check korbe ekta word er pore space peyese kina jodi space na pai tahole
             porer condition check korbe space na thakar condition true hole word 1 count hobe.*/
            /*hello,raajkumar!how are.you?
            computer recoginze korte pare konta character ar konta symbol
            index 4 str[4] howar por se check kore dekhbe space nai && 4+1 index e space nai 
            tahole word count korbe */
            {
                wordcount++;
            }
        }
        printf("Count = %d\n",wordcount+1);
        /*last e je word ta thakbe tar last charcter porjonto loop cholbe kintu last word
        er pore ar loop cholbe na tai space oo pabe na.. akhon ami jodi manually 1 add kore
        na dei tahole last word ta count hobe na jar jonno ekta count kom hobe*/
    }
    return 0;
}