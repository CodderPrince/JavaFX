// C++ implementation of the approach
#include <bits/stdc++.h>
using namespace std;

// Function to return the number of
// positive integers less than or equal
// to N that have odd number of digits
int odd_digits(int n)
{
	if (n < 10)
		return n;
	else if (n / 10 < 10)
		return 9;
	else if (n / 100 < 10)
		return 9 + n - 99;
	else if (n / 1000 < 10)
		return 9 + 900;
	else if (n / 10000 < 10)
		return 909 + n - 9999;
	else
		return 90909;
}

// Driver code
int main()
{
	int n ;
cin>>n>>endl;
	cout << odd_digits(n);

	return 0;
}
