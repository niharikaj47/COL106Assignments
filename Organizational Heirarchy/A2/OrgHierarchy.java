import java.io.*; 
import java.util.*; 
// Tree node
class Node
{
	int index,key,height;
	Node left,right,parent;
	
	Node (int d)
	{
		this.parent=null;
		this.left=null;
		this.right=null;
		this.key=d;
		this.index=0;
		this.height=-1;
		return;
	}
}

public class OrgHierarchy implements OrgHierarchyInterface
{
	Vector<Integer> boss_index=new Vector<Integer>();
	Vector<Integer> node_id=new Vector<Integer>();
	Vector<Integer> levels=new Vector<Integer>();
	Vector<Vector<Integer>> employees=new Vector<Vector<Integer>>();

	Node root;
	int size ;
	public OrgHierarchy()
	{
		root=null;
		size=0;
	}

	private Node Search(int id)
	{
		Node x=this.root;
		Node y=null;
		boolean flag=false;
		while(x!=null && !flag)
		{	 
			y=x;
			if(x.key==id){flag=true;}
			else if(x.key>id){x=x.left;}
			else if (x.key<id){x=x.right;}
		}
		if(flag) return y;
		else return null;
	}

	private Node Minnode(Node w)
	{
		Node x=w;
		Node y=x;
		while(x.left!=null){
			y=x;
			x=x.left;
		}
		return y;
	}

	private Node Successor(Node x){
		if(x.right!=null){return Minnode(x.right);}
		Node y =x.parent;
		while(y!=null && x==y.right){
			x=y;
			y=y.parent;
		}
		return y;
	}

	int diff(Node N) { 
		if(N == null)
			return 0;
		return ht(N.left) - ht(N.right); 
	}	 

	int ht(Node x) {
		if(x == null)
			return -1;
		return x.height;
	}

	void rightRotate(Node y) {
		Node x = y.left;
		x.parent = y.parent;
		if(x.parent.left == y)
			x.parent.left = x;
		if(x.parent.right == y)
			x.parent.right = x;
		y.left = x.right;
		if(y.left != null)
			y.left.parent = y;
		x.right = y;
		y.parent = x;
		y.height = max(ht(y.left), ht(y.right)) + 1;
		x.height = max(ht(x.left), ht(x.right)) + 1;
	
		return;	
	}

	void leftRotate(Node x) { 
		Node y = x.right; 
		y.parent = x.parent;

		if(y.parent.left == x)
			y.parent.left = y;
		if(y.parent.right == x)
			y.parent.right = y;
		x.right = y.left;
		if(x.right != null)
			x.right.parent = x;
		y.left = x;
		x.parent = y;
 
	
		x.height = max(ht(x.left), ht(x.right)) + 1; 
		y.height = max(ht(y.left), ht(y.right)) + 1; 
	
		return; 
	}	 

	int max(int a, int b) { 
		if(a>b) return a;
		else return b; 
	} 

	public void rotate(Node node){
		node.height=max(ht(node.left),ht(node.right))+1;
		while(node.parent!=null)
		{	if(diff(node)>1 &&  ht(node.left.left)>=ht(node.left.right) ) rightRotate(node);
			else if(diff(node)>1 && ht(node.left.right)>ht(node.left.left)){
				leftRotate(node.left);
				rightRotate(node);}
			if(diff(node)<-1 &&  ht(node.right.right)>=ht(node.right.left) ) leftRotate(node);
			else if(diff(node)<-1 && ht(node.right.left)>ht(node.right.right)){
				rightRotate(node.right);
				leftRotate(node);}
			else {
				node=node.parent;
		
			}
		}
	}

	int counter=0;
	void insert(Node node,int id)
	{
		Node z= new Node(id);     //Created a new node with the given value 
		Node y=null;              //Extra node for comparison 
		Node x=node;
		while(x!=null){
			y=x;
			if(z.key<x.key){
				x=x.left;}
			else {
				x=x.right;}
		}
		z.parent =y;
		if(y==null){this.root=z;}
		else if (z.key<y.key){y.left=z;}
		else {y.right=z;}
		size=size+1;
		z.index=counter;
		counter++;
		Node check=z.parent;
		if(check!=null)
		{
			rotate(check);
			
		}
	}


	void Delete(int id)
	{
		Node check=null;
		Node w =Search(id);
		if(w.left==null && w.right==null )
		{
			System.out.println("X");
			if (w==root) {root=null;check=null;}
			else if(w.parent.left==w){
				w.parent.left=null;
				check=w.parent;
				w=null;
			}
			else if(w.parent.right==w){
				w.parent.right=null;
				check=w.parent;
				w=null;
			}
		}
	
		else if (w.left==null && w.right!=null)
		{
			System.out.println("Y");
			Node y=w.right;
			Node z=w.parent;
			if(w==root) {
				w.right=root;
				check=w.right;
				w=null;
			}
			else if(w.parent.left==w){
				w.parent.left=y;
				y.parent=z;
				check=y;
				w=null;
			}
			else if (w.parent.right==w){
				w.parent.right=y;
				y.parent=z;
				check=y;
				w=null;
			}
		}	

		else if (w.left!=null && w.right==null){

			System.out.println("Z");
			Node y=w.left;
			if(w==root) {w.left=root;w=null;check=root;}
			else if(w.parent.left==w){
				Node z=w.parent;
				w.parent.left=y;
				y.parent=z;
				check=y;
				w=null;
			}
			else if( w.parent.right==w){
				Node z=w.parent;
				z.right=y;
				y.parent=z;
				check=y;
				w=null;
			}
		}
		else if (w.left!=null && w.right!=null )
		{
			System.out.println("W");
			Node y=Successor(w);
			Node z=w.right;
			if(y==z){
				System.out.println("This loop");
				w=y;check=w;}
			else
			{
				System.out.println("This loop is defective ");
				Node x=w.left;
				Node t=y.parent;
				Node a =y.right;
				
				w.right=z;
				z.parent=w;
				t.left=a;
				if(a!=null) {
					System.out.println("This is bad");
					a.parent=t;
				}
				check=w;
			}
		}
		size=size-1;

		if(check!=null){
			while(check.parent!=null){
				rotate(check);
				check=check.parent;
				System.out.println("X");
			}
		}
	}
		
