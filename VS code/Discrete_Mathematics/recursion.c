#include <stdio.h>

int sum(int n) {
  if (n == 0) {
    return 0;
  } else {
    return n + sum(n - 1);
  }
}

int main() {
  int x = 100;
  int result = sum(x);
  printf("The sum of all the numbers from 1 to 100 is %d\n", result);
  return 0;
}
