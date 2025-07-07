#include <iostream>
#include <vector>

using namespace std;

int binarySearch(const vector<int> &array, int x)
{
    int n = array.size();
    int k = 0;

    for (int b = n / 2; b >= 1; b /= 2)
    {
        while (k + b < n && array[k + b] <= x)
        {
            k += b;
        }
    }

    if (array[k] == x)
    {
        // x found at index k
        return k;
    }

    // If x is not found, return -1 or any other appropriate value
    return -1;
}

int main()
{
    vector<int> array = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
    int x = 7;

    int result = binarySearch(array, x);

    if (result != -1)
    {
        cout << "Element " << x << " found at index " << result << endl;
    }
    else
    {
        cout << "Element " << x << " not found in the array." << endl;
    }

    return 0;
}
