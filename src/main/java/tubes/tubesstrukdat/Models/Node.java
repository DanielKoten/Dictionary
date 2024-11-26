package tubes.tubesstrukdat.Models;

public class Node<K extends Comparable<K>, V> {
    K key;
    V value;
    Node<K, V> left, right, parent;
    boolean isRed;

    public Node(K key, V value) {
        this.key = key;
        this.value = value;
        this.left = null;
        this.right = null;
        this.parent = null;
        this.isRed = true; // Default to red on insertion
    }
}
