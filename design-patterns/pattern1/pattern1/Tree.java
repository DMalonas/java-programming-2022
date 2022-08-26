
public class Tree {
    private String name;
    
    private Tree tree1;
    private Tree tree2;
    
    private Person person1;
    private Person person2;

    public Tree(Person p1, int num) {
        tree1 = null;
        tree2 = null;
        person1 = p1;
        person2 = null;
        name = "tree" + num;
    }

    public Tree(Tree t1, int num) {
        tree1 = t1;
        tree2 = null;
        person1 = null;
        person2 = null;
        name = "tree" + num;
    }

    public Tree(Tree t1, Tree t2, int num){
        tree1 = t1;
        tree2 = t2;
        person1 = null;
        person2 = null;
        name = "tree" + num;
    }
    
    public Tree(Tree t1, Person p2, int num){
        tree1 = t1;
        tree2 = null;
        person1 = null;
        person2 = p2;
        name = "tree" + num;
    }
    
    public Tree(Person p1, Person p2, int num){
        tree1 = null;
        tree2 = null;
        person1 = p1;
        person2 = p2;
        name = "tree" + num;
    }
    
    public void print() {
        System.out.println(name);
        if (tree1 != null) {
            tree1.print();
        }
        if (tree2 != null) {
            tree2.print();
        }
        if (person1 != null) {
            person1.print();
        }
        if (person2 != null) {
            person2.print();
        }
    }
}
