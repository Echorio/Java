package Tree;

import java.util.ArrayList;


public class BSTree {
    Node root;

    public BSTree() {root = null;}
    
    public boolean isEmpty() {return root == null;}
    
    public void clear() {
        root = null;
    }

    public void visit(Node p){
        if(p == null) 
            return;
        System.out.print(p.info + "  ");
    }
    //return a Node which has key = given key in the tree p
    public Node search(Node p, int key) {
        if(p == null) 
            return null;
        if(p.info == key) 
            return p;
        else if(p.info > key) 
            return search(p.left, key);
        else return search(p.right, key);
    }
    //insert a key x to the tree
    public void insert(int x) {
        if (isEmpty()) {
            root = new Node(x);
            return;
        }
        Node p = new Node(x);
        Node f = null, q = root;
        while(q != null) {
            if(q.info == x) {
                System.out.println(" The key " + x + " already exists, no insertion");
                return;
            }
            if(q.info < x) {
                f = q;
                q = q.right;
            }
            else {
                f = q;
                q = q.left;
            }
        }
        if(f == null) 
            root = p;
        else if(p.info > f.info) 
            f.right = p;
        else f.left = p;
        System.out.println(x+" added successfully!");
    }
    
    public void breadth(){ 
        if(root==null) 
            return;
        MyQueue q = new MyQueue();
        q.enqueue(root);
        Node p;
        while(!q.isEmpty()){ 
            p = (Node) q.dequeue();
            if(p.left !=null)
                q.enqueue(p.left);
            if(p.right !=null)
                q.enqueue(p.right);
            visit(p);
        }
    }
    //preorder a tree
    public void preOrder(Node p) {
        if(p == null) 
            return;
        visit(p);
        preOrder(p.left);
        preOrder(p.right);
    }
    //postorder a tree
    public void postOrder(Node p) {
        if(p == null) 
            return;
        postOrder(p.left);
        postOrder(p.right);
        visit(p);
    }
    //inorder a tree
    public void inOrder(Node p) {
        if(p == null) 
            return;
        inOrder(p.left);
        visit(p);
        inOrder(p.right);
    }
    //bft
    public void BFT(Node p){
        if(p == null) 
            return;
        MyQueue m = new MyQueue();
        m.enqueue(p);
        while(!m.isEmpty()) {
            Node q = (Node)m.dequeue();
            visit(q);
        if(q.left != null) 
            m.enqueue(q.left);
        if(q.right != null) 
            m.enqueue(q.right);
        }
    }
    //height of tree
    int height(Node p){
        if(p==null) 
            return 0;
        else{
            int lDepth=height(p.left);
            int rDepth=height(p.right);
        if (lDepth > rDepth) 
            return (lDepth + 1);
        else return (rDepth + 1);
        }
    }
    /*Part 2___________________________________________________________________*/
    //delete a node on BST
    public void deleteByCopy(int x){
        Node p = search(root, x);
        if(p==null) {
            System.out.println("Key "+x+" does not exists, deletion failed"); 
            return;
        }
        Node f = null, q = root;
        while(q!=p) {
            f=q; 
        if(q.info>p.info) 
            q=q.left;
        else q=q.right;
        }
        //1.p has no child
        if(p.left==null&&p.right==null) {
            if(f==null) 
                root=null;
        else if(f.left==p) 
            f.left=null;
        else f.right = null;
        }
      //2.p has left child only
        else if(p.left!=null&&p.right==null) {
            if(f==null) 
                root=p.left;
            else if(f.left==p) 
                f.left=p.left;
            else f.right=p.left;
        }
      //3.p has right child only
        else if(p.left==null&&p.right!=null) {
            if(f==null) 
                root=p.right;
            else if(f.left==p) 
                f.left=p.right;
            else f.right=p.right;
        }
      //4.p has both child
        else if(p.left!=null&&p.right!=null) {
       //tim q la node lon nhat ben con trai cua p -> q la con phai nhat
       //cua con trai cua p
            q = p.left;f = null;
        while(q.right != null)
            f=q;q=q.right;
        p.info = q.info;
       //delete q
        if(f==null) 
            p.left=q.left;
        else f.right=q.left;
        }
    }
    public void deleteByMerging(int x) {
        Node p = search(root, x);
        if(p==null) {
            System.out.println("Key "+x+" does not exists, deletion failed");
            return;
        }
     //find f is father of p
        Node f = null, q = root;
        while(q!=p) {
            f=q;
            if(q.info>p.info) 
                q=q.left;
            else q=q.right;
        }
     //1.p has no child
        if(p.left==null&&p.right==null) {
            if(f==null) 
                root=null;
        else if(f.left==p) 
            f.left=null;
        else f.right=null;
        }
     //2.p has left child only
        else if(p.left!=null && p.right==null) {
            if(f==null) 
                root=p.left;
        else if(f.left==p) 
            f.left=p.left;
        else f.right=p.left;
        }
     //3.p has right child only
        else if(p.left==null && p.right!=null) {
            if(f==null) 
                root=p.right;
        else if(f.left==p) 
            f.left=p.right;
        else f.right=p.right;
        }
     //4.p has both child
        else if(p.left!=null && p.right!=null) {
      //tim q la node lon nhat ben con trai cua p -> q la con phai nhat
      //cua con trai cua p
            q=p.left;
            Node t=null;
        while(q.right!=null) 
            t=q; q=q.right;
        Node rp=p.right;
        q.right=rp;
        if(f==null) 
            root=p.left;
        else if(f.left==p) 
            f.left=p.left;
        else f.right=p.left;
        }
    }
    //balancing a BST
    //inorder a BST and save all items to an array
    public void buildArray(ArrayList a, Node p) {
        if(p == null) 
            return;
        buildArray(a, p.left);
        a.add(p);
        buildArray(a, p.right);
    }
    //insert all items from an array to a new BST
    public void balance(ArrayList a, int first, int last) {
        if(first <= last) {
            int middle = (first + last) / 2;
            Node p = (Node)a.get(middle);
            insert(p.info);
//          insert(a[m]); if a is a list
            balance(a, first, middle - 1);
            balance(a, middle + 1, last);
        }
    }
    
    public void balance(Node p) {
        ArrayList a = new ArrayList();
        buildArray(a, p);
        int last = a.size(), first = 0;
     //create a new tree and insert all items from a to the BST
        BSTree t = new BSTree();
        t.balance(a, first, last - 1);
        root = t.root;
    }
    /*Part 3___________________________________________________________________*/
    public Node rotateLeft(Node p) {
        if(p.right == null) 
            return p;
        Node p1 = p.right;
        p.right = p1.left;
        p1.left = p;
        return p1;
    }
    
    public Node rotateRight(Node p) {
        if(p.left == null) 
            return p;
        Node p1 = p.left;
        p.left = p1.right;
        p1.right = p;
        return p1;
    }
    
    public Node doubleRotateLeft (Node p){
        p.left = rotateRight(p.left);
        return rotateLeft(p);
    }
    
    public Node doubleRotateRight (Node p){
        p.right = rotateLeft(p.right);
        return rotateRight(p);
    }
}