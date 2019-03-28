import java.util.HashSet;
import java.util.Set;

public class AllSubStrings {

	public static void main(String[] args) {
		String str = "abcde";
		Set<String> set = new HashSet<String>();
		generateSubStrings(str,"",set);
		
		System.out.println();
	}
	
	public static void generateSubStrings(String str, String prefix, Set<String> set) {
		set.add(prefix);
		for (int i = 0; i < str.length(); i++) {
			generateSubStrings(str.substring(i + 1), prefix + str.substring(i, i + 1), set);
		}
	}
}
