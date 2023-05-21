import java.util.Iterator;
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

        Iterator<BST<Integer, String>.Pairs> iterator = BST.iterator();
        while (iterator.hasNext()) {
            BST.Pairs pair = iterator.next();
            System.out.println(pair.key + ": " + pair.val);}

            int keyToDelete = 9;
            BST.delete(keyToDelete);
            System.out.println("After deleting key " + keyToDelete + ":");

            System.out.println("Inorder traversal using iterator:");
            iterator = BST.iterator();
            while (iterator.hasNext()) {
                BST.Pairs pair = iterator.next();
                System.out.println(pair.key + ": " + pair.val);
            }
    }
}
