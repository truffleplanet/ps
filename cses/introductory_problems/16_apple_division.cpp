#include <iostream>

using namespace std;

int main()
{
    ios_base::sync_with_stdio(false);
    cin.tie(nullptr);

    int n;
    cin >> n;

    long long arr[20];
    long long total{};
    for (int i{}; i < n; ++i)
    {
        cin >> arr[i];
        total += arr[i];
    }
    long long minDiff{total};
    for (int i{}; i < (1 << n); ++i)
    {
        long long partialSum{};
        for (int j = 0; j < n; ++j)
        {
            if (((i >> j) & 1) != 0)
            {
                partialSum += arr[j];
            }
        }
        long long other{total - partialSum};
        minDiff = min(minDiff, abs(other - partialSum));
    }

    cout << minDiff << '\n';
}