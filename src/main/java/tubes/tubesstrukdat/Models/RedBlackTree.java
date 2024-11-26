package tubes.tubesstrukdat.Models;

public class RedBlackTree {
    private Node root;

    // Rotasi Kiri
    private void rotateLeft(Node x) {
        Node y = x.getRight();
        x.setRight(y.getLeft());
        if (y.getLeft() != null) y.getLeft().setParent(x);
        y.setParent(x.getParent());
        if (x.getParent() == null) {
            root = y;
        } else if (x == x.getParent().getLeft()) {
            x.getParent().setLeft(y);
        } else {
            x.getParent().setRight(y);
        }
        y.setLeft(x);
        x.setParent(y);
    }

    // Rotasi Kanan
    private void rotateRight(Node y) {
        Node x = y.getLeft();
        y.setLeft(x.getRight());
        if (x.getRight() != null) x.getRight().setParent(y);
        x.setParent(y.getParent());
        if (y.getParent() == null) {
            root = x;
        } else if (y == y.getParent().getRight()) {
            y.getParent().setRight(x);
        } else {
            y.getParent().setLeft(x);
        }
        x.setRight(y);
        y.setParent(x);
    }

    // Fixing RBT setelah insert
    private void fixInsert(Node z) {
        while (z.getParent() != null && z.getParent().isRed()) {
            if (z.getParent() == z.getParent().getParent().getLeft()) {
                Node y = z.getParent().getParent().getRight();
                if (y != null && y.isRed()) {
                    z.getParent().setRed(false);
                    y.setRed(false);
                    z.getParent().getParent().setRed(true);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getRight()) {
                        z = z.getParent();
                        rotateLeft(z);
                    }
                    z.getParent().setRed(false);
                    z.getParent().getParent().setRed(true);
                    rotateRight(z.getParent().getParent());
                }
            } else {
                Node y = z.getParent().getParent().getLeft();
                if (y != null && y.isRed()) {
                    z.getParent().setRed(false);
                    y.setRed(false);
                    z.getParent().getParent().setRed(true);
                    z = z.getParent().getParent();
                } else {
                    if (z == z.getParent().getLeft()) {
                        z = z.getParent();
                        rotateRight(z);
                    }
                    z.getParent().setRed(false);
                    z.getParent().getParent().setRed(true);
                    rotateLeft(z.getParent().getParent());
                }
            }
        }
        root.setRed(false);
    }

    // Insert node ke RBT
    public void insert(String key, String value, String description, String translationDescription) {
        Node node = new Node(key, value, description, translationDescription);
        if (root == null) {
            root = node;
            root.setRed(false); // Root selalu hitam
            return;
        }
        Node temp = root;
        Node parent = null;
        while (temp != null) {
            parent = temp;
            if (key.compareTo(temp.getKey()) < 0) {
                temp = temp.getLeft();
            } else if (key.compareTo(temp.getKey()) > 0) {
                temp = temp.getRight();
            } else {
                // Jika key sudah ada, update value dan deskripsi
                temp.setValue(value);
                temp.setDescription(description);
                temp.setTranslationDescription(translationDescription);
                return;
            }
        }
        node.setParent(parent);
        if (key.compareTo(parent.getKey()) < 0) {
            parent.setLeft(node);
        } else {
            parent.setRight(node);
        }
        fixInsert(node);
    }

    // Pencarian node berdasarkan key
    public Node search(String key) {
        Node temp = root;
        while (temp != null) {
            if (key.compareTo(temp.getKey()) < 0) {
                temp = temp.getLeft();
            } else if (key.compareTo(temp.getKey()) > 0) {
                temp = temp.getRight();
            } else {
                return temp;
            }
        }
        return null; // Tidak ditemukan
    }

    public Node searchByValue(String value) {
        return searchByValueHelper(root, value);
    }

    private Node searchByValueHelper(Node node, String value) {
        if (node == null) {
            return null;
        }
        if (node.getValue().equalsIgnoreCase(value)) {
            return node;
        }
        Node leftSearch = searchByValueHelper(node.getLeft(), value);
        if (leftSearch != null) {
            return leftSearch;
        }
        return searchByValueHelper(node.getRight(), value);
    }

}
