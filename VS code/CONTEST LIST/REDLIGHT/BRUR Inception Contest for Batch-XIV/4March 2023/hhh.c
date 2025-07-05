#include <math.h>
#include <stdio.h>
float distance(int x1, int y1, int x2, int y2)
{
	return sqrt((x2 - x1)*(x2 - x1) + (y2 - y1)*(y2 - y1));
}
int main()
{
	printf("%f", distance(3, 4, 4, 3));
	return 0;
}
