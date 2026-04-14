#include <iostream>
#include <string>
#include <vector>

int main()
{
  std::ios_base::sync_with_stdio(false);
  std::cin.tie(nullptr);

  int n{};
    std::cin >> n;
    std::cin.ignore();

    std::vector<std::string> names(n);
    for (auto &name : names)
    {
        std::getline(std::cin, name);
    }

    for (int pass{}; pass < 2; ++pass)
    {
      for (auto &name: names)
      {
        int ans;
        std::cout << "? " << name << '\n' << std::flush;
        std::cin >> ans;
        if (ans == 1)
        {
          std::cout << "! " << name << '\n' << std::flush;
          return 0;
        }
      }
    }

    return 0;
}
