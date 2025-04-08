T = int(input())


for t in range(1, T+1):
    str1 = input()
    str2 = input()

    d = {k:0 for k in str1}
    for char in str2:
        if char in d:
            d[char] += 1
    
    vals = list(d.values())
    print(f"#{t} {max(vals)}")
    