import java.util.*;
import java.io.*;


public class heapss
{
    private node_heap[] arr;
    protected int capacity=1;
    public int size=0;
    public int start=1;
    public int end=2;
    
    public heapss()
    {
        arr=new node_heap[capacity];
    }

    public void checkgrow() 
    {
        if(size==capacity){
            node_heap[] arr2=new node_heap[2*capacity];
            for(int i=1;i<=size;i++) arr2[i]=arr[i];
            capacity=capacity*2;
            arr=arr2;
            start=1;
            end=size+1;
        }
    }


    public class node_heap
    {
        private Queue<Node> abc;
        private int qlength;
        private int qnum;

        node_heap(int k,Queue<Node> idc)
        {
            abc=idc; 
            qlength=abc.size();
            qnum=k;
        }
    }

    public int size(){
        return this.size;
    }

    
    public void percolatedown(int i,node_heap x)
    {
        int n=this.size;
        if(2*i>n) arr[i]=x;
        else if(2*i==n){
            if(arr[2*i].qlength < x.qlength) arr[i]=x;
            else if (arr[2*i].qlength == x.qlength)
            {
                if(x.qnum > arr[2*i].qnum){
                    arr[i]=arr[2*i];
                    arr[2*i]=x;
                }
                else arr[i]=x;
            }
        }
        else if(2*i >n)
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


    public void percolateup(int i, node_heap x){

        if(arr[i/2].qlength>x.qlength && i/2>0)
        {
            arr[i]=arr[i/2];
            arr[i/2]=x;
            percolateup(i/2,x);
        }
        else if(arr[i/2].qlength==x.qlength && i/2>0){
            if(arr[i].qnum< arr[i/2].qnum){
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
        for(int i=k/2;i>=0;i--){
            percolatedown(i,arr[i]);
        }
    }
    

    public void insertheap(node_heap x)
    {
        this.checkgrow();
        int k=this.size;
        arr[k+1]=x;
        percolateup(k,x);
        size=size+1;
    }

    public void deletemin(){
        int k=this.size;
        node_heap temp=arr[start];
        arr[start]=arr[end];
        arr[end]=null;
        size=size-1;
        percolatedown(1,arr[start]);
        insertheap(temp);
    }

    public node_heap q_min(){
        return arr[1];
    }
}