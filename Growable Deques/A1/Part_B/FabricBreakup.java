import java.util.Scanner;
import java.util.*;
import java.io.*;


public class FabricBreakup
{
	public static void main(String args[]) throws EmptyStackException
	{	
		try
		{
			FileInputStream fstream = new FileInputStream(args[0]);
			Scanner s=new Scanner(fstream);
			int N=s.nextInt();
			Stack Value=new Stack();
			Stack top_value=new Stack();
			int[][] a= new int[N][3];
			for(int i=0;i<N;i++)
			{
				a[i][0]=s.nextInt();
				a[i][1]=s.nextInt();
				if(a[i][1]==1)
				{
					a[i][2]=s.nextInt();
				}
			}
			for(int i=0;i<N;i++)
			{
				if(a[i][1]==1)
				{
					if(Value.isEmpty())
					{
						Value.push(a[i][2]);
						top_value.push(0);
					}
					else if(a[i][2]>(int)Value.top()  || a[i][2]==(int)Value.top())
					{
						Value.push(a[i][2]);
						top_value.push(0);
					}
					else if(a[i][2]<(int)Value.top())
					{
						top_value.push((int)top_value.pop()+1);
					}
				}
				else if(a[i][1]==2)
				{
					if(Value.isEmpty() && top_value.isEmpty()) {System.out.println((i+1)+" "+-1);}
					else {System.out.println((i+1)+" "+top_value.pop());
					Value.pop();
					}
				}
			}
		}
		catch(FileNotFoundException e){
			System.out.println("Whatever you want");
		}
	}
}