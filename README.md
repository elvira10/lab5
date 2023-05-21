# Binary Search Tree (BST)
This is a Java implementation of a Binary Search Tree (BST) data structure. The BST allows for efficient storage and retrieval of key-value pairs, where the keys are required to be comparable. The implementation provides methods for insertion, deletion, search, and traversal of the tree.

### Class Structure
The BST class is defined with two type parameters, K and V, representing the types of keys and values stored in the tree, respectively. It contains a nested Node class, representing a node in the BST. Each Node instance stores a key, value, references to its left and right child nodes, and the size of the subtree rooted at the current node.

### Public Methods
* void put(K key, V value): Inserts a key-value pair into the BST.
```
    public void put(K key, V val) {
        root = put(root, key, val);
    }
```

* V get(K key): Retrieves the value associated with the given key from the BST.
```
public V get(K key) {
        return search(root, key);
    }
```
* void delete(K key): Deletes the key and its associated value from the BST.
```
    public void delete(K key) {
        root = delete(root, key);
    }
```
* void preorder(): Prints the BST in preorder traversal.
```
    public void preorder() {
        preorder(root);
        System.out.println();
    }
```
* Iterator<Pairs> iterator(): Returns an iterator for the BST, providing key-value pairs in in-order traversal.
```
    public Iterator<Pairs> iterator() {
        return new KeyIterator();
    }
```
* int size(): Returns the size of the BST.
```
    public int size(){
        return getSize(root);
    }
```

### Private Methods
* Node put(Node current, K key, V value): Inserts a key-value pair into the BST rooted at the given node (helper method).
  ```
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
  ```
* V search(Node current, K key): Searches for the key in the BST rooted at the given node (helper method).
```
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
```
* Node delete(Node root, K key): Deletes the key from the BST rooted at the given node (helper method).
```
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
```
* K findMaxData(Node root): Finds the maximum key in the BST rooted at the given node.
```
    public K findMaxData(Node root) { //If the right child of the current node is not null recursively call findMaxData on the right child
        if (root.right != null) {
            return findMaxData(root.right);
        } else { //If the right child is null, the rightmost node in the tree has been reached, which has the maximum key
            return root.key;
        }
    }
```
* void preorder(Node node): Performs a preorder traversal of the BST rooted at the given node (helper method).
```
    public void preorder(Node node) {
        if (node != null) {
            System.out.print(node.value + " "); //Visit the current node and print its value
            preorder(node.left); //Recursively traverse the left subtree in preorder
            preorder(node.right); //Recursively traverse the right subtree in preorder
        }
    }
```
* int getSize(Node node): Calculates the size of the BST rooted at the given node (helper method).
```
    private int getSize(Node node) {
        if (node == null) {
            return 0; //Base case: an empty subtree has size 0
        } else {
            return node.size; //Returns the size of the subtree rooted at the current node
        }
    }
```
* void inOrder(Node node): Traverses the BST in in-order and pushes nodes onto the stack (helper method).
```
    private class KeyIterator implements Iterator<Pairs> {
        private Stack<Node> stack;
        public KeyIterator() {
            this.stack = new Stack<>();
            inOrder(root); //Start the iterator by traversing the tree in-order
        }
```

### Iterator
The BST class includes a nested KeyIterator class that implements the Iterator<Pairs> interface. This iterator provides key-value pairs from the BST in in-order traversal. It uses a stack to keep track of nodes during the traversal.

### Pairs Class
The Pairs class is a simple class that represents a key-value pair. It has two public fields: key and val, which store the key and value, respectively.

### Usage
1. Create an instance of the BST class, specifying the key and value types.
2. Use the put() method to insert key-value pairs into the BST.
3. Use the get() method to retrieve the value associated with a given key.
4. Use the delete() method to remove a key-value pair from the BST.
5. Use the preorder() method to print the elements of the BST in preorder traversal.
6. Use the iterator() method to obtain an iterator for iterating over the key-value pairs.
7. Use the size() method to get the number of nodes in the BST.

### Notes
* The keys used in the BST must be comparable.
* The BST does not allow duplicate keys. If a duplicate key is inserted, the corresponding value is updated.
* The BST does not support null keys.
* The BST supports null values.
* The iterator provides key-value pairs in ascending order based on the keys.