#include <stdio.h>  
#include <string.h>  
int main()  
{  
    char str[20];
    scanf ("%s", str);   
    if(strcmp(str,"ABC")==0)
    {
        printf("ARC");
    }
    else if(strcmp(str,"ARC")==0)
    {
        printf("ABC");
    }
    return 0;  
}  