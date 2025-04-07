N = int(input())

tree = {}
for _ in range(N):
    root, left, right = input().split()
    tree[root] = (left, right)


def preorder(tree, node):
    if node == ".":
        return
    else:
        print(node, end='')
        preorder(tree, tree[node][0])
        preorder(tree, tree[node][1])

def inorder(tree, node):
    if node == ".":
        return
    else:
        inorder(tree, tree[node][0])
        print(node, end='')
        inorder(tree, tree[node][1])

def postorder(tree, node):
    if node == ".":
        return
    else:
        postorder(tree, tree[node][0])
        postorder(tree, tree[node][1])
        print(node, end='')

preorder(tree, "A")
print()
inorder(tree, "A")
print()
postorder(tree, "A")