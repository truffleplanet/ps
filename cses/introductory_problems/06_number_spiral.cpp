#include <iostream>

using namespace std;

/*
 * 1-based idx
 * layer == max(y, x)
 * layer가 홀수일 시는 (layer, 1)에서 시작, 짝수일 시는 (1, layer)에서 시작
 * 홀수일시는 right -> up 진행, 짝수일 시는 down, left로 진행
 * 1x1 = 1, 2x2=4, 3x3=6, 4x4=16인데,
 * 각 레이어의 시작값은 직전 레이어의 마지막 값+1이니
 * 따라서 각 레이어의 초기값은 (layer - 1)^2 + 1이다.
 */

int main ()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int t;
  cin >> t;

  do
  {
    long long y, x;
    cin >> y >> x;

    const long long layer = max(y, x);
    const long long startVal = (layer - 1) * (layer - 1) + 1;
    const long long sign = (layer & 1) * 2 - 1;
    cout << startVal + (layer - 1) + (sign * (x - y)) << '\n';
  } while (--t);

  return 0;
}