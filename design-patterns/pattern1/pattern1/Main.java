
public class Main {
    public static void main(String[] args) {
        /* Let's create a family tree (for instance like one used on genealogy sites).
           For the sake of simplicity, assume an individual can have at most two children.
           If an individual has 1-2 children, they are considered a "tree". If an individual
           does not have children, they are considered a "person".
           With that in mind, let's populate a family tree with some data. */

        Person p1 = new Person(1);
        Person p2 = new Person(2);
        Person p3 = new Person(3);
        Person p4 = new Person(4);

        Tree t1 = new Tree(p1, 1);
        Tree t2 = new Tree(p2, p3, 2);
        Tree t3 = new Tree(t1, p4, 3);
        Tree t4 = new Tree(t3, t2, 4);
        
        t4.print();
    }
}
