//check palindrome without using library function

#include <stdio.h>
#include<string.h>

int main() 
{
  char str[100];
  int i, len;

  printf("Enter a word: ");
  scanf("%s", str);

  len = strlen(str);

  for (i = 0; i < len / 2; i++) 
  {
    if (str[i] != str[len - 1 - i]) 
    {
      printf("'%s' is not a palindrome.\n", str);
      return 0;
    }
  }

  printf("%s is a palindrome.\n", str);

  return 0;
}