	public boolean isEmpty()
	{
		return root==null;
	}	 

	public int size(){
		return this.size;
	}

	public int level(int id) throws IllegalIDException, EmptyTreeException
	{
		Node x=Search(id);
		if(x==null) throw new IllegalIDException("No such employee");
		else if(isEmpty()) throw new EmptyTreeException("Tree is empty");
		else{
			int y=x.index;
			return levels.get(y);
		}
	} 

	public void hireOwner(int id) throws NotEmptyException{
		if(!(isEmpty()))throw new NotEmptyException("Owner is already hired");
		else{
			insert(root,id);
			Node y=Search(id);
			y.index=0;
			boss_index.add(-1);
			node_id.add(id);
			levels.add(1);
			employees.add(new Vector<Integer>());
		}
	}

	public void hireEmployee(int id, int bossid) throws IllegalIDException, EmptyTreeException{
		Node y=Search(bossid);
		if(y==null) throw new IllegalIDException("Invalid bossid");
		else if(isEmpty()) throw new EmptyTreeException("Tree is Empty");
		else {
			insert(root,id);
			Node w=Search(id);
			node_id.add(id);
			boss_index.add(y.index);
			levels.add(1+levels.get(y.index));
			employees.add(new Vector<Integer>());
			employees.get(y.index).add(w.index);
		}
	}

	public void fireEmployee(int id) throws IllegalIDException,EmptyTreeException
	{
		Node w=Search(id);
		if(w==null) throw new IllegalIDException("Not a valid id");
		else if(isEmpty()) throw new EmptyTreeException("Tree is Empty");
		else {
			levels.set(w.index,-1);
			Delete(id);
		}
	}	

	public void fireEmployee(int id, int manageid) throws IllegalIDException,EmptyTreeException{
		Node x=Search(id);
		Node y=Search(manageid);
		if(x==null || y==null ) throw new IllegalIDException("Not a valid id");
		else if(isEmpty()) throw new EmptyTreeException("Organization is Empty");
		else 
		{
			Vector<Integer> e1= employees.get(x.index);
			Vector<Integer> e2=employees.get(y.index);
			levels.set(x.index,-1);
			for(int i=0;i<e1.size();i++)
			{
				boss_index.set(e1.get(i),y.index);
				e2.add(e1.get(i));
			}
			System.out.println("JDCBCJ");
			Delete(id);
		}
	} 

	public int boss(int id) throws IllegalIDException,EmptyTreeException{
		Node x=Search(id);
		if(x==null) throw new IllegalIDException("Not a valid id ");
		else if(isEmpty()) throw new EmptyTreeException("Organization is empty");
		else{
			int y=boss_index.get(x.index);
			return node_id.get(y);
		}
	}

	public int lowestCommonBoss(int id1, int id2) throws IllegalIDException,EmptyTreeException{
		Node x=Search(id1);
		Node y=Search(id2);
		int x1,y1,l1,l2;
		if(x==null || y==null) throw new IllegalIDException("Invalid id");
		else if(isEmpty()) throw new EmptyTreeException("Tree is empty");
		else if(id1==node_id.get(0) || id2==node_id.get(0)) return -1;
		else{
			x1=x.index;y1=y.index;l1=levels.get(x1);l2=levels.get(y1);
			while(l1!=l2){
				if(l1<l2){
					y1=boss_index.get(y1);
					l2=l2-1;
				}
				else if(l1>l2)
				{
					x1=boss_index.get(x1);
					l1=l1-1;
				}
			}
			while(x1!=y1){
				x1=boss_index.get(x1);
				y1=boss_index.get(y1);
			}
		}
		return node_id.get(x1);
	}

	public String toString(int id) throws IllegalIDException, EmptyTreeException
	{
		String s="";
		Node w=Search(id);
		if(w==null) throw new IllegalIDException("Invalid id ");
		else if(isEmpty()) throw new EmptyTreeException("Tree is Empty");
		else
		{
		int x = w.index;

		Vector<Integer> v_id, v_index;
		v_index=new Vector<Integer>();
		v_index.add(x);
		// s=s+node_id.get(x);
		while (v_index.size() > 0) {
			// s=s+",";
			v_id = new Vector<Integer> ();
			for (int j = 0; j < v_index.size(); j++) {
				v_id.add(node_id.get(v_index.get(j)));
			}
			Collections.sort(v_id);
			for(int i=0;i<v_id.size()-1;i++){
				s=s+ v_id.get(i)+" ";	
			}
			s=s+v_id.get(v_id.size()-1);
			Vector<Integer> v_temp = new Vector<Integer> ();
			for (int j = 0; j < v_index.size(); j++) {
				for (int i = 0; i < employees.get(v_index.get(j)).size(); i++) {
					if (levels.get(employees.get(v_index.get(j)).get(i)) != -1) {
						v_temp.add(employees.get(v_index.get(j)).get(i));
					}
				}
			}
			s=s+",";
			v_index = v_temp;
		}
		// s.deleteCharAt(s.length()-1); 
		s=s.substring(0,s.length()-1);
		}
		return s;
	}
}

