#include<stdio.h>
#include<string.h>
int main()
{
    int t;
    scanf("%d",&t);
    getchar();
    while(t--)

    {
        char str[100001];
        int i,len=0,count=1;
        gets(str);
        for(i=0; i<strlen(str); i++)
        {
            if(str[i]==' ' && str[i+1]!= ' ')
            {
                count++;
            }
        }
        printf("%d\n",count);
    }
    return 0;
}