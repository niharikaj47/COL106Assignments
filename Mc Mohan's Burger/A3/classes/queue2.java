public class queue2 {

    private Node1
  
    private Object[] deque;
    protected int capacity=1;
    protected int size=0;
    protected int start=0;
    protected int end=1;
    public ArrayDeque()
    {
      deque= new Node1[capacity];
    }
    public void checkgrow() 
    {
      if(size==capacity){
        Object[] deque2=new Object[2*capacity];
        int j=start; 
  
        for(int i=0;i<size;i++){ 
          deque2[i]=deque[(j+i)%size];
        }
        capacity=capacity*2;
        deque=deque2;
        start=0;
        end=size;
  
      }
    }
    public void insertFirst(Object o){
      this.checkgrow();
      start=(capacity+start-1)%capacity;
      deque[start]=o;
      size=size+1;
      return;
    }
    
    public void insertLast(Object o)
    {
      this.checkgrow();
      deque[(start+size)%capacity]=o;
      end=(end+1)%capacity;
      size=size+1;
      return;
    }
    
    public Object removeFirst() throws EmptyDequeException
    {
      if(isEmpty()) throw new EmptyDequeException ("Deque is Empty");
      Object temp=deque[start];
      start=(start+1)%capacity;
      size=size-1;
      return temp;
    }
    
    public Object removeLast() throws EmptyDequeException
    {
      if(isEmpty()) throw new EmptyDequeException ("Deque is Empty");
      end=(capacity+end-1)%capacity;
      Object temp=deque[end];
      size=size-1;
      return temp;
    }
  
    public Object first() throws EmptyDequeException{
      if(isEmpty()) throw new EmptyDequeException("Deque is Empty");
      else return deque[start]; 
    }  
    public Object last() throws EmptyDequeException{
      if(isEmpty()) throw new EmptyDequeException("Deque is Empty");
      else return deque[(capacity + end -1)%capacity];
    }
    
    public int size(){
      return this.size;
    }
    
    public boolean isEmpty(){
      if(size==0) 
        return true;
      return false;
    }
}
  