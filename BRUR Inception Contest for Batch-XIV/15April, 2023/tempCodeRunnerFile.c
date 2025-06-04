#include <stdio.h>
int main() { 
  int n, i, r, p, b, tb;
  while (scanf("%d", &n) == 1)
  {
    tb = 0; 
    if (n % 3 == 2)
    {
      p = n + 1;
    }
    else
    {
      p = n;
    }
    for (i = p; i >= 3; i = b + r) 
    
    {
      b = i / 3; 
      r = i % 3; 
      tb = tb + b; 
    }
    if (n % 6 == 0)
      n = n + 1;
    if ((n + 2) % 6 == 0)
      n = n + 1; 
    printf("%d\n", n + tb);
  }
  return 0;
}
