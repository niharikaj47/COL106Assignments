import java.util.*;
import java.io.*;

public class MMBurgers implements MMBurgersInterface 
{
  private class Heapss
  {
    private Node_heap[] arr;
    protected int capacity=1;
    public int size=0;
    public int start=1;
    public int end=start+size+1;
    
    public Heapss()
    {
        arr=new Node_heap[capacity+1];
    }

    public void checkgrow() 
    {
        if(size==capacity){
            Node_heap[] arr2=new Node_heap[2*capacity+1];
            for(int i=start;i<end;i++) arr2[i]=arr[i];
            capacity=capacity*2;
            arr=arr2;
            start=1;
            end=start+size+1;                   //end is the index that is empty
        }
    }

    public int size(){
        return this.size;
    }

    
    public void percolatedown(int i,Node_heap x)
    {
      int n=this.size;
      if(2*i>n) {arr[i]=x;}
      else if(2*i==n || 2*i+1==n){
          if(arr[2*i].qlength >x.qlength) arr[i]=x;
          else if (arr[2*i].qlength == x.qlength)
          {
              if(x.qnum > arr[2*i].qnum){
                  arr[i]=arr[2*i];
                  arr[2*i]=x;
              }
              else arr[i]=x;
          }
      }
      else if(2*i<=n)
      {
          int j;
          if(arr[2*i].qlength < arr[2*i+1].qlength) j=2*i;
          else if (arr[2*i].qlength==arr[2*i+1].qlength){
              if(arr[2*i].qnum<arr[2*i+1].qnum) j=2*i;
              else j=2*i+1;
          }
          else  j=2*i +1;

          if (arr[j].qlength<x.qlength)
          {
              arr[i] = arr[j];
              arr[j]=x;
              percolatedown(j,x);
          }
          else if(arr[j].qlength==x.qlength){
              if(arr[j].qnum<x.qnum){
                  arr[i] = arr[j];
                  arr[j]=x;
                  percolatedown(j,x);
              }
              else arr[i]=x;
          }
          else arr[i]=x;
      }
  }



    public void percolateup(int i, Node_heap x)
    {
      System.out.println(i);
        if(i==start) arr[i]=x;
        else if(arr[i/2].qlength>x.qlength )
        {
            arr[i]=arr[i/2];
            arr[i/2]=x;
            percolateup(i/2,x);
        }
        else if(arr[i/2].qlength==x.qlength)
        {
            if(arr[i].qnum < arr[i/2].qnum){
                arr[i]=arr[i/2];
                arr[i/2]=x;
                percolateup(i/2,x);
            }
            else arr[i]=x;
        }
        else arr[i]=x;
    }


    public void buildminheap(){
        int k=this.size;
        for(int i=k/2;i>=start;i--){
            percolatedown(i,arr[i]);
        }
    }

    public void insertheap(Node_heap x)
    {
        this.checkgrow();
        int k=this.size;
        arr[end]=x;
        size=size+1;
        end=end+1;
        percolateup(end,x);  
    }


    public void deletemin(){
        int k=this.size;
        Node_heap temp=arr[start];
        arr[start]=arr[end-1];
        arr[end-1]=null;
        end=end-1;
        size=size-1;
        percolatedown(start,arr[start]);
        insertheap(temp);
    }


    public Node_heap q_min(){
        return arr[start];
    }
  }

    private Node root;

    public class Node_heap            //This node is for heaps 
    {
        private Queue<Node> abc;
        private int qlength;
        private int qnum;

        Node_heap(int k,Queue<Node> idc)
        {
            abc=idc; 
            qlength=abc.size();
            qnum=k;
        }
    }

    private class Node1                   //This node is for griddle wait 
    {                 
      private int c_id;
      private int num2;
      private Node n;

      Node1(int a,int b,Node w){
        c_id=a;
        num2=b;
        n=w;
      }
    }

