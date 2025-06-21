#include<stdio.h>
#include<string.h>
int main()//Safaet47
{

  char s[100000];
  scanf("%s",&s);
  int len=strlen(s);
  while(1)
  {
      if(len==0)
      {
          printf("Yes\n");
          break;
      }
      else if(len<4)
      {
          printf("No\n");
          break;
      }
       if(strncmp(s+len-5,"dream",5)==0)
        {
          len=len-5;
        }
     else if(strncmp(s+len-5,"dream",5)==0)
      {
          len=len-5;
      }
      else if(len>=6 && strncmp(s+len-6,"dream",6)==0)
      {
          len=len-6;
      }
      else if(len>=7 && strncmp(s+len-7,"dream",7)==0)
      {
          len=len-7;
      }
      else
      {
          printf("No\n");
          break;
      }
  }

}