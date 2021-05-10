
import java.util.*;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;



//class LRUCache {
//    private class Node{
//        int v;
//        int k;
//        Node pre;
//        Node next;
//        Node(){}
//        Node(int k, int v){
//            this.k = k;
//            this.v = v;
//        }
//    }
//    private int size;
//    private int capacity;
//    private HashMap<Integer, Node> nodeMap;
//    private Node head, tail;
//    public LRUCache(int capacity) {
//        nodeMap = new HashMap<>();
//        this.capacity = capacity;
//        size = 0;
//        head = new Node();
//        tail = new Node();
//        head.next = tail;
//        tail.pre = head;
//    }
//    public int get(int key) {
//        Node node = nodeMap.get(key);
//        if(node == null) return -1;
//        else{
//            releaseNode(node);
//            addToHead(node);
//            return node.v;
//        }
//    }
//    public void put(int key, int value) {
//        Node node = nodeMap.get(key);
//        if(node == null){       // new node
//            node = new Node(key, value);
//            nodeMap.put(node.k, node);
//            addToHead(node);
//            size++;
//        }else{                  // update
//            node.v = value;
//            releaseNode(node);
//            addToHead(node);
//        }
//        if(size > capacity){
//            nodeMap.remove(tail.pre.k);
//            releaseNode(tail.pre);
//            size--;
//        }
//    }
//    private void addToHead(Node x){
//        x.pre = head;
//        x.next = head.next;
//        head.next.pre = x;
//        head.next = x;
//    }
//    private void releaseNode(Node x){
//        x.pre.next = x.next;
//        x.next.pre = x.pre;
//    }
//
//}
//class Solution1 {
//    // https://leetcode-cn.com/problems/find-critical-and-pseudo-critical-edges-in-minimum-spanning-tree/submissions/
//    private class Edge implements Comparable<Edge>{
//        public int l;
//        public int r;
//        public int v;
//        private int id;
//        public Edge(int _l, int _r, int _v, int _id){
//            l = _l;
//            r = _r;
//            v = _v;
//            id = _id;
//        }
//        @Override
//        public int compareTo(Edge o) {  // ascending sorting method which will be called by Arrarys.sort
//            return v - o.v;
//        }
//    }
//    private int fa[];
//    private Edge edge[];
//    private int value; // the weight of MST
//    private int find(int x){
//        if(x != fa[x]) fa[x] = find(fa[x]);
//        return fa[x];
//    }
//    private void unit(int x, int y){
//        fa[find(x)] = find(y);
//    }
//    private void init(int n){
//        fa = new int[n];
//        for (int i = 0; i < n; i++) {
//            fa[i] = i;
//        }
//    }
//    public List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
//        init(n);
//        value = 0;
//        edge = new Edge[edges.length];
//        for (int i = 0; i < edges.length; i++) {
//            edge[i] = new Edge(edges[i][0], edges[i][1], edges[i][2], i);
//        }
//        Arrays.sort(edge, 0, edges.length);
//        for (int i = 0; i < edges.length; i++) {
//            int fx = find(edge[i].l);
//            int fy = find(edge[i].r);
//            if(fx != fy){
//                unit(fx, fy);
//                value += edge[i].v;
//            }
//        }
//        List<List<Integer>> ans = new LinkedList<>();
//        List<Integer> cri = new LinkedList<>();
//        List<Integer> pseudo = new LinkedList<>();
//        for (int i = 0; i < edges.length; i++) {
//            int tempValue = 0;
//            init(n);
//            for (int j = 0; j < edges.length; j++) {
//                if(j == i) continue;
//                int fx = find(edge[j].l);
//                int fy = find(edge[j].r);
//                if(fx != fy){
//                    unit(fx, fy);
//                    tempValue += edge[j].v;
//                }
//            }
//            if(value != tempValue){   // is critical edge
//                cri.add(edge[i].id);
//            }else{                    // judge if it is pseudo-critical edge
//                tempValue = 0;
//                init(n);
//                unit(edge[i].l, edge[i].r);
//                tempValue += edge[i].v;
//                for (int j = 0; j < edges.length; j++) {
//                    if(j == i) continue;
//                    int fx = find(edge[j].l);
//                    int fy = find(edge[j].r);
//                    if(fx != fy){
//                        unit(fx, fy);
//                        tempValue += edge[j].v;
//                    }
//                }
//                if(tempValue == value) pseudo.add(edge[i].id);
//            }
//        }
//        ans.add(cri);
//        ans.add(pseudo);
//        return ans;
//    }
//}
//class Solution2 {
//
//    private class Edge{
//        // https://leetcode-cn.com/problems/min-cost-to-connect-all-points/solution/prim-and-kruskal-by-yexiso-c500/
//        int l;
//        int r;
//        int v;
//        Edge(int _l, int _r, int _v) {
//            l = _l;
//            r = _r;
//            v = _v;
//        }
//    }
//    private List<Edge> edge;
//    private int n;
//    private int manhattanDis(int x1, int y1, int x2, int y2){
//        return Math.abs(x1- x2) + Math.abs(y1 - y2);
//    }
//    private int fa[];
//    private void init(){
//        fa = new int[n];
//        for (int i = 0; i < n; i++) {
//            fa[i] = i;
//        }
//    }
//    private int find(int x){
//        if(x != fa[x]) fa[x] = find(fa[x]);
//        return fa[x];
//    }
//    private void unit(int x, int y){
//        fa[find(x)] = find(y);
//    }
//    public int minCostConnectPoints(int[][] points) {
//        n = points.length;
//        init();
//        edge = new ArrayList<>();
//        for (int i = 0; i < n; i++) {
//            for (int j = i + 1; j < n; j++) {
//                edge.add(new Edge(i, j, manhattanDis(points[i][0], points[i][1],
//                        points[j][0], points[j][1])));
//            }
//        }
//        Collections.sort(edge, new Comparator<Edge>() {
//            @Override
//            public int compare(Edge o1, Edge o2) {
//                return o1.v - o2.v;
//            }
//        });
//        int ans = 0;
//        for (int i = 0; i < edge.size(); i++) {
//            int l = edge.get(i).l;
//            int r = edge.get(i).r;
//            if(find(l) != find(r)){
//                unit(l, r);
//                ans += edge.get(i).v;
//            }
//        }
//        return ans;
//    }
//}
//class Solution {
//    public int[] findRedundantDirectedConnection(int[][] edges) {
//
//    }
//}


class Student{
    public String stdname;
    public Integer stdcode;
    public Student(String stdname,Integer stdcode) {
        this.stdname=stdname;
        this.stdcode=stdcode;
    }
    public Student(String stdname) {
        this.stdname=stdname;
        this.stdcode=new Random().nextInt();
    }
    public void study(String lesson) {
        System.out.println(stdname+" learning "+lesson);
    }
    public void setName(String stdname) {
        this.stdname=stdname;
    }
    public void doSomeHelp(Student s) {
        System.out.println(stdname+" help "+s.stdname);
    }
}

class Lexer{
    static Map<String, Student> object_map = new HashMap<>();

    void method(String expr){

        if(expr.contains("setName")) {
            String regExpr = "^(\\w*).setName\\(\"(\\w*)\"\\);$";
            Pattern pattern = Pattern.compile(regExpr);
            Matcher matcher = pattern.matcher(expr);
            if (matcher.matches()) {
                Student object = object_map.get(matcher.group(1));

                object.setName(matcher.group(2));

            } else {
                System.out.println("Wrong Method");
            }
        }else if(expr.contains("study")){
            String regExpr = "^(\\w*).study\\(\"(\\w*)\"\\);$";
            Pattern pattern = Pattern.compile(regExpr);
            Matcher matcher = pattern.matcher(expr);
            if (matcher.matches()) {
                try {
                    Student object = object_map.get(matcher.group(1));
                    object.study(matcher.group(2));
                } catch (Exception e){
                    System.out.println("Wrong Variable");
                }
            } else {
                System.out.println("Wrong Method");
            }

        }else if(expr.contains("doSomeHelp")) {
            String regExpr = "^(\\w*).doSomeHelp\\((\\w*)\\);$";
            Pattern pattern = Pattern.compile(regExpr);
            Matcher matcher = pattern.matcher(expr);
            if (matcher.matches()) {
                String ob_1_name = matcher.group(1);
                String ob_2_name = matcher.group(2);
                try{
                    Student ob_1 = object_map.get(ob_1_name);
                    Student ob_2 = object_map.get(ob_2_name);
                    ob_1.doSomeHelp(ob_2);

                }catch (Exception e){
                    System.out.println("Wrong Variable");
                }
            } else {
                System.out.println("Wrong Method");
            }
        }


    }

    void init(String viable_name, String std_name, String std_code){
        Student object = new Student(std_name, Integer.valueOf(std_code));
        object_map.put(viable_name, object);
    }

    public Lexer(String expr){

        if (expr.contains("s1.do();")) {
            System.out.println("Wrong Method");
        }

        else if (expr.contains("=new")){      // a constructor request
            //JavaGo.loggerInfo.info("Constructor Type");
            // check constructor
            //String [] exprs;

            String regExpr = "^\\w*\\s(\\w*)=new\\s\\w*\\(\"(\\w*)\",(\\d*)\\);$";
            Pattern pattern = Pattern.compile(regExpr);
            Matcher matcher = pattern.matcher(expr);
            boolean isMatched = matcher.matches();
            //System.out.println(isMatched);

            if(isMatched && expr.contains("Student")){
                JavaGo.loggerInfo.info("Call Student Constructor");
                //System.out.println("Student Constructor");
                init(matcher.group(1), matcher.group(2), matcher.group(3));
            }else if(!expr.contains("Student")){
                JavaGo.loggerInfo.info("Wrong Type");
                System.out.println("Wrong Type");
            }
            else{
                JavaGo.loggerInfo.info("Wrong Constructor");
                System.out.println("Wrong Constructor");
            }

        }else{ // method request
            method(expr);
        }
    }
}
public class JavaGo {
    static private final String INPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/input.txt";
    static private final String OUTPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/output.txt";
    public static final Logger loggerInfo = Logger.getLogger("Information");

    public static void main(String[] args) throws Exception {
        FileInputStream instream = null;
        PrintStream outstream = null;
        try {
            instream = new FileInputStream(INPUT);
            outstream = new PrintStream(new FileOutputStream(OUTPUT));
            System.setIn(instream);
            System.setOut(outstream);
        } catch (Exception e) {
            System.err.println("Error Occurred. " + e.toString());
        }
        Logger.getGlobal().info("Java log is on now.");
        Scanner in = new Scanner(System.in); // freopen() on

        while(in.hasNextLine()) {
            Lexer myLexer = new Lexer(in.nextLine());
        }
    }
}