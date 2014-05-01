/**
 * Mad-Advertisement
 * Copyright (C) 2011-2013 Thorsten Marx <thmarx@gmx.net>
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * 	http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package de.marx_labs.utilities.common.searchtree.old;

import java.util.List;
import java.util.Set;

public class SearchTreeTest {
	public static void main(String[] args) throws Exception {
		SearchTree<String> tree = new SearchTree<String>(10);

		// tree.put(1L, "eins"); // alt
		// tree.put(2L, "zwei");
		// tree.put(3L, "drei");
		// tree.put(4L, "vier");
		// tree.put(5L, "fünf");
		// tree.put(6L, "sechs");
		// tree.put(7L, "sieben");
		// tree.put(8L, "acht");
		// tree.put(9L, "neun");
		// tree.put(10L, "zehn"); // neu

		tree.put(35291919986720768L, "eins");
		tree.put(35298233173409793L, "zwei");
		tree.put(35314053391851520L, "drei");
		tree.put(35314053391851521L, "vier");

		System.out.println("newest");
		Set<String> result0 = tree.newest(1);
		printResult(result0);
		System.out.println();

		System.out.println("new");
		List<String> result = tree.findNew(35314053391851519L, 3);
		printResult(result);
		System.out.println();

		System.out.println("old");
		Set<String> result2 = tree.findOld(35314053391851519L, 3);
		printResult(result2);
		System.out.println();
	}

	private static void printResult(Iterable<String> result) {
		for (String st : result) {
			System.out.println(st);
		}
	}
}