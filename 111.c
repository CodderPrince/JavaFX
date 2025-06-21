#include<stdio.h>
int main (){
    int a,b,div;
    scanf("%d %d",&a,&b);
    if(a>12 && b<=12){
        div = 24-a;
        printf("O JOGO DUROU %d HORA(S)\n",div+b);
    } else if (a<=12 && b>12){
        printf("O JOGO DUROU %d HORA(S)\n",b-a);
    }else if(a<b && a<=12 && b<= 12){
      printf("O JOGO DUROU %d HORA(S)\n",b-a);
    }else if(a>b && a<=12 && b<=12){
      div= 24-a;
      printf("O JOGO DUROU %d HORA(S)\n",div+b);
    } else if (a==b && a<12 && b<12){
      printf("O JOGO DUROU 24 HORA(S)\n");
    }



}