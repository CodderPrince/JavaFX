#include<stdio.h>
#include<string.h>
int main()
{
int t;
scanf("%d", &t);
getchar();
while(t--)
{
char str[101];
gets(str);
int i;
for(i=0; i<strlen(str); i++)
{
if(str[i]>= 'A' && str[i]<= 'Z')
{
    printf("%d", str[i]-64);
    /*A er ascii value=65 tai input character theke 64 sub korbo ar string tar length porjonto
    loop chalabo ar ebhabe check korte thakbo ar ascii value print korte thakbo ar ekta test
    case shes hole loop er baire giye ekta new line dibo*/
}
}
printf("\n");
}
return 0;
}
