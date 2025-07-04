#include <stdio.h>
int main() { 
  int n, i, r, p, b, tb;
  while (scanf("%d", &n) == 1) //n=6  n==8  n==7
  {
    tb = 0; 
    if (n % 3 == 2)//n==2
    {
      p = n + 1;//p=9
    }
    else
    {
      p = n;//p=6  p=7
    }
    for (i = p; i >= 3; i = b + r) 
    /*i=6; i=2*/ /*i=9; */ /*i=7; */
    {
      b = i / 3; //b=2  b=3   b=2
      r = i % 3; //r=0  r=0  r=1
      tb = tb + b; //tb=2  tb=3
    }
    if (n % 6 == 0)
      n = n + 1;//n=7
    if ((n + 2) % 6 == 0)
      n = n + 1; 
    printf("%d\n", n + tb);//7+2=9   9+3=12
  }
  return 0;
}
