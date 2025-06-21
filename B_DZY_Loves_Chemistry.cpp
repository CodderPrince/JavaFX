#include <bits/stdc++.h>
using namespace std;

#define ll long long
#define mod 1000000007            // 1e9 + 7
#define PI 3.14159265358979323846 // acos(-1)
#define nl "\n"

const ll mx = 1e5 + 123;
vector<ll> adj[mx];
bool vis[mx];
ll cnt = 0;

void dfs(int u)
{
    // firstly mark this node is visited
    /*
    visited=1
    non-visited=0
    */
    vis[u] = 1;

    // ranged based or forish loop

    // find all corresponding adjacent list
    for (auto v : adj[u])
    {
        if (vis[v] == 0)
        {
            cnt++;
            dfs(v);
        }
    }
}

int main()
{
    ll nodes, edges;
    cin >> nodes >> edges;

    // special case
    /*if (nodes == 1 && edges == 0)
    {
        cout << "1\n";
        return 0;
    }*/

    int i;
    for (i = 1; i <= edges; i++)
    {
        int u, v;
        cin >> u >> v;
        // if(u==1 && v)
        // bioconditional
        adj[u].push_back(v);
        adj[v].push_back(u);
    }

    // ll cnt = 0;
    for (i = 1; i <= nodes; i++)
    {
        // check nodes are visited or not
        // iƒ not visited then called dfs

        if (vis[i] == 0)
        {
            dfs(i);
        }
    }

    //
    /*Here this is an undirected graph
    That means two ways are possible
    when go 1 to 2 that means it's also possible
    to go 2 to 1
    so power the total count
    */
    ll p = cnt;
    p = pow(2, p);
    cout << p << nl;

    return 0;
}
