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
import java.util.concurrent.ConcurrentNavigableMap;
import java.util.concurrent.ConcurrentSkipListMap;

import org.mapdb.DBMaker;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class MapDBSearchTree<T>
		/* extends ConcurrentSkipListMap<Long, T> */implements SearchTree<T> {

	private int maxSize = 1;

	ConcurrentNavigableMap<Long, T> treeMap = DBMaker.newTempTreeMap();

	public MapDBSearchTree(int maxSize) {
		this.maxSize = maxSize;
	}

	@Override
	public T put(Long key, T value) {
		T result = treeMap.put(key, value);
		if (treeMap.size() > maxSize) {
			treeMap.remove(treeMap.firstKey());
		}

		return result;
	}

	@Override
	public Set<T> newest(int size) {
		Long key = treeMap.lastKey();
		Set<T> matches = new LinkedHashSet<T>();
		for (int i = 0; i < size; i++) {
			matches.add(treeMap.get(key));
			key = treeMap.lowerKey(key);

			if (key == null) {
				break;
			}
		}

		List<T> reverseList = Lists.reverse(Lists.newArrayList(matches));

		return Sets.newLinkedHashSet(reverseList);
	}

	@Override
	public List<T> findNew(Long term, int size) {
		SortedMap<Long, T> matchMap = new TreeMap<Long, T>();

		matchMap.putAll(treeMap.tailMap(term, false));

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

	@Override
	public Set<T> findOld(Long term, int size) {
		Set<T> matches = new LinkedHashSet<T>();
		Long key = treeMap.lowerKey(term);
		for (int i = 0; i < size; i++) {
			matches.add(treeMap.get(key));
			key = treeMap.lowerKey(key);

			if (key == null) {
				break;
			}
		}
		List<T> reverseList = Lists.reverse(Lists.newArrayList(matches));

		return Sets.newLinkedHashSet(reverseList);
	}

}
