#include <iostream>
#include <vector>

using namespace std;

inline long long ll(int x) { return static_cast<long long>(x); }

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;

  struct Entry
  {
    int pos;
    int time;
  };
  vector<Entry> data(n);

  for (int i{}; i < n; ++i) cin >> data[i].pos;
  for (int i{}; i < n; ++i) cin >> data[i].time;

  long long timeTotal{max(ll(data[n - 1].time), ll(data[n - 1].pos))};
  for (int i{n - 2}; i >= 0; --i)
  {
    timeTotal += data[i + 1].pos - data[i].pos;
    timeTotal = max(timeTotal, ll(data[i].time));
  }
  timeTotal += data[0].pos;

  cout << timeTotal << '\n';

  return 0;
}