    private class Node2                    //This node is for griddle 
    {
      private int ot;
      private int num;
      Node2(int a,int b)
      {
        ot=a;
        num=b;

      }
    }  

    private class Node {
    
      private int queueno;
      private int key;
      private int balance;
      private int height;
      private Node left, right, parent;
      private int timein;
      private int burgers;
      private int outtimequeue;
      private int otgriddle;
      private int tdelivery;
  
      Node(int k, Node p) {
        key = k;
        parent = p;
        timein=-1;
        burgers=-1;
        queueno=-1;
        outtimequeue=-1;
        otgriddle=-1;
        tdelivery=-1;
      }
    }
  
    public boolean insert(int key) {
      if (root == null) root = new Node(key, null);
      else {
        Node n = root;
        Node parent;
        while (true) {
          if (n.key == key) return false;
  
          parent = n;
  
          boolean goLeft = n.key > key;
          n = goLeft ? n.left : n.right;
  
          if (n == null) {
            if (goLeft) {
              parent.left = new Node(key, parent);
            } else {
              parent.right = new Node(key, parent);
            }
            rebalance(parent);
            break;
          }
        }
      }
      return true;
    }
  
    private void delete(Node node) {
      if (node.left == null && node.right == null) {
        if (node.parent == null) root = null;
        else {
          Node parent = node.parent;
          if (parent.left == node) {
            parent.left = null;
          } else parent.right = null;
          rebalance(parent);
        }
        return;
      }
      if (node.left != null) {
        Node child = node.left;
        while (child.right != null) child = child.right;
        node.key = child.key;
        delete(child);
      } else {
        Node child = node.right;
        while (child.left != null) child = child.left;
        node.key = child.key;
        delete(child);
      }
    }
  
    public void delete(int delKey) {
      if (root == null) return;
      Node node = root;
      Node child = root;
  
      while (child != null) {
        node = child;
        child = delKey >= node.key ? node.right : node.left;
        if (delKey == node.key) {
          delete(node);
          return;
        }
      }
    }
  
    private void rebalance(Node n) {
      setBalance(n);
  
      if (n.balance == -2) {
        if (height(n.left.left) >= height(n.left.right)) n = rotateRight(n);
        else n = rotateLeftThenRight(n);
  
      } else if (n.balance == 2) {
        if (height(n.right.right) >= height(n.right.left)) n = rotateLeft(n);
        else n = rotateRightThenLeft(n);
      }
  
      if (n.parent != null) {
        rebalance(n.parent);
      } else {
        root = n;
      }
    }
  
    private Node rotateLeft(Node a) {
  
      Node b = a.right;
      b.parent = a.parent;
  
      a.right = b.left;
  
      if (a.right != null) a.right.parent = a;
  
      b.left = a;
      a.parent = b;
  
      if (b.parent != null) {
        if (b.parent.right == a) {
          b.parent.right = b;
        } else {
          b.parent.left = b;
        }
      }
  
      setBalance(a, b);
  
      return b;
    }
  
    private Node rotateRight(Node a) {
  
      Node b = a.left;
      b.parent = a.parent;
  
      a.left = b.right;
  
      if (a.left != null) a.left.parent = a;
  
      b.right = a;
      a.parent = b;
  
      if (b.parent != null) {
        if (b.parent.right == a) {
          b.parent.right = b;
        } else {
          b.parent.left = b;
        }
      }
  
      setBalance(a, b);
  
      return b;
    }
  
    private Node rotateLeftThenRight(Node n) {
      n.left = rotateLeft(n.left);
      return rotateRight(n);
    }
  
    private Node rotateRightThenLeft(Node n) {
      n.right = rotateRight(n.right);
      return rotateLeft(n);
    }
  
    private int height(Node n) {
      if (n == null) return -1;
      return n.height;
    }
  
    private void setBalance(Node... nodes) {
      for (Node n : nodes) {
        reheight(n);
        n.balance = height(n.right) - height(n.left);
      }
    }
  
    private void reheight(Node node) {
      if (node != null) {
        node.height = 1 + Math.max(height(node.left), height(node.right));
      }
    }
  
    public boolean search(int key) 
    {
      Node result = searchHelper(this.root, key);
      if (result != null) return true;
  
      return false;
    }
  
    private Node searchHelper(Node root, int key) 
    {
      
      if (root == null || root.key == key) return root;

      if (root.key > key)
        return searchHelper(root.left, key); 
  
      return searchHelper(root.right, key);
    }
    
   
    int x;
    int[] time;
    int griddlespace;
    int t_i=0;
    int t_f=0;
    Queue<Node1> g_w=new LinkedList<>();          //Node1 stores c_id and num2 burgers and ; seacrh and add tout
    Queue<Node2> g_s=new LinkedList<>();          //Node 2 stores ot and num
    int maxg_s;
    int g_wait=0;
    Node_heap[] counters;
    Heapss heap_xyz=new Heapss();
    int totalno=0;
    int counterno=0;




    public void timeupdate(int t)
    {
      for(int i=t_i;i<=t;i++)                   //Removing the cooked burgers from the griddle 
      {            
        boolean flag=false;
        while(g_s.isEmpty()==false  && flag==false)
        {
          Node2 cd=g_s.peek();
          if(cd.ot==i) {                      
            griddlespace=griddlespace+cd.num;
            g_s.remove();
          }
          else flag=true;
        }

        
        for(int j=x;j>=1;j--)                                           //Check customers at each counters and add to griddle wait
        {                                                            //Don't use  in any part of your code
          Node_heap x1=counters[j];
          Node x2;

          if(x1.abc.isEmpty()==false )  x2=x1.abc.peek();
          else x2=null;
          if( x2 !=null && x2.outtimequeue==i)              
          {
            Node w=x1.abc.peek();         
            Node1 n2 =new Node1(x2.key,x2.burgers,w);     
            g_w.add(n2);
            g_wait = g_wait + x2.burgers;
            x1.abc.remove(); 
          }
          heap_xyz.buildminheap();
        }


        boolean flag2=false;
        while(!g_w.isEmpty() && flag2==false && griddlespace>0)        //My burgers are put for cooking g_w updated and g_s updated
        {
          Node1 n3=g_w.peek();
          if(n3.num2> griddlespace)
          {
            g_wait=g_wait-griddlespace;
            n3.num2=n3.num2-griddlespace;
            Node2 n4=new Node2(i+10,griddlespace);
            g_s.add(n4);
            griddlespace=0;
            flag2=true;
          }

          else if(n3.num2==griddlespace)
          {
            g_wait=g_wait-griddlespace;
            n3.num2=0;
            n3.n.otgriddle=i+10;
            n3.n.tdelivery=i+10+1;
            g_w.remove();
            Node2 n4=new Node2(i+10,griddlespace) ;     //ot is out time from griddle  which is 10+current time 
            g_s.add(n4);
            griddlespace=0;
            flag2=true;
          }

          else if(n3.num2<griddlespace)
          {
            g_wait=g_wait-n3.num2;
            griddlespace=griddlespace-n3.num2;
            n3.n.otgriddle=i+10;
            n3.n.tdelivery=i+10+1;
            g_w.remove();
            Node2 n4= new Node2(i+10,n3.num2);    //ot is out time from griddle which is 10+ current time 
            g_s.add(n4);
            flag2=false;
          }
          // System.out.println(2);
        }


        for(int j=1;j<=x;j++)
        {
          time[j]--;
          if(time[j]<0) time[j]=0;
        }   
      }
    }


