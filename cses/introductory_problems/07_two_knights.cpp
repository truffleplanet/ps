#include <iostream>

using namespace std;

/*
 * 자 생각해보자.
 * 어떤 위치에 knight를 놓으면, 그 knight가 갈 수 있는 위치 외에는 전부 knight를 놓을 수 있다.
 * 한 knight만 다른 knight에 도달하는 경우는 없는 것이다.
 *
 * 다만 이 사실만으로 접근하면 O(sigma(k^2))에 근사한 시간이 걸린다.
 * 따라서 좀 더 접근해보자면..
 *
 * 전체 보드에서 2개 뽑는 조합에서
 * 서로 공격하는 쌍의 수를 빼보면 어떨까?
 *
 * 가능한 이동 방향은
 * (+- 1, +- 2) (+- 2, +- 1)
 * 으로 총 8방향인데
 * 중복을 피하려면, 4방향만 보면 된다
 * 따라서
 * (1, 2) 보고 (-1, -2) 보지 않고
 * (2, 1) 보고 (-2, -1) 보지 않고
 * (1, -2) 보고 (-1, 2) 보지 않고
 * (2, -1) 보고 (-2, 1) 보지 않기로 한다.
 * 보드가 k * k 일 시 각 방향 당 놓을 수 있는 경우의 수는
 * (k - 1) * (k - 2)이다 (k == 1 || k == 2 이면 0)
 *
 * c(k^2, 2) --> k * (k - 1) / 2 이다.
 * 따라서 k^2 * (k^2 - 1) / 2 - 4 * (k - 1) * (k - 2) 를 계산하면 되고
 */

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;

  for (int i{1}; i <= n; ++i)
  {
    const long long k = i;
    const long long k2 = k * k;
    const long long comb = (k2 * (k2 - 1)) >> 1;
    const long long attackCases = ((k - 1) * (k - 2)) << 2;
    cout << comb - attackCases << '\n';
  }

  return 0;
}