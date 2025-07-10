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
            if(str[i]== ' ')
            /*ekhane je condition set koresi count tokhoni hobe jokhon ekta space pabe
            ami jehetu word count korbo tai ekta word shes tokhoni hobe jokhon notun kono 
            space pabe..*/
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