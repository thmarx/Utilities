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
package de.marx_labs.utilities.common.searchtree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentSkipListMap;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

public class MapSearchTree<T> extends ConcurrentSkipListMap<Long, T> implements
		SearchTree<T> {

	private int maxSize = 1;

	public MapSearchTree(int maxSize) {
		this.maxSize = maxSize;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.marx_labs.searchtree.SearchTree#put(java.lang.Long, T)
	 */
	@Override
	public T put(Long key, T value) {
		T result = super.put(key, value);
		if (size() > maxSize) {
			// remove(lastKey());
			remove(firstKey());
		}

		return result;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.marx_labs.searchtree.SearchTree#newest(int)
	 */
	@Override
	public Set<T> newest(int size) {
		// SortedMap<Long, T> matchMap = this.tailMap(lastKey(), true);

		Long key = lastKey();
		Set<T> matches = new LinkedHashSet<T>();
		for (int i = 0; i < size; i++) {
			matches.add(get(key));
			key = lowerKey(key);

			if (key == null) {
				break;
			}
		}

		List<T> reverseList = Lists.reverse(Lists.newArrayList(matches));

		return Sets.newLinkedHashSet(reverseList);
		// return matches;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.marx_labs.searchtree.SearchTree#findNew(java.lang.Long, int)
	 */
	@Override
	public List<T> findNew(Long term, int size) {
		SortedMap<Long, T> matchMap = new TreeMap<Long, T>();
		matchMap.putAll(this.tailMap(term, false));

		int count = 0;
		List<T> matches = new LinkedList<T>();
		for (T ci : matchMap.values()) {
			matches.add(ci);

			count++;
			if (count == size) {
				break;
			}
		}

		return matches;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.marx_labs.searchtree.SearchTree#findOld(java.lang.Long, int)
	 */
	@Override
	public Set<T> findOld(Long term, int size) {
		Set<T> matches = new LinkedHashSet<T>();
		Long key = lowerKey(term);
		for (int i = 0; i < size; i++) {
			matches.add(get(key));
			key = lowerKey(key);

			if (key == null) {
				break;
			}
		}
		List<T> reverseList = Lists.reverse(Lists.newArrayList(matches));

		return Sets.newLinkedHashSet(reverseList);
	}

}
