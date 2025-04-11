s = input()

lefty = "("
righty = ")"
left = 0
ans = 0
for c in s:
    if c == lefty:
        left += 1
    elif before == lefty and c == righty:
        left -= 1
        ans += left
    elif c == righty:
        left -= 1
        ans += 1
    else:
        pass

    before = c

print(ans)

# (((() -> 3
# (()() -> 4, 4
# )) -> 1, 1
# (()) -> 3, 1  
# ()- > 2
# )) -> 1 1
# (()()) -> 1 1 1 

# 11 12 13 17 19 21 24