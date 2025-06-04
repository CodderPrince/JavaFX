#include<stdio.h>
#include<string.h>
getchar();
int main()
{
    char str[100001];
    gets(str);
    int len = strlen(str);
    for(int i=0; i<len; i++)
    {
        if(str[i] == '.')
        {
            printf("0");
        }
        else if(str[i] == '-' && str[i+1] == '.')
        {
            printf("1");
            i++;
        }
        else if(str[i] == '-' && str[i+1] == '-')
        {
            printf("2");
            i++;
        }
    }
    return 0;
}
/*question e jemon bola hoyese sei bhabei condition set kora hoyese*/