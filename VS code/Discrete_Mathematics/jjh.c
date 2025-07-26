#include <stdio.h>

int main() {
  char str[100];
  int i = 0;

  while (1) {
    char ch = getchar();
    if (ch == '\n') {
      break;
    } else {
      str[i++] = ch;
    }
  }

  str[i] = '\0';

  printf("The string is: %s\n", str);

  return 0;
}

