#include<stdio.h>
#include<conio.h>
int main()
{
    int num=1, res, i, j;
    printf("\t\tTable from 1 to 10\n\n");
    for(i=0; i<10; i++)
    {
        printf("Table of %d = ", num);
        for(j=1; j<=10; j++)
        {
            res = num*j;
            printf("%d  ", res);
        }
        printf("\n");
        num++;
    }

    return 0;
}
