
import java.util.*;
import java.lang.*;
import java.io.*;

// The main method must be in a class named "Main".
class Main {

    static class Node {
        Node parent;
        int val;
        Node left;
        Node right;

        public Node(int val) {
            this.val = val;
        }
    }

    static StringBuilder sb;
    
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        sb = new StringBuilder();
        
        List<Integer> input = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            input.add(Integer.parseInt(line));
        }

        Node root = buildTree(input);
        postorder(root);
        System.out.println(sb);
    }

    static Node buildTree(List<Integer> lst) {
        if (lst.size() == 0) {
            return null;
        }
        
        int mid = lst.get(0);
        int range = -1;
        for (int i = 0; i < lst.size(); i++) {
            if (lst.get(i) > mid) {
                range = i;
                break;
            }
        }

        List<Integer> left;
        List<Integer> right;
        
        if (range == -1) {
            left = lst.subList(1, lst.size());
            right = Collections.emptyList();
        } else {
            left = lst.subList(1, range);
            right = lst.subList(range, lst.size());
        }

        Node root = new Node(mid);
        root.left = buildTree(left);
        root.right = buildTree(right);
        
        return root;
    }

    static void postorder(Node node) {
        if (node.left != null)
            postorder(node.left);
        if (node.right != null)
            postorder(node.right);
        sb.append(node.val).append('\n');    
    }
    
}