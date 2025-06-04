#include<stdio.h>
int main()
{
    int sec, hour,min;
    scanf("%d",&sec);
   hour= sec/3600;//total sec ke hour e convert korte hobe 1hour=3600 second
   sec= sec%3600;//hour e convert korar por obosisto sec
   min= sec/60;//obosisto second ke minute e convert korte hobe
   sec= sec%60;//obosisto second hisab korte hobe
    printf("%d:%d:%d\n",hour,min,sec);
    return 0;
}