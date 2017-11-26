import java.util.*;

/**
 * Container class to different classes, that makes the whole
 * set of classes one class formally.
 */
public class GraphTask {

    /* Main method. */
    public static void main(String[] args) {
        GraphTask a = new GraphTask();
        a.run();
    }

    /* Actual main method to run examples and everything. */
    public void run() {
        Graph g = new Graph("G");

        g.createRandomSimpleGraph(5,4);
  // g.createRandomSimpleGraph(10,9); // TEST 2
//        List<Vertex> connectedVertices = g.getConnectedVertices(null);  // TEST 3

        // TEST 4
        /*
              g.createRandomTree(6);   
        g.first.first.next = null; // kustutame esimese kaare    
        g.first.first = null; // kustutame esimese kaare  teise otspunkti
        */
        
     // kustutame kõik kaared - TEST 5       
        g.first.next.next.next.first = null;
        g.first.next.next.next.next.first = null;
        g.first.next.first = null;
        g.first.next.next.first = null;        
        g.first.first.next = null;
        g.first.first = null;
        // TEST 5 lõpp

        /*        
        List<Vertex> connectedVertices = g.getConnectedVertices(g.first); // .next.next.next
        for (Vertex vertex : connectedVertices) {
            System.out.println(vertex);
        }
        */       
        
        System.out.println("--- graafi väljatrükk algab --- \n" + g + "\n--- graafi väljatrüki lõpp ---");       
       
        List<Vertex> connectedVertices1 = g.getConnectedVertices(g.first); // .next.next.next       
         System.out.print("\n--- ühendused tipust" +  g.first.toString() + ": ");
        for (Vertex vertex : connectedVertices1) {
           System.out.print(vertex);
        } 
        List<Vertex> connectedVertices2 = g.getConnectedVertices(g.first.next); // .next.next.next       
        System.out.print("\n--- ühendused tipust" +  g.first.next.toString() + ": ");
       for (Vertex vertex : connectedVertices2) {
          System.out.print(vertex);
       }         
        List<Vertex> connectedVertices3 = g.getConnectedVertices(g.first.next.next); // .next.next.next       
         System.out.print("\n--- ühendused tipust" +  g.first.next.next.toString() + ": ");
        for (Vertex vertex : connectedVertices3) {
           System.out.print(vertex);
        } 
        List<Vertex> connectedVertices4 = g.getConnectedVertices(g.first.next.next.next); // .next.next.next       
        System.out.print("\n--- ühendused tipust" +  g.first.next.next.next.toString() + ": ");
       for (Vertex vertex : connectedVertices4) {
          System.out.print(vertex);
       } 
       List<Vertex> connectedVertices5 = g.getConnectedVertices(g.first.next.next.next.next); // .next.next.next       
       System.out.print("\n--- ühendused tipust" +  g.first.next.next.next.next.toString() + ": ");
      for (Vertex vertex : connectedVertices5) {
         System.out.print(vertex);
      }         
       
                
    }
   

    /** 
     * Kirjutada graafi sügavuti läbimisel põhinev algoritm, 
     * mis leiab kõik tipud antud graafis G = (V, E), 
     * mis on saavutatud tipust a ∈ V
     */
    class Vertex {

        private String id;
        private Vertex next;
        private Arc first;
        private int info = 0;

        Vertex(String s, Vertex v, Arc e) {
            id = s;
            next = v;
            first = e;
        }

        Vertex(String s) {
            this(s, null, null);
        }

        @Override
        public String toString() {
            return id;
        }
    }


    /**
     * Arc represents one arrow in the graph. Two-directional edges are
     * represented by two Arc objects (for both directions).
     */
    class Arc {

        private String id;
        private Vertex target;
        private Arc next;
        private int info = 0;
        // You can add more fields, if needed

        Arc(String s, Vertex v, Arc a) {
            id = s;
            target = v;
            next = a;
        }

        Arc(String s) {
            this(s, null, null);
        }

        @Override
        public String toString() {
            return id;
        }
    }


    class Graph {

        private String id;
        private Vertex first;
        private int info = 0;

        Graph(String s, Vertex v) {
            id = s;
            first = v;
        }

        Graph(String s) {
            this(s, null);
        }

        @Override
        public String toString() {
            String nl = System.getProperty("line.separator");
            StringBuffer sb = new StringBuffer(nl);
            sb.append(id);
            sb.append(nl);
            Vertex v = first;
            while (v != null) {
                sb.append(v.toString());
                sb.append(" -->");
                Arc a = v.first;
                while (a != null) {
                    sb.append(" ");
                    sb.append(a.toString());
                    sb.append(" (");
                    sb.append(v.toString());
                    sb.append("->");
                    sb.append(a.target.toString());
                    sb.append(")");
                    a = a.next;
                }
                sb.append(nl);
                v = v.next;
            }
            return sb.toString();
        }

        public Vertex createVertex(String vid) {
            Vertex res = new Vertex(vid);
            res.next = first;
            first = res;
            return res;
        }

        public Arc createArc(String aid, Vertex from, Vertex to) {
            Arc res = new Arc(aid);
            res.next = from.first;
            from.first = res;
            res.target = to;
            return res;
        }

        /**
         * Create a connected undirected random tree with n vertices.
         * Each new vertex is connected to some random existing vertex.
         *
         * @param n number of vertices added to this graph
         */
        public void createRandomTree(int n) {
            if (n <= 0)
                return;
            Vertex[] varray = new Vertex[n];
            for (int i = 0; i < n; i++) {
                varray[i] = createVertex("v" + String.valueOf(n - i));
                if (i > 0) {
                    int vnr = (int) (Math.random() * i);
                    createArc("a" + varray[vnr].toString() + "_"
                            + varray[i].toString(), varray[vnr], varray[i]);
                    createArc("a" + varray[i].toString() + "_"
                            + varray[vnr].toString(), varray[i], varray[vnr]);
                } else {
                }
            }
        }

        /**
         * Create an adjacency matrix of this graph.
         * Side effect: corrupts info fields in the graph
         *
         * @return adjacency matrix
         */
        public int[][] createAdjMatrix() {
            info = 0;
            Vertex v = first;
            while (v != null) {
                v.info = info++;
                v = v.next;
            }
            int[][] res = new int[info][info];
            v = first;
            while (v != null) {
                int i = v.info;
                Arc a = v.first;
                while (a != null) {
                    int j = a.target.info;
                    res[i][j]++;
                    a = a.next;
                }
                v = v.next;
            }
            return res;
        }

        /**
         * Create a connected simple (undirected, no loops, no multiple
         * arcs) random graph with n vertices and m edges.
         *
         * @param n number of vertices
         * @param m number of edges
         */
        public void createRandomSimpleGraph(int n, int m) {
            if (n <= 0)
                return;
            if (n > 2500)
                throw new IllegalArgumentException("Too many vertices: " + n);
            if (m < n - 1 || m > n * (n - 1) / 2)
                throw new IllegalArgumentException
                        ("Impossible number of edges: " + m);
            first = null;
            createRandomTree(n); // n-1 edges created here
            Vertex[] vert = new Vertex[n];
            Vertex v = first;
            int c = 0;
            while (v != null) {
                vert[c++] = v;
                v = v.next;
            }
            int[][] connected = createAdjMatrix();
            int edgeCount = m - n + 1; // remaining edges
            while (edgeCount > 0) {
                int i = (int) (Math.random() * n); // random source
                int j = (int) (Math.random() * n); // random target
                if (i == j)
                    continue; // no loops
                if (connected[i][j] != 0 || connected[j][i] != 0)
                    continue; // no multiple edges
                Vertex vi = vert[i];
                Vertex vj = vert[j];
                createArc("a" + vi.toString() + "_" + vj.toString(), vi, vj);
                connected[i][j] = 1;
                createArc("a" + vj.toString() + "_" + vi.toString(), vj, vi);
                connected[j][i] = 1;
                edgeCount--; // a new edge happily created
            }
        }

        /**
         * Sisendmeetod
         * @param v tipp, mida uuritakse
         * @return List kõikidest tippudest, mis on uuritava tipuga ühenduses ({@code v})
         */
        public List<Vertex> getConnectedVertices(Vertex v) {
            return getConnectedVertices(v, new ArrayList<>());
        }

        /**
         * Leida tipud, mis on selle tipuga ühenduses
         * @param v tipp, millega seotud tippe otsida
         * @param läbitud tippude list , "mustad"
         * @return ühendatud tippude list {@code v}
         */
        private List<Vertex> getConnectedVertices(Vertex v, List<Vertex> processed) {
            if (v == null) {
                throw new IllegalArgumentException("Töödeldav tipp ei tohi olla null");
            }

            //  "värvi mustaks" v - lisa töödeldute listi 
            processed.add(v);

            List<Vertex> connected = new ArrayList<>();

            Arc arc = v.first;

            // esimene kaar, mis sellest tipust lähtub 
            while (arc != null) {
                // värvi kaare otsas olev tipp "halliks"- töötle
                Vertex target = arc.target;
                // jäta meelde järgmine kaar
                arc = arc.next;

                // kui tipp on töödeldute listis ("must)" , siis võta järgmine kaar
                if (processed.contains(target)) {
                    continue;
                }

                // lisa "hall" tipp tulemuste listi 
                connected.add(target);
                // rekursioon - töötle kõik "hallist" tipust lähtuvad kaared ja lisa tulemus listi
                connected.addAll(getConnectedVertices(target, processed));
            }            
            // tagasta ühendatud tipud
            return connected;
        }
    }
}
