import java.util.*;

public class CodonProfiler {
	
	/**
	 * Count how many times each codon in an array of codons occurs
	 * in a strand of DNA. Return int[] such that int[k] is number
	 * of occurrences of codons[k] in strand. Strand codons can start
	 * at all valid indexes that are multiples of 3: 0, 3, 6, 9, 12, ...
	 * @param strand is DNA to be analyzed for codon occurrences.
	 * @param codons is an array of strings, each has three characters
	 * @return int[] such that int[k] is number of occurrences of codons[k] in 
	 * strand. 
	 */
	public int[] getCodonProfile(IDnaStrand strand, String[] codons) {
		HashMap<String,Integer> map = new HashMap<>();
		int[] ret = new int[codons.length];
		
		Iterator<Character> iter = strand.iterator();
		while(iter.hasNext())	{
			char a = iter.next();
			char b = 'z';           // not part of any real codon
			char c = 'z';
			if (iter.hasNext()) {
				b = iter.next();
			}
			if (iter.hasNext()) {
				c = iter.next();
			}
			String cod = "" + a + b + c;
			if(map.keySet().contains(cod))	{
				map.put(cod, map.get(cod) + 1);
			}
			else	{
				map.put(cod, 1);
			}
		}
		
		for(int i = 0; i < codons.length; i++)	{
			if(map.keySet().contains(codons[i]))	{
				ret[i] = map.get(codons[i]);
			}
			else 	{
				ret[i] = 0;
			}
		}
/**
 * This is the inefficient version. Commented out because it is not 
 * code we want to run, but it is still helpful to look at. 
 * Much of the effecient version is adapted from this version.				
 */
//		for(int k=0; k < codons.length; k++) {
//			Iterator<Character> iter = strand.iterator();
//			while (iter.hasNext()) {
//				char a = iter.next();
//				char b = 'z';           // not part of any real codon
//				char c = 'z';
//				if (iter.hasNext()) {
//					b = iter.next();
//				}
//				if (iter.hasNext()) {
//					c = iter.next();
//				}
//				String cod = ""+a+b+c;
//				if (cod.equals(codons[k])) {
//					ret[k] += 1;
//				}
//			}
//		}
		return ret;
	}
}
