#include <iostream>
#include <string>
#include <vector>

int main()
{
    int n{};
    std::cin >> n;
    std::cin.ignore();

    std::vector<std::string> names(n);
    for (int i{}; i < n; i++)
    {
        std::getline(std::cin, names[i]);
    }

    for (int pass{}; pass < 2; pass++)
    {
      for (int i{}; i < n; i++)
      {
        int ans{};
        std::cout << "? " << names[i] << '\n' << std::flush;
        std::cin >> ans;
        if (ans == 1)
        {
          std::cout << "! " << names[i] << '\n' << std::flush;
          return 0;
        }
      }
    }

    return 1;
}
