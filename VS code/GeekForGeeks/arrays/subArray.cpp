#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define nl endl

vector<vector<int>> getSubArrays(vector<int> &arr)
{
    int n = arr.size();
    vector<vector<int>> result;

    // Pick starting point
    for (int i = 0; i < n; i++)
    {
        vector<int> subarray;
        // Pick ending point
        for (int j = i; j < n; j++)
        {
            subarray.push_back(arr[j]);
            result.push_back(subarray);
        }
    }

    return result;
}
int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);
    vector<int> arr = {1, 2, 3};
    vector<vector<int>> res = getSubArrays(arr);

    // Print all subarrays
    for (const auto &subarray : res)
    {
        cout << "[";
        for (size_t i = 0; i < subarray.size(); ++i)
        {
            cout << subarray[i];
            if (i < subarray.size() - 1)
            {
                cout << ", ";
            }
        }
        cout << "]," << " ";          
    }
    cout << nl;

    return 0;
}
