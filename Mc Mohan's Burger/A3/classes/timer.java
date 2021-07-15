    public void timeupdate(int t)
    {
      for(int i=t_i;i<=t;i++)
      {
        boolean flag=false;
        while(!g_s.isEmpty() && flag==false)
        {
          if(g_s.peek().ot==t) {                          //Define a new node for griddle which stores the ot and numof burgers 
            griddlespace=griddlespace+ g_s.peek().num;
            g_s.remove();
          }
          else flag=true;
        }
        for(int j=k;j>=1;j--){
          if(counters[i].peek().tout==i){     // tout if from node of tree
            Node w=counters[i].peek();          //Update the heap when cutomer leaves the queue 
            Node2 abc =new Node1(c_id,) ;     //Create a new node for adding to griddle wait with the attributes ofpeek
            g_s.add(abc);
            counters[i].remove();             //The customer is removed from the counter
          }
        }
        boolean flag2=0;
        while(!g_w.isEmpty() && flag2==0)
        {
          Node1 abc=g_w.peek();
          if(abc.num2> griddlespace){
            abc.num2=abc.num2-griddlespace;
            abc.out_time=  // I will check this  in the end 
            Node2 xyz=new Node2(ot,num);
            griddlespace=0;
            flag2=1;
          }
          else if(abc.num2==griddlespace)
          {
            abc.num2=0
            abc.tout2=t+10+1;
            // Now insert this time in the AVL tree and IDK how to do that 
            g_w.remove();
            Node2 xyz=new Node2(ot,num)       //ot is out time from griddle  which is 10+current time 
            g_s.add(xyz);
            griddlespace=0;
            flag2=1;
          }
          else if(abc.num2<griddlespace)
          {
            griddlespace=griddlespace- abc.num2;
            abc.tout2=t+10+1;            //Insert this time in AVL tree
            g_w.remove();
            Node2 xyz= new Node2(ot,num);    //ot is out time from griddle which is 10+ current time 
            g_s.add(xyz);
            flag2=0;
          }
        }
      }
    }
