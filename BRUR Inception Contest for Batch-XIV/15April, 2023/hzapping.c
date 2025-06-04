#include <stdio.h>
int main()
{
	int a, b;
	while(scanf("%d %d", &a, &b) ==2){
		if(a== -1 && b== -1)
        //here total channel 0 to 99 .. so negative not allow
        {
			break;
		}
		int stay_channel=0, want_to_go_that_channel=0;
		if(a>b)//3,9   0,99
        {
			stay_channel = a-b;
			want_to_go_that_channel = 100-a+b;
		}
		else
        {
			stay_channel = b-a;//9-3=6    99-0=99
			want_to_go_that_channel = 100-b+a;//100-9+3=94   100-99+0=1
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
//here we alltime print short distance
/*amader emon code likhte hobe jekhane koto number channel e ami asi and koto number channel
e ami jete chai tar jonno kotogulo step amar jete hote tar count er value*/