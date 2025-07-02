#include <stdio.h>
int main()
{
	int a, b;
	while(scanf("%d %d", &a, &b) ==2){
		if(a== -1 && b== -1)
        {
			break;
		}
		int stay_channel=0, want_to_go_that_channel=0;
		if(a>b)
        {
			stay_channel = a-b;
			want_to_go_that_channel = 100-a+b;
		}
		else
        {
			stay_channel = b-a;
			want_to_go_that_channel = 100-b+a;
		}
		if(stay_channel < want_to_go_that_channel)
        {
			printf("%d\n", stay_channel);
		}
		else
        {
			printf("%d\n", want_to_go_that_channel);
		}
	}
	return 0;
}
