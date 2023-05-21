import org.w3c.dom.Node;

public class BST <K extends Comparable <K>, V > {
    private Node root;

    private class Node {
        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V val) {
            this.key = key;
            this.value = val;
        }
    }

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node current, K key, V val) {
        if (current == null) {
            return new Node(key, val);
        }
        int comparison = key.compareTo(current.key);
        if (comparison < 0) {
            current.left = put(current.left, key, val);
        } else if (comparison > 0) {
            current.right = put(current.right, key, val);
        } else {
            current.value = val;
        }
        return current;
    }

    public V get(K key) {
        return search(root, key);
    }

    private V search(Node current, K key) {
        if (current == null) {
            return null;
        }
        int comparison = key.compareTo(current.key);
        if (comparison < 0) {
            return search(current.left, key);
        } else if (comparison > 0) {
            return search(current.right, key);
        } else {
            return current.value;
        }
    }
    public void delete(K key) {
        root = delete(root, key);
    }
    private Node delete(Node root, K key) {
        if (root == null) {
            return null;
        }
        else if (key.compareTo(root.key) < 0) {
            root.left = delete(root.left, key);
        }
        else if (key.compareTo(root.key) > 0) {
            root.right = delete(root.right, key);
        }
        else {
            if (root.left != null && root.right != null) {
                K lmax = findMaxData(root.left);
                root.key = lmax;
                root.left = delete(root.left, lmax);
                return root;
            }
            else if (root.left != null) {
                return root.left;
            }
            else if (root.right != null) {
                return root.right;
            }
            else {
                return null;
            }
        }
        return root;
    }
    public K findMaxData(Node root) {
        if (root.right != null) {
            return findMaxData(root.right);
        } else {
            return root.key;
        }
    }
    public void preorder(){
        preorder(root);
        System.out.println();
    }
    public void preorder(Node node){
        if(node!=null){
            System.out.print(node.value+" ");
            preorder(node.left);
            preorder(node.right);
        }
    }
}
