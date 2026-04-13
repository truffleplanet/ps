#include <iostream>
#include <string>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    std::string s;
    std::string t;

    std::getline(std::cin, s);
    std::getline(std::cin, t);

    if (s + t == t + s) {
        std::cout << 1;
    } else {
        std::cout << 0;
    }

    return 0;
}
