package SinglyLinkedList;
public class MyList {
    Node head,tail;
    
    MyList() {
        head=tail=null;
    }
    boolean isEmpty() {
        return head==null;
    }
    public int size() {
        Node p = head; int c = 0;
        while(p != null) {
            c++; 
            p = p.next;
        }
        return c;
    }
    void clear() {
        head=tail=null;
    }
    void add(int x) { 
        if(isEmpty())
            head=tail=new Node(x,null);
        else {
            Node q =new Node(x,null);
            tail.next=q; 
            tail=q;
        }
    }
    void addFirst (int x){
        Node node = new Node(x,head);
        if (isEmpty())
            head=tail=node;
        else head = node;
    }
    void addIndex (int x, int index){
        if (index < 0) return;
        if (index == 0) addFirst(x);
        else{
            Node current = head;
            int pos = 0;
            while (current != null){
                if (index-1 == pos) break;
                current = current.next;
                pos++;
            }
            if (current == null) return;
            else {
                Node node = new Node (x,current.next);
                current.next = node;
            }
        }
        
    }
    void addPosition(int pos, Node x){
        Node p = head;
        while (pos-- > 1)
            p=p.next;
        Node node = new Node (x.info,p.next);
        p.next = node;
    }
    void traverse() {
        Node p=head;
        while(p!=null) {
            System.out.print("  " + p.info);
            p=p.next;
        }
        System.out.println();
    }
    Node get(int pos) {
        Node p = head;
        int c = 0;
        while(p != null && c < pos) {
            c++;
            p = p.next;
        }
        return p;
    }
    Node search(int x) {
        Node p = head;
        while(p != null && p.info != x) 
            p = p.next;
        return p;
    }
    Node findIndex (int index) {
        if(index<=0) return null;
        Node current = head;
        int pos = 0;
        while (current!=null){
            if(index-1 == pos) break;
            current = current.next;
            pos++;
        }
        if(current==null) return null;
        return current;
    }
    void sort() {
        int n = size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                Node pi = get(i), pj = get(j);
                if(pi.info > pj.info) {
                    int t = pi.info;
                    pi.info = pj.info;
                    pj.info = t;
                }
            }
        }
    }
    void sort1(){
        if(isEmpty()) return;
        Node pi = head, pj;
        while (pi!=null){
            pj=pi.next;
            while (pj!=null){
                if(pi.info>pj.info){
                    int temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj=pj.next;
            }
            pi=pi.next;
        }
    }
    void sortFirstKElements(int k){
        for(int i=0;i<k;i++){
            int count=0;
            for(Node pj = head; count<k-1;pj = pj.next){
                count++;
                if(pj.info>pj.next.info){
                    int temp = pj.info;
                    pj.info = pj.next.info;
                    pj.next.info = temp;
                }
            }
        }
    }
    void sortLastKElements(int k){
        int count = size()-k;
        Node pi=head, pj;
        while (count-- >0 ) pi = pi.next;
        while (pi!=null){
            pj=pi.next;
            while (pj!=null){
                if(pi.info>pj.info){
                    int temp = pi.info;
                    pi.info = pj.info;
                    pj.info = temp;
                }
                pj=pj.next;
            }
            pi=pi.next;
        }
    }
     void sortByName() {
        Node pi, pj;
        Person x;
        for (pi = head; pi != null; pi = pi.next) {
            for (pj = pi.next; pj != null; pj = pj.next) {
                if (pj.info.name.compareTo(pi.info.name) < 0) {
                    x = pi.info;
                    pi.info = pj.info;
                    pj.info = x;
                }
            }
        }
    }
    void reverse() {
        int n = size();
        for(int i = 0, j = n - 1; i < j; i ++, j --) {
            Node pi = get(i), pj = get(j);
            int t = pi.info;
            pi.info = pj.info;
            pj.info = t;
        }
    }
    void deleteFirst() {
        if (head==tail)
            head=tail=null;
        else head=head.next;
    }
    void deleteLast() {
        if (head==tail)
            head=tail=null;
        else {
            Node p = head;
            while (p.next != tail){
                p=p.next;
            }
            p.next = null;
            tail = p;
        }
    }
    void delIndex (int index) {
        if(index<0) return;
        if(index==0) {
            deleteFirst();
            return;
        }
        Node current = findIndex(index);
        if(current==null) return;
        if(current==tail) {
            deleteLast();
            return;
        }
        int value = current.next.info;
        current.next = current.next.next;
        return;
    }
    void deleteWithInfo(int x){
        if(isEmpty()) return;
        Node current = head, prev = null;
        while(current!=null){
            if (current.info==x) {
                if(current==head){
                    head=head.next;
                    current=head;
                }
                else {
                    prev.next=current.next;
                    current=prev.next;
                }
            }
            else {
                prev = current;
                current = current.next;
            }
        }
    }
    public static void main(String[] args) {
        MyList m = new MyList();
        Node n = new Node (3,null);
        m.add(3);
        m.add(3);
        m.add(6);
        m.add(3);
        m.add(3);
        m.add(4);
        m.add(3);
        m.add(8);
        m.addFirst(8);
        m.traverse();
        m.deleteFirst();
        m.traverse();
        m.deleteLast();
        m.traverse();
        m.deleteWithInfo(3);
        m.traverse();
        m.sort();
        m.traverse();
    }
}

