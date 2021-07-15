// You should utilise your implementation of ArrayDeque methods to implement this
public class Stack implements StackInterface {	
	
	ArrayDeque stack=new ArrayDeque();		
	public void push(Object o)
	{
		// System.out.println("Push: " + o);
		 stack.insertLast(o);
		
	}
	public Object pop() throws EmptyStackException{
		try{
			Object temp=stack.removeLast();
		    return temp;
		}
		catch(EmptyDequeException e){
			throw new EmptyStackException("Stack is Empty");
		}
	}
	public Object top() throws EmptyStackException{
		try{
			Object temp=stack.last();
			return temp;}
		catch(EmptyDequeException e){
			throw new EmptyStackException("Stack is Empty");
		}
	}
	public boolean isEmpty(){
		return stack.isEmpty();
	}
    public int size(){
		return stack.size();
	}
}