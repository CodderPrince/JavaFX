#include<stdio.h>
#include<conio.h>

typedef int Number;

void main(){

   Number a,b,c;    // Here a,b,&c are integer type of variables.


   printf("Enter any two integer numbers: ") ;
   scanf("%d%d", &a,&b) ;

   c = a + b;

   printf("Sum = %d", c) ;
}
