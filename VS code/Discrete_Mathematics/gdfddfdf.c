#include<stdio.h>
int main()
{
    printf("a\n");
    printf("%d%c%f",10,'x',1.23);
    printf("\nb\n");
    printf("%21d%c%40.2f",1234,'x',1.23);
    printf("\nc\n");
    printf("%d\t%4.2f",1234,456);
    printf("\nd\n");
    printf("\"%08.2f\"",123.4);//it means compile print total 7 digit with . count and if shorter use 0 to makeup
    printf("\ne\n");
    printf("%d%d%d",10,20);
    printf("\n");
}
