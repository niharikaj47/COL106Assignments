public class program
{
	public String test(String hex)
	{
		String binary="";
		for(int i=0;i<hex.length();i++)
        {
            char ch=hex.charAt(i);
            if(ch=='0') binary=binary+"0000";
            else if (ch=='1') binary=binary+"0001";
            else if (ch=='2') binary=binary+"0010";
            else if (ch=='3') binary=binary+"0011";
            else if (ch=='4') binary=binary+"0100";
            else if (ch=='5') binary=binary+"0101";
            else if (ch=='6') binary=binary+"0110";
            else if (ch=='7') binary=binary+"0111";
            else if (ch=='8') binary=binary+"1000";
            else if (ch=='9') binary=binary+"1001";
            else if (ch=='A') binary=binary+"1010";
            else if (ch=='B') binary=binary+"1011";
            else if (ch=='C') binary=binary+"1100";
            else if (ch=='D') binary=binary+"1101";
            else if (ch=='E') binary=binary+"1110";
            else if (ch=='F') binary=binary+"1111";
        }
        for(int i=0;i<3;i++)
            {
            char ch1=binary.charAt(0);
            if(ch1=='0'){
                String abc=binary.substring(1);
                binary=abc;
                }
            else break;
        }
        return binary;		
	}
}
