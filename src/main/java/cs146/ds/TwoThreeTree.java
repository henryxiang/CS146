package cs146.ds;

import java.util.LinkedList;
import java.util.List;

public class TwoThreeTree {
    public static class Node {
        private List<Integer> keys = new LinkedList<Integer>();
        private List<Node> children = new LinkedList<Node>();
        private Node parent;

        public Node() {}

        public Node(int key) {
            this.keys.add(key);
        }

        public Node(int key, Node child1, Node child2) {
            this.keys.add(key);
            this.children.add(child1);
            this.children.add(child2);
        }

        public List<Integer> getKeys() {
            return keys;
        }

        public List<Node> getChildren() {
            return children;
        }

        public boolean isLeaf() {
            return this.children.size() == 0;
        }

        public boolean isTwoNode() {
            return this.keys.size() == 1;
        }

        public boolean isThreeNode() {
            return this.keys.size() == 2;
        }

        public boolean isFourNode() {
            return this.keys.size() == 3;
        }

        public int compareKey(int key) {
            int rank = 0;
            while (key > keys.get(rank).intValue()) rank += 1;
            return rank;
        }

        public int addKey(int key) {
            int rank = compareKey(key);
            this.keys.add(rank, key);
            return rank;
        }

        public void addChild(int index, Node child) {
            this.children.add(index, child);
        }

        public Node[] split() {
            Node[] result = new Node[2];
            result[0] = new Node(keys.get(0), children.get(0), children.get(1));
            result[1] = new Node(keys.get(2), children.get(2), children.get(3));
            return result;
        }
    }

    private Node root;

    public Node getRoot() {
        return root;
    }

    public void insert(int key) {
        if (root == null) {
            root = new Node(key);
            return;
        }
        Node leaf = findLeafNode(root, key);
        leaf.addKey(key);
        checkAndSplitNode(leaf);
    }

    private Node findLeafNode(Node root, int key) {
        int rank = root.compareKey(key);
        if (root.children.get(rank) == null) return root;
        return findLeafNode(root.children.get(rank), key);
    }

    private void checkAndSplitNode(Node node) {
        if (!node.isFourNode()) return;
        int key = node.keys.get(1); // get middle key
        Node[] nodes = node.split();
        if (node.parent == null) {
            this.root = new Node(key);
            this.root.children.add(nodes[0]);
            this.root.children.add(nodes[1]);
        }
        int rank = node.parent.addKey(key);
        node.parent.addChild(rank, nodes[1]);
        node.parent.addChild(rank, nodes[0]);
        checkAndSplitNode(node.parent);
    }
}
