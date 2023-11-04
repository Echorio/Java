package Graph;

public class Graph {
    
    int [][] a;//adjcency matrix
    int n;//total vertex
    char []vertex = "ABCDEFGHIJKLMN".toCharArray();
    
    public Graph() {
     int [][]b  = {
     //A  B  C  D  E  F  G  H  I
      {0, 1, 1, 1, 0, 0, 0, 0, 0}, //A
      {1, 0, 1, 1, 0, 0, 0, 0, 0}, //B 
      {1, 1, 0, 1, 0, 0, 0, 0, 0}, //C
      {1, 1, 1, 0, 0, 1, 0, 0, 0}, //D
      {0, 0, 0, 0, 0, 1, 0, 0, 0}, //E
      {0, 0, 0, 1, 1, 0, 0, 0, 0}, //F
      {0, 0, 0, 0, 0, 0, 0, 1, 1}, //G
      {0, 0, 0, 0, 0, 0, 1, 0, 0}, //H
      {0, 0, 0, 0, 0, 0, 1, 0, 0}  //I
     };
     a = b;
     n = a.length;
    }
    //visit vertex i
    public void visit(int i) {
     System.out.print(vertex[i] + "  ");
    }
    //Breadth first traversal
    public void BFT(int u, boolean []c) {
     MyQueue mq = new MyQueue();
     mq.enqueue(u); c[u] = true;
     while(!mq.isEmpty()) {
      int j = (int)mq.dequeue();
      visit(j);
      for (int i = 0; i < n; i++) {
       if(!c[i] && a[j][i] > 0) {
        mq.enqueue(i); c[i] = true;
       }}}
    }
    //Breath first traversal
    public void BFT(int u) {
     boolean []c = new boolean[n];
     BFT(u,c);
     for (int i = 0; i < n; i++) {
      if(!c[i]) BFT(i,c);
     }
    }
    //Depth first traversal
    public void DFT(int i, boolean []c) {
     c[i] = true; visit(i);
     for (int j = 0; j < n; j++) {
      if(!c[j] && a[i][j] > 0) DFT(j,c);
     }
    }
    public void DFT(int i) {
     boolean []c = new boolean[n];
     DFT(i,c);
     for (int j = 0; j < n; j++) {
      if(!c[j]) DFT(j,c);
     }}
    /*Session 1_______________________________________________________________*/
    //connectivity
    int [] tp; int con;
    public void conDFT(int i, boolean []c) {
     c[i] = true;
     tp[i] = con;
     for (int j = 0; j < n; j++) {
      if(!c[j] && a[i][j] > 0) conDFT(j,c);
     }}
    public void conDFT() {
     con = 0;
     tp = new int [n];
     boolean [] c = new boolean[n];
     for (int i = 0; i < n; i++) {
      if(!c[i]) {
       con ++; conDFT(i, c);
      }}
     //output connectivity
     System.out.println("Connectivity: " + con);
     for (int i = 1; i <= con; i++) {
      System.out.println("Connectivity = " + i);
      for (int j = 0; j < n; j++) {
       if(tp[j] == i) visit(j);
      }
     System.out.println("");
     }
    }
    
    //a path between u and v
    int u, v; int [] t; boolean found = false;
    public void pathDFT(int i, boolean []c) {
     c[i] = true;
     for (int j = 0; j < n; j++) {
      if(!c[j] && a[i][j] > 0) {
       t[j] = i;
       if(j == v) {
        found = true; return;
       }
       pathDFT(j, c);
      }}
    }
    public void pathDFT(int u, int v) {
     boolean [] c = new boolean[n];
     t = new int [n];
     this.u = u;
     this.v = v;
     pathDFT(u, c);
     if(found == false) 
      System.out.println("not found a path from " + vertex[u] + " to " + vertex[v]);
     else {
      System.out.println("a path from " + vertex[u] + " to " + vertex[v]);
      int [] h = new int[n];
      int hn = 0;
      h[0] = v;
      while(u != v) {
       v = t[v]; h[++hn] = v;
      }
      for (int i = hn; i >= 0; i--) {
       visit(h[i]);
       if(i > 0) System.out.print("->");
      }}
    }
    /*Session 2_______________________________________________________________*/
    void setData(int[][] b) {
        n = b.length;
        int i, j;
        a = new int[n][n];
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                a[i][j] = b[i][j];
            }
        }
    }

    void dispAdj() {
        int i, j;
        for (i = 0; i < n; i++) {
            System.out.println();
            for (j = 0; j < n; j++) {
                System.out.printf("%4d", a[i][j]);
            }
        }
    }
    void dijkstra(int fro, int to) {
        int INF = 99;
        boolean[] S = new boolean[n];
        int[] d = new int[n];
        int[] p = new int[n];
        int i, j, k, t;
        for (i = 0; i < n; i++) {
            S[i] = false;
            d[i] = a[fro][i];
            p[i] = fro;
        }
        S[fro] = true;

        while (true) {
            t = INF;
            k = -1;
            for (i = 0; i < n; i++) {
                if (S[i]) {
                    continue;
                }
                if (d[i] < t) {
                    t = d[i];
                    k = i;
                }
            }
            if (k == -1) {
                System.out.println("No solution");
                return;
            }
            // select k into the set S
            S[k] = true;
            if (k == to) {
                break;
            }
            // update data
            for (i = 0; i < n; i++) {
                if (S[i]) {
                    continue;
                }
                if (d[i] > d[k] + a[k][i]) {
                    d[i] = d[k] + a[k][i];
                    p[i] = k;
                }
            }
        }
        System.out.println("The shortest distance is " + d[to]);
        MyStack s = new MyStack();
        i = to;
        while (true) {
            s.push(i);
            if (i == fro) {
                break;
            }
            i = p[i];
        }
        i = s.pop();
        System.out.println("Th shotest path is");
        System.out.print(i);
        while (!s.isEmpty()) {
            i = s.pop();
            System.out.print(" -> " + i);
        }
        System.out.println();
    }
    
    int deg(int i) {
        int s, j;
        s = 0;
        for (j = 0; j < n; j++) {
            s += a[i][j];
        }
        s += a[i][i];
        return (s);
    }

    boolean allDegEven() {
        for (int i = 0; i < n; i++) {
            if (deg(i) % 2 == 1) {
                return (false);
            }
        }
        return (true);
    }

    boolean isConnected() {
        MyStack s = new MyStack();
        boolean[] p = new boolean[n];
        int i, r;
        for (i = 0; i < n; i++) {
            p[i] = false;
        }
        s.push(0);
        p[0] = true;
        while (!s.isEmpty()) {
            r = s.pop();
            for (i = 0; i < n; i++) {
                if (!p[i] && a[r][i] > 0) {
                    s.push(i);
                    p[i] = true;
                }
            }
        }
        for (i = 0; i < n; i++) {
            if (!p[i]) {
                return (false);
            }
        }
        return (true);
    }

    boolean hasEulerCycle() {
        if (allDegEven() && isConnected()) {
            return (true);
        } else {
            return (false);
        }
    }

    void eulerCycle(int k) {
        if (!hasEulerCycle()) {
            System.out.println("Condicitons for Euler's cycle are not satisfied");
            return;
        }
        int[] eu = new int[100];
        int m = 0;
        int i, j, r;
        MyStack s = new MyStack();
        s.push(k);
        while (!s.isEmpty()) {
            r = s.top();
            for (i = 0; i < n; i++) {
                if (a[r][i] > 0) {
                    break;
                }
            }
            if (i == n) { // r isolated
                s.pop();
                eu[m++] = r;
            } else {
                s.push(i);
                a[r][i]--;
                a[i][r]--;
            }
        }
        System.out.println("Euler's cycle is:");
        System.out.print(v[eu[0]]);
        for (i = 1; i < m; i++) {
            System.out.print(" -> " + v[eu[i]]);
        }
        System.out.println();
    }
}
