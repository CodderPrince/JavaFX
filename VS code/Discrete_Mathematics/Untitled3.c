#include<stdio.h>
#include<string.h>

int main()
{
    int t;
    scanf("%d",&t);
    getchar();
    while(t--)
    {
        char str [1001], str2[1000];
        gets(str);
        int i,j;
        for(i=0; i<strlen(str); i++)
        {
            if(str[i]!=' ')
            {
                str2[j] = str[i];
                j++;
            }
            else if(j>0)
            {
                str2[j]='\0';
                strrev(str2);
                printf("%s ",str2);
                j=0;
            }
            else
            {
                printf("\n");
            }
        }

        if(j>0)
        {
            str2[j] = '\0';
            strrev(str2);
            printf("%s",str2);
        }
        printf("\n");
    }

    return 0;
}
