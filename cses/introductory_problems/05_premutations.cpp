#include <iostream>;

using namespace std;

/*
 *  규칙을 파악해보자
 *  일단 회전이 가능하니 어떤 숫자로 시작하든 상관이 없다.
 *  1
 *  1 2 (x)
 *   (x)
 *  3 1 4 2 (o)
 *  1 3 5 2 4 (o)
 *  1 3 5 2 4 6 (o)
 *  1 3 5 7 2 4 6 (o)
 *  1 3 5 7 9 2 4 6 8 (o)
 *  관찰 결과 5 이상일 시에는 무조건 가능하다.
 *  왜냐하면 1..4까지의 가장 큰 짝수와 가장 작은 홀수의 차가 2 이상이므로, 홀수를 순서대로 적고,
 *  짝수를 이후 순서대로 적는다면 항상 성립한다.
 *  2, 3의 경우는 어떤 순열도 불가능하다.
 *  4의 경우는 예외적으로, 이것이 회전하는 순열이 아니므로, (즉 0과 n-1이 인접하지 않으므로)
 *  순서를 잘 섞어서 조건이 성립하는 순열을 만들 수 있다.
 */

int main ()
{
  ios_base::sync_with_stdio(false);
  cin.tie(nullptr);

  int n;
  cin >> n;

  if (n == 1)
  {
    cout << "1" << '\n';
  } else if (n == 2 || n == 3)
  {
    cout << "NO SOLUTION" << '\n';
  } else if (n == 4)
  {
    cout << "3 1 4 2" << '\n';
  } else
  {
    for (int i = 1; i <= n; i += 2)
    {
      cout << i << ' ';
    }
    for (int i = 2; i <= n; i += 2)
    {
      cout << i << ' ';
    }
    cout << '\n';
  }

  return 0;
}