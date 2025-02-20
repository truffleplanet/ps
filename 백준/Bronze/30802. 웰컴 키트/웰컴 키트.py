N = int(input())
shirts_requesets = map(int, input().split())
T, P = map(int, input().split())


total_bundles = 0
for size in shirts_requesets:
    bundles, remainder = divmod(size, T)
    if remainder == 0:
        total_bundles += bundles
    else:
        total_bundles += bundles + 1

print(total_bundles)
pen_bundles, single_pens = divmod(N, P)
print(pen_bundles, single_pens)
