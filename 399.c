#include<stdio.h>
#include<string.h>
int mani()
{
    int t;

    scanf("%d",&t);
    while(t--)
    {
         char str[1000],rev[1000];
        scanf("%s",str);
         rev[1000] = strrev(str);
        if(strcmp(str,rev)==0)
        {
            printf("Yes! It is pallindrome!\n");
        }
        else
        {
            printf("sorry! it is not palindrome\n");
        }

        printf("%s",str);
    }
    return 0;
}
