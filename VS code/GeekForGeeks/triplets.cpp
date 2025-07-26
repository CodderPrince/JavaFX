//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution
{
public:
    int countTriplets(vector<int> &arr, int target);
};

int Solution::countTriplets(vector<int> &arr, int target)
{
    int cnt = 0;
    int len = arr.size();

    // Recursive function to find combinations
    function<void(int, int)> combination = [&](int i, int ts)
    {
        // Base case: if the sum equals the target
        if (ts == target)
        {
            cnt++;
            return;
        }

        // Base case: if index out of bounds or sum exceeds the target
        if (i >= len || ts > target)
        {
            return;
        }

        // Pick the current element
        combination(i + 1, ts + arr[i]);

        // Do not pick the current element
        combination(i + 1, ts);
    };

    // Start recursion
    combination(0, 0);

    return cnt;
}

//{ Driver Code Starts.

vector<int> lineArray()
{
    string line;
    getline(cin, line);
    stringstream ss(line);
    vector<int> arr;
    int num;
    while (ss >> num)
    {
        arr.push_back(num);
    }
    return arr;
}

int main()
{
    int t;
    cin >> t;
    cin.ignore();
    while (t--)
    {
        vector<int> arr = lineArray();
        int target;
        cin >> target;
        cin.ignore();

        Solution ob;
        int res = ob.countTriplets(arr, target);
        cout << res << endl;
        cout << "~" << endl;
    }
}

// } Driver Code Ends
