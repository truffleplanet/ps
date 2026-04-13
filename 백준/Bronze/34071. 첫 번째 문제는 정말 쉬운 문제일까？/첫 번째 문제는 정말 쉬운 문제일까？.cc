#include <iostream>

int main() {
    std::ios_base::sync_with_stdio(false);
    std::cin.tie(nullptr);

    int n{};
    std::cin >> n;

    int val{};
    std::cin >> val;

    bool isSmaller{false};
    bool isLarger{false};

    for (int i{1}; i < n; i++) {
        int curr{};
        std::cin >> curr;

        if (val < curr)
            isSmaller = true;
        else if (val > curr)
            isLarger = true;

        if (isSmaller && isLarger) {
            std::cout << "?\n";
            return 0;
        }
    }

    if (isLarger)
        std::cout << "hard\n";
    if (isSmaller)
        std::cout << "ez\n";

    return 0;
}
