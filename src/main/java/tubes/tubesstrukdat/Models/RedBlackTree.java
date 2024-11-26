package tubes.tubesstrukdat.Models;

public class RedBlackTree<K extends Comparable<K>, V> {
    private Node<K, V> root;

    public RedBlackTree() {
        root = null;
    }

    public void insert(K key, V value) {
        Node<K, V> newNode = new Node<>(key, value);
        root = insertNode(root, newNode);
        fixViolation(newNode);
    }

    public V search(K key) {
        Node<K, V> node = searchNode(root, key);
        return node == null ? null : node.value;
    }

    private Node<K, V> searchNode(Node<K, V> current, K key) {
        if (current == null || key.compareTo(current.key) == 0) {
            return current;
        }
        if (key.compareTo(current.key) < 0) {
            return searchNode(current.left, key);
        } else {
            return searchNode(current.right, key);
        }
    }

    private Node<K, V> insertNode(Node<K, V> root, Node<K, V> newNode) {
        if (root == null) {
            return newNode;
        }
        if (newNode.key.compareTo(root.key) < 0) {
            root.left = insertNode(root.left, newNode);
            root.left.parent = root;
        } else if (newNode.key.compareTo(root.key) > 0) {
            root.right = insertNode(root.right, newNode);
            root.right.parent = root;
        }
        return root;
    }

    private void rotateLeft(Node<K, V> node) {
        Node<K, V> rightChild = node.right;
        node.right = rightChild.left;
        if (rightChild.left != null) {
            rightChild.left.parent = node;
        }
        rightChild.parent = node.parent;
        if (node.parent == null) {
            root = rightChild;
        } else if (node == node.parent.left) {
            node.parent.left = rightChild;
        } else {
            node.parent.right = rightChild;
        }
        rightChild.left = node;
        node.parent = rightChild;
    }

    private void rotateRight(Node<K, V> node) {
        Node<K, V> leftChild = node.left;
        node.left = leftChild.right;
        if (leftChild.right != null) {
            leftChild.right.parent = node;
        }
        leftChild.parent = node.parent;
        if (node.parent == null) {
            root = leftChild;
        } else if (node == node.parent.left) {
            node.parent.left = leftChild;
        } else {
            node.parent.right = leftChild;
        }
        leftChild.right = node;
        node.parent = leftChild;
    }

    private void fixViolation(Node<K, V> node) {
        Node<K, V> parent, grandparent;
        while (node != root && node.isRed && node.parent.isRed) {
            parent = node.parent;
            grandparent = parent.parent;
            if (parent == grandparent.left) {
                Node<K, V> uncle = grandparent.right;
                if (uncle != null && uncle.isRed) {
                    grandparent.isRed = true;
                    parent.isRed = false;
                    uncle.isRed = false;
                    node = grandparent;
                } else {
                    if (node == parent.right) {
                        rotateLeft(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateRight(grandparent);
                    boolean tempColor = parent.isRed;
                    parent.isRed = grandparent.isRed;
                    grandparent.isRed = tempColor;
                    node = parent;
                }
            } else {
                Node<K, V> uncle = grandparent.left;
                if (uncle != null && uncle.isRed) {
                    grandparent.isRed = true;
                    parent.isRed = false;
                    uncle.isRed = false;
                    node = grandparent;
                } else {
                    if (node == parent.left) {
                        rotateRight(parent);
                        node = parent;
                        parent = node.parent;
                    }
                    rotateLeft(grandparent);
                    boolean tempColor = parent.isRed;
                    parent.isRed = grandparent.isRed;
                    grandparent.isRed = tempColor;
                    node = parent;
                }
            }
        }
        root.isRed = false;
    }
}
