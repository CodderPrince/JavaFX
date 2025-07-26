#include <iostream>
#include <vector>
#include <algorithm> // for reverse
using namespace std;

void rotateArr(vector<int> &arr, int d)
{
    int n = arr.size();
    d = d % n; // Handle cases where d >= n

    // Step 1: Reverse the first d elements
    reverse(arr.begin(), arr.begin() + d);
    for (int i : arr)
    {
        cout << i << " ";
    }
    cout << endl;
    // Step 2: Reverse the remaining elements
    reverse(arr.begin() + d, arr.end());
    for (int i : arr)
    {
        cout << i << " ";
    }
    cout << endl;
    // Step 3: Reverse the entire array
    reverse(arr.begin(), arr.end());
    for (int i : arr)
    {
        cout << i << " ";
    }
    cout << endl;
}
  
int main()
{
    vector<int> arr = {1, 2, 3};
    int d = 2;    
    rotateArr(arr, d);

    for (int i : arr)
    {
        cout << i << " ";
    }
    return 0;
}
