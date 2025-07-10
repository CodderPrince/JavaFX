#include<stdio.h>
#include<string.h>
int main()
{
    int t;
    scanf("%d",&t);
    getchar();
    while(t--)
    {
        char sen[100001], ch[2];
        int count=0;
        gets(sen);
        gets(ch);
        for(int i=0; i<strlen(sen); i++)
        {
            if(sen[i]==ch[0])
            {
                count++;
            }
        }
        if(count>0)
        {
            printf("Occurrence of '%c' in '%s' = %d\n",ch[0],sen,count);
        }
        else
        {
            printf("'%c' is not present\n",ch[0]);
        }

    }
    return 0;

}