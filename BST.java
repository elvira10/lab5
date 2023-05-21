import org.w3c.dom.Node;
import java.util.Iterator;
import java.util.Stack;

public class BST <K extends Comparable <K>, V > {
    private Node root;
    private class Node {
        private K key; //Key associated with the node
        private V value; //Value associated with the node
        private Node left, right; //Left and right child nodes
        private int size; //Size of the subtree rooted at the current node
        /**
         * constructor
         * @param key the key of the node
         * @param val the value associated with the key
         */
        public Node(K key, V val) { //Node constructor

            this.key = key;
            this.value = val;
            this.size = 1;
        }
    }

    /**
     * inserts a key-value pair into the bst
     * @param key the key to insert
     * @param val the value associated with the key
     */
    public void put(K key, V val) {
        root = put(root, key, val);
    }

    /**
     * inserts a key-value pair into the bst rooted at the given node
     * @param current the current node in the recursive call
     * @param key the key to insert
     * @param val the value associated with the key
     * @return the updated node
     */
    private Node put(Node current, K key, V val) {
        if (current == null) { //If the current node is null, create a new node with the key-value pair
            return new Node(key, val);
        }
        int comparison = key.compareTo(current.key); //Compare the key with the key of the current node
        if (comparison < 0) { //If the key is less than the current node's key, recursively put the key-value pair in the left subtree
            current.left = put(current.left, key, val);
        } else if (comparison > 0) { //If the key is greater than the current node's key, recursively put the key-value pair in the right subtree
            current.right = put(current.right, key, val);
        } else { //If the key is equal to the current node's key, update the value of the current node
            current.value = val;
        }
        current.size = 1 + getSize(current.left) + getSize(current.right); //Update the size of the current node
        return current; //Return the modified current node
    }

    /**
     * retrieves the value associated with the given key from the bst
     * @param key the key to search for
     * @return the value associated with the key or null if not found
     */
    public V get(K key) {
        return search(root, key);
    }

    /**
     * searches for the key in the BST rooted at the given node
     * @param current the current node in the recursive call
     * @param key the key to search for
     * @return the value associated with the key or null if not found
     */
    private V search(Node current, K key) {
        if (current == null) { //If the current node is null, the key is not found in the tree
            return null;
        }
        int comparison = key.compareTo(current.key); //Compare the key with the current node's key
        if (comparison < 0) { //If the key is smaller than the current node's key, search in the left subtree
            return search(current.left, key);
        } else if (comparison > 0) { //If the key is greater than the current node's key, search in the right subtree
            return search(current.right, key);
        } else { //If the key is equal to the current node's key, return the corresponding value
            return current.value;
        }
    }

    /**
     * deletes the key and its associated value from the bst
     * @param key the key to delete
     */
    public void delete(K key) {
        root = delete(root, key);
    }

    /**
     * deletes the key from the bst rooted at the given node
     * @param root  the root of the current subtree
     * @param key   the key to delete
     * @return the updated node
     */
    private Node delete(Node root, K key) {
        if (root == null) { //Base case: If the root is null, return null
            return null;
        } else if (key.compareTo(root.key) < 0) { //If the key is less than the current node's key, go left
            root.left = delete(root.left, key); //Recursively delete from the left subtree
        } else if (key.compareTo(root.key) > 0) { //If the key is greater than the current node's key, go right
            root.right = delete(root.right, key); //Recursively delete from the right subtree
        } else { //If the key is equal to the current node's key, we found the node to delete
            if (root.left != null && root.right != null) { //Case 1: If the node has both left and right children
                K lmax = findMaxData(root.left); //Find the maximum key in the left subtree
                root.key = lmax; //Replace the current node's key with the predecessor's key
                root.left = delete(root.left, lmax); //Recursively delete the predecessor from the left subtree
                return root;
            } else if (root.left != null) { //Case 2: If the node has only a left child
                return root.left;
            } else if (root.right != null) { //Case 3: If the node has only a right child
                return root.right;
            } else { //Case 4: If the node has no children
                return null;
            }
        }
        root.size = 1 + getSize(root.left) + getSize(root.right); //Update the size of the current node after deletion
        return root;
    }

    /**
     * finds the maximum key in the bst rooted at the given node
     * @param root the root of the current subtree
     * @return the maximum key
     */
    public K findMaxData(Node root) { //If the right child of the current node is not null recursively call findMaxData on the right child
        if (root.right != null) {
            return findMaxData(root.right);
        } else { //If the right child is null, the rightmost node in the tree has been reached, which has the maximum key
            return root.key;
        }
    }

    /**
     * prints the bst in preorder traversal
     */
    public void preorder() {
        preorder(root);
        System.out.println();
    }

    /**
     * preorder traversal of the bst rooted at the given node
     * @param node the current node in the recursive call
     */
    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.value + " "); //Visit the current node and print its value
            preorder(node.left); //Recursively traverse the left subtree in preorder
            preorder(node.right); //Recursively traverse the right subtree in preorder
        }
    }

    /**
     * returns an iterator for the bst
     * @return an iterator that provides key-value pairs in in-order traversal
     */
    public Iterator<Pairs> iterator() {
        return new KeyIterator();
    }
    private class KeyIterator implements Iterator<Pairs> {
        private Stack<Node> stack;
        public KeyIterator() {
            this.stack = new Stack<>();
            inOrder(root); //Start the iterator by traversing the tree in-order
        }

        /**
         * checks if there are more key-value pairs to iterate over
         * @return true if there are more pairs, false otherwise
         */
        @Override
        public boolean hasNext() {
            return !stack.isEmpty(); //Check if there are any remaining nodes in the stack
        }

        /**
         * returns the next key-value pair
         * @return the next key-value pair
         */
        @Override
        public Pairs next() {
            Node node = stack.pop(); //Retrieve the next node from the stack
            return new Pairs(node.key, node.value);
        }

        /**
         * traverses the bst in in-order and pushes nodes onto the stack
         * @param node the current node in the recursive call
         */
        private void inOrder(Node node){
            if (node == null){
                return;
            }
            inOrder(node.right); //Push all left child nodes onto the stack
            stack.push(node); //Push the current node onto the stack
            inOrder(node.left); //Recursively process the right subtree
        }
    }
    public class Pairs {
        public K key;   // The key of the pair
        public V val;   // The value associated with the key

        /**
         * pairs constructor
         * @param key the key of the pair
         * @param value the value associated with the key
         */
        public Pairs(K key, V value) {
            this.key = key;
            this.val = value;
        }
    }

    /**
     * returns the size of the bst
     * @return the number of nodes in the bst
     */
    public int size(){
        return getSize(root);
    }

    /**
     * calculates the size of the bst rooted at the given node
     * @param node the current node in the recursive call
     * @return the size of the subtree
     */
    private int getSize(Node node) {
        if (node == null) {
            return 0; //Base case: an empty subtree has size 0
        } else {
            return node.size; //Returns the size of the subtree rooted at the current node
        }
    }
}