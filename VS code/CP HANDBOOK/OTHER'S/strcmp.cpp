#include <iostream>
#include <vector>
#include <string.h>
using namespace std;

// Define a comparison function for strings
bool comp(string s1, string s2) {
  return strcmp(s1.c_str(), s2.c_str()) < 0;
}

int main() {
  // Create a vector of strings
  vector<string> v = {"apple", "orange", "banana", "pear", "grape"};
  
  // Sort the vector using the comparison function
  //sort(v.begin(), v.end(), comp);
  
  // Print the sorted vector
  for (string s : v) {
    cout << s << " ";
  }
  cout << endl;
  
  return 0;
}
