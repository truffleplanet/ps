#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    long long int n;
    cin >> n;

    long long sum{((n + 1LL) * n) >> 1};

    for (int i{1}; i < n; ++i)
    {
        long long num;
        cin >> num;
        sum -= num;
    }

    cout << sum << '\n';
    return 0;
}