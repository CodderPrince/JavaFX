//{ Driver Code Starts
#include <bits/stdc++.h>
using namespace std;

// } Driver Code Ends
class Solution
{
public:
    vector<int> getThreeLargest(vector<int> &arr)
    {
        //set
        set<int> Un(arr.begin(),arr.end());

        if(Un.size()<3){
            //using array
            vector<int> result(Un.begin(),Un.end());
            reverse(result.begin(), result.end());
            return result;
        }



         //using iterator
            auto it = Un.end();
            vector<int> res;
              
            --it;
            res.push_back(*it);
            --it;
            res.push_back(*it);
            --it;
            res.push_back(*it);

            return res;

        /*// Use a set to store unique elements in ascending order
        set<int> unique_elements(arr.begin(), arr.end());

        // If there are fewer than 3 elements, return the existing elements in descending order
        if (unique_elements.size() < 3)
        {
            vector<int> result(unique_elements.begin(), unique_elements.end());
            reverse(result.begin(), result.end()); // Reverse to make it descending
            return result;
        }

        // Traverse to get the three largest elements
        auto it = unique_elements.end(); // End of the set (past-the-last element)
        vector<int> result;

        // Move back three steps to get the three largest elements
        --it; // Largest element
        result.push_back(*it);

        --it; // Second largest element
        result.push_back(*it);

        --it; // Third largest element
        result.push_back(*it);

        return result;*/
    }
};

//{ Driver Code Starts.

int main()
{
    int tc;
    cin >> tc;
    cin.ignore(); // To ignore the newline after the number of test cases

    while (tc-- > 0)
    {
        string input;
        getline(cin, input);

        vector<int> arr;
        stringstream ss(input);
        int num;

        while (ss >> num)
        {
            arr.push_back(num);
        }

        Solution obj;
        vector<int> ans = obj.getThreeLargest(arr);

        for (int i : ans)
        {
            cout << i << " ";
        }
        cout << endl;
        cout << "~" << endl;
    }

    return 0;
}

// } Driver Code Ends