//{ Driver Code Starts
#include <bits/stdc++.h>

using namespace std;

// } Driver Code Ends
// User function template for C++
class Solution
{
public:
    // Function returns the second
    // largest elements
    int getSecondLargest(vector<int> &arr)
    {
        // Code Here
        // use set
        set<int> uniqueElements(arr.begin(), arr.end());

        /*
        if less than two elements that means here no 2nd largest
        */
        if (uniqueElements.size() < 2)
        {
            return -1;
        }
         
        // now set the cursor to last using iterator
        auto it = uniqueElements.end();
        --it;       // move the cursor to largest element
        --it;       // move the cursor to the 2nd largest
        return *it; // print the value
    }
};

//{ Driver Code Starts.

int main()
{
    int t;
    cin >> t;
    cin.ignore();
    while (t--)
    {
        vector<int> arr;
        string input;
        getline(cin, input);
        stringstream ss(input);
        int number;
        while (ss >> number)
        {
            arr.push_back(number);
        }
        Solution ob;
        int ans = ob.getSecondLargest(arr);
        cout << ans << endl;
        cout << "~" << endl;
    }
    return 0;
}

// } Driver Code Ends