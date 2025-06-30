#include <stdio.h>
#include <stdlib.h>

int main()
{
	int W, H;
	int x, y, r;

	scanf("%d%d%d%d%d", &W, &H, &x, &y, &r);
	W -= r, H -= r;
	if (r <= x && x <= W && r <= y && y <= H)
     printf("Yes\n");
	else printf("No\n");
	return 0;
}