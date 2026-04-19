#include <iostream>

using namespace std;

/*
 *  첫번째 케이스가 가능하고
 *  n번째 케이스로 n + 1을 표현할 수 있는 전형적인 재귀 문제이다.
 *
 *  n == 1 일 시 때 1번의 움직임이 필요하고
 *  move(1) == 1 이고
 *  move(n+1) == move(n) + move(n) + 1 이므로
 *  1 -> 3 -> 7 -> 15 -> ... 2^(k) - 1  같은 규칙성을 띤다.
 *  점화식을 풀어보자면,
 *  move(n + 1) == 2 * move(n) + 1
 *  t(n) == move(n) + 1
 *  t(n + 1) - 1 == 2 * t(n) -1
 *  t(n + 1) == 2 * t(n)
 *  이므로 t(n)은 등비수열이다.
 *  따라서 t(1) = 2 이므로 t(n) = 2^n 이다.
 *  move(n) == t(n) - 1 이므로
 *  move(n) == 2^n - 1 이다.
 */

void printMove(int a, int b);
void hanoi(int n, int from, int to, int alt);

int main()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;

  cout << (1 << n) - 1 << '\n';
  hanoi(n, 1, 3, 2);

  return 0;
}

void printMove(int a, int b)
{
    cout << a << ' ' << b << '\n';
}

void hanoi(int n, int from, int to, int alt)
{
  if (n == 1)
  {
    printMove(from, to);
    return;
  }

  hanoi(n - 1, from, alt, to);
  printMove(from, to);
  hanoi(n - 1, alt, to, from);
}
