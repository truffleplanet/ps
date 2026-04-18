#include <iostream>

using namespace std;

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);
  cout.tie(nullptr);

  int n;
  cin >> n;

  int arr[n];
  for (int i{}; i < n; ++i)
  {
    cin >> arr[i];
  }

  long long totalMoves{};
  int runningMax = arr[0];
  for (int i{1}; i < n; ++i)
  {
    int diff = runningMax - arr[i];
    totalMoves += max(0, diff);
    runningMax = max(runningMax, arr[i]);
  }
  cout << totalMoves << '\n';
}