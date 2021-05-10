import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
class LFUCache {
    private class Node{
        int v;
        int k;
        int t; // which means tier
        Node pre;
        Node next;
        Node(){}
        Node(int v, int k){
            this.v = v;
            this.k = k;
            t = 0;
        }
    }
    private int size;
    private int capacity;
    private Map<int, Node> nodeMap; // key to node
    private Map<int, Node> tierMap; // tier to node which represents the head node
    Node head, tail;
    public LFUCache(int capacity) {
        this.capacity = capacity;
        size = 0;
        nodeMap = new HashMap<>();
        tierMap = new HashMap<>();
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
        head.t = 1000000;
        tail.t = -1;
    }
    public int get(int key) {

    }
    public void put(int key, int value) {
        Node node = nodeMap[key];
        if(node == null){           // new node
            node = new Node(key, value);
            Node nextNode = tierMap[key];
            setPre(ndoe, nextNode);
            tierMap[node.t] =
        }else{                      // simple update
            node.t++;
            node.
        }
    }
    private void setPre(Node pre, Node node){
        pre.next = node;
        pre.pre = node.pre;
        node.pre.next = pre;
        node.pre = pre;
    }
    private void release(Node node){
        node.pre.next = null;
        node.next.pre = null;
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
public class JavaGo {

    static private final String INPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/input.txt";
    static private final String OUTPUT = "/Users/robertwong/IdeaProjects/DataStructure/src/output.txt";
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

        Scanner in = new Scanner(System.in); // freopen() on
        Logger.getGlobal().info("Java log is on now.");
        String expr = in.nextLine();
        Lexer lexer = new Lexer(expr);
        lexer.show();
        Processor processor = new Processor(lexer.getInFix());
        processor.show();
        Calculator calculator = new Calculator(processor.getPostFix());
        String ans = calculator.getResult();
        System.out.println(ans);
    }
}
