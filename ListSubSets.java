import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
With this class you can recursively generate a Set<List<Integer>> with all sublists of a given List object
*/
public class ListSubSets {

	public static void main(String[] args) {
		//example of 
		List<Integer> list = new ArrayList<Integer>();
		list.add(2); list.add(3); list.add(12); 
		List<Integer> prefix = new ArrayList<Integer>();  //this object is used for the recursion
		Set<List<Integer>> set = new HashSet<List<Integer>>();
		
		generateSublists(list,prefix,set);
		System.out.println();
	}
	
//	public static void wordComb(String str, String prefix, Set<String> set) {
//		set.add(prefix);
//		for (int i = 0; i < str.length(); i++) {
//			wordComb(str.substring(i + 1), prefix + str.substring(i, i + 1), set);
//		}
//	}
	
	public static void generateSublists(List<Integer> array, List<Integer> prefix, Set<List<Integer>> set)
	{
		set.add(prefix);
		//bereinige(prefix);
		for (int i = 0; i < array.size(); i++) {
			List<Integer> array2 = array.subList(i+1, array.size());
			List<Integer> prefix2 = new ArrayList<Integer>();
			prefix2.addAll(prefix);
			prefix2.add(array.get(i));
			generateSublists(array2,prefix2,set);
		}
	}

	private static void bereinige(ArrayList<Integer> prefix) {
		HashSet<Integer> set = new HashSet<Integer>();
		int i = prefix.size();
		for (; i > 0; i--) {
			set.add(prefix.get(0));
			prefix.remove(0);
		}
		Iterator it = set.iterator();
		while(it.hasNext())
		{
			prefix.add((Integer) it.next());
		}
		
	}

}
