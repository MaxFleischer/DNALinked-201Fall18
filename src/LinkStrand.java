
public class LinkStrand implements IDnaStrand{
	
	private class Node {
	   	String info;
	   	Node next;
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	   }
	   private Node myFirst,myLast;
	   private long mySize;
	   private int myAppends;
	   
	   private int myIndex;
	   private int myLocalIndex;
	   private Node myCurrent;
	   
	   public LinkStrand()	{
		   this("");
	   }
	   
	   public LinkStrand(String a)	{
		   initialize(a);
	   }
	   
	   @Override
	   public void initialize(String a)	{
		   Node next = new Node(a);
		   myFirst = next;
		   myLast = next;
		   mySize = a.length();
		   myAppends = 0;
		   myIndex = 0;
		   myLocalIndex = 0;
		   myCurrent = myFirst;
	   }
	   
	   @Override
	   public IDnaStrand append(String a)	{
		   this.myLast.next = new Node(a);
		   myLast = myLast.next;
		   myAppends++;
		   mySize += a.length();
		   return this;
	   }
	   
	   @Override
	   public int getAppendCount()	{
		   return myAppends;
	   }
	   
	   @Override
	   public long size()	{
		   return mySize;
	   }
	   
	   @Override
	   public String toString()	{
		   StringBuilder buf = new StringBuilder();
		   Node working = myFirst;
		   while(working != null)	{
			   buf.append(working.info);
			   working = working.next;
		   }
		   return buf.toString();
	   }
	   
	   @Override
	   public IDnaStrand reverse()	{
		   if(myFirst == null)	{
			   return this;
		   }
		   
		   LinkStrand ret = new LinkStrand(myFirst.info);
		   Node working = myFirst;
		   Node prev = ret.myLast;
		   
		   while(working.next != null)	{
			   Node next = new Node(working.next.info);
			   next.next = prev;
			   prev = next;
			   ret.myFirst = next;
			   working = working.next;
		   }
		   
		   Node first = ret.myFirst;
		   		   
		   while(first != null)	{
			   StringBuilder copy = new StringBuilder(first.info);
			   first.info = copy.reverse().toString();
			   first = first.next;
		   }
		   
		   LinkStrand ret2 = new LinkStrand(ret.myFirst.info);
		   Node link = ret.myFirst.next;
		   while(link != null)	{
			   ret2.append(link.info);
			   link = link.next;
		   }
		   
		   return ret2;
	   }
	   
	   @Override
	   public char charAt(int index)	{
		   if(index < 0 || index > mySize)	{
			   throw new IndexOutOfBoundsException();
		   }
		   if(index < myIndex)	{
			   myIndex = 0;
			   myLocalIndex = 0;
			   myCurrent = myFirst;
		   }
		   while(myIndex != index)	{
			   myIndex++;
			   myLocalIndex++;
			   if (myLocalIndex >= myCurrent.info.length()) {
					myLocalIndex = 0;
					myCurrent = myCurrent.next;
					if(myCurrent == null)	{
						   throw new IndexOutOfBoundsException();
					}
			   }
			}
	       return myCurrent.info.charAt(myLocalIndex);
		   }
	   
	   @Override
	   public IDnaStrand getInstance(String a)	{
		   return new LinkStrand(a);
	   }
}
