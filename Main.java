public class Main {
    public static void main(String[] args) {
        BST<Integer, String> BST = new BST<>();

        BST.put(4, "horse");
        BST.put(7, "goat");
        BST.put(1, "sheep");
        BST.put(9, "cow");
        BST.put(2, "donkey");

        String value = BST.get(4);
        System.out.println(value);

        System.out.println("Before:");
        BST.preorder();
        BST.delete(1);
        System.out.println("After:");
        BST.preorder();
    }
}