    public boolean isEmpty()
    {                                               //Another way can be to traverse through each node
      boolean flag3=true;                           //and check customer state of each node 
      int i=1;
      while(flag3==true && i<=counters.length-1)
      {
        if(counters[i].abc.isEmpty()) {flag3=true;i++;}
        else {flag3=false;}
      }
      if(flag3==true && griddlespace==maxg_s && g_wait==0 ) return true;
      else return false;
    }
    

    public void setK(int k) throws IllegalNumberException
    {
      counterno=k;
      x=k;
      time=new int[x+1];
      counters=new Node_heap[x+1];
      if(k<=0) throw new IllegalNumberException("Invalid number ");
      else{
        for(int i=1;i<=x;i++)
        {
          Queue<Node> abc=new LinkedList<>();         //initialised a new queue node for my linkedlist
          Node_heap w=new Node_heap(i,abc);
          heap_xyz.insertheap(w);
          counters[i] = w;
        }

        for(int i=1;i<=x;i++){
          time[i]=0;
        }
      } 
    }


    public void setM(int m) throws IllegalNumberException   
    {
      if(m<=0) throw new IllegalNumberException("Invalid number");
      else {
        griddlespace=m;
        maxg_s=m;
      }
    } 


    public void advanceTime(int t) throws IllegalNumberException
    {
      if(t<t_i) throw new IllegalNumberException("Invalid number");
      else {
        timeupdate(t);
        t_i=t;
      }
    }


    public void arriveCustomer(int id, int t, int numb) throws IllegalNumberException
    {
      if(t<t_i || numb<=0 || id<0) throw new IllegalNumberException("Invalid number");
      else{
        advanceTime(t);
        t_i=t;
        Node_heap ac=heap_xyz.q_min();
        int index=ac.qnum;
        time[index]=time[index]+index;
        Node w=new Node(id,null);
        insert(w.key);
        Node a=searchHelper(root,id);
        a.queueno=index;
        a.timein=t;
        a.burgers=numb;
        a.outtimequeue=time[index];
        ac.abc.add(a);
        heap_xyz.deletemin();
        totalno++;
      }
    }  
    public int check(int id){
      Node a =searchHelper(root ,id);
      return a.queueno;
    }

    public int customerState(int id, int t) throws IllegalNumberException
    {
      int n8=0;
      if(t<t_i) throw new IllegalNumberException("Invalid time");
      else if(search(id)==false ) throw new IllegalNumberException("Invalid Number");
      else
      {
        advanceTime(t);
        t_i=t;
        if(search(id)==false) n8=0;
        Node a=searchHelper(root,id);
        if(t<a.outtimequeue) n8= a.queueno;
        else if(t>=a.outtimequeue && a.tdelivery==-1) n8=a.queueno+1;
        else if(t>=a.tdelivery) n8= a.queueno+2;
      }
      return n8;

    }

    public int griddleState(int t) throws IllegalNumberException
    {
      if(t<t_i) throw new IllegalNumberException("Invalid number");
      else
      {
        advanceTime(t);
        t_i=t;
        return maxg_s-griddlespace;
      }
    } 




    public int griddleWait(int t) throws IllegalNumberException{
      if(t<t_i) throw new IllegalNumberException("Illegal number");
      else{
        advanceTime(t);
        t_i=t;
        return g_wait;
      }
    } 

    public int customerWaitTime(int id) throws IllegalNumberException
    {
      Node a;
      if(search(id)==false) throw new IllegalNumberException("Illegal id");
      else{
        a=searchHelper(root,id);
      }
      return a.tdelivery-a.timein;
    }
    
    public int customerTime(Node a)
    {
      return a.tdelivery-a.timein;
    }

    int totaltime=0;

    public void traversal(Node abc) 
    {
      if(abc==null) return;
      traversal(abc.left);
      traversal(abc.right);
      totaltime=totaltime+customerTime(abc);
    }

	  public float avgWaitTime()
    {
      traversal(root);
      float h1=totaltime;
      float h2=totalno;
      float h3 =h1/h2;
      return h3;
    }

  }