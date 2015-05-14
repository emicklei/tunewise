/**
 * Licensed Material - Property of PhilemonWorks B.V.
 * 
 * (c) Copyright PhilemonWorks 2005 - All rights reserved.
 * Use, duplication, distribution or disclosure restricted. 
 * See http://www.philemonworks.com for information.
 */
package tunewise;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * Utils is
 * 
 * @author E.M.Micklei
 */
public class Utils {
	static Comparator CaseInsensitiveComparator = new Comparator(){
		public int compare(Object o1, Object o2) {
			String s1 = (String)o1;
			String s2 = (String)o2;
			return s1.compareToIgnoreCase(s2);
		};
	};
	public static List asSorted(Set aSet) {
		List sorted = new ArrayList(aSet.size());
		for (Iterator iter = aSet.iterator(); iter.hasNext();) {
			String element = (String) iter.next();
			sorted.add(element);
		}
		Collections.sort(sorted,CaseInsensitiveComparator);
		return sorted;
	}
}
