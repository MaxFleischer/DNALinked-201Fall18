/**
 * 
 * @author maxfleischer
 *
 */
public class LinkStrand implements IDnaStrand{
	
	/**
	 * 
	 * @author maxfleischer
	 * private class used as the nodes in our linked lists
	 */
	private class Node {
	   	String info;
	   	Node next;
	   	
	   	/**
	   	 * Parameterized constructor, makes a new Node object with info 
	   	 * equal to the argument but no next field.
	   	 * @param s - String we want this node to hold
	   	 */
	   	public Node(String s) {
	      	info = s;
	      	next = null;
	   	}
	   }
	   private Node myFirst,myLast;
	   private long mySize;
	   private int myAppends;
	   
	   // These three are helpful in the efficient version of charAt, nowhere else
	   private int myIndex;
	   private int myLocalIndex;
	   private Node myCurrent;
	   
	   /**
	    * Default constructor, makes a new empty LinkStrand
	    */
	   public LinkStrand()	{
		   this("");
	   }
	   
	   /**
	    * Parameterized constructor, makes a new LinkStrand with the first
	    * node having some inputting String in its info field
	    * @param a - first Strand of DNA to be put in the LinkStrand
	    */
	   public LinkStrand(String a)	{
		   initialize(a);
	   }
	   
	   /**
	    * Method that gives values to all of the instance variables
	    * @param a- first Strand of DNA to be in the LinkStrand
	    */
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
	   
	   /**
	    * Adds a new Node of DNA to the LinkStrand
	    * @param a- Strand of DNA to be added.
	    */
	   @Override
	   public IDnaStrand append(String a)	{
		   this.myLast.next = new Node(a);
		   myLast = myLast.next;
		   myAppends++;
		   mySize += a.length();
		   return this;
	   }
	   
	   /**
	    * returns the number of additions to this LinkStrand
	    */
	   @Override
	   public int getAppendCount()	{
		   return myAppends;
	   }
	   
	   /**
	    * returns the total length of all the DNA in the LinkStrand
	    */
	   @Override
	   public long size()	{
		   return mySize;
	   }
	   
	   /**
	    * Returns a String of all the DNA in the LinkStrand
	    */
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
	   
	   /**
	    * Returns a new LinkStrand object that is the reverse of this one.
	    * All nodes are reversed, all codons in those nodes are reversed.
	    */
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
	   
	   /**
	    * Finds the codon at a specified index in the LinkStrand
	    * Initially, has efficiency O(N) on the first call for param n
	    * On subsequent calls has efficiency O(N1 - N2), where N1 is the argument
	    * 	on this call and N2 is the argument on the previous call
	    * 	IF AND ONLY IF N1>N2
	    * Otherwise, has efficiency N1.
	    * Throws indexOutOfBoundsException
	    * @param a- index of LinkStrand we want the character of
	    */
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
	   
	   /**
	    * Returns a new LinkStrand object with a specified initial DNA splice
	    * @param a- initial DNA splice
	    */
	   @Override
	   public IDnaStrand getInstance(String a)	{
		   return new LinkStrand(a);
	   }
}
