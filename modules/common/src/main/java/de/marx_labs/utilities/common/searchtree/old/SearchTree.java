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

public class SearchTree<T> extends ConcurrentSkipListMap<Long, T> {

    private int maxSize = 1;

    public SearchTree(int maxSize) {
        super(new LongComparator());
//        super();
        this.maxSize = maxSize;
    }

    @Override
    public T put(Long key, T value) {
        T result = super.put(key, value);
        if (size() > maxSize) {
            remove(lastKey());
        }

        return result;
    }

    public Set<T> newest(int size) {
//        SortedMap<Long, T> matchMap = new TreeMap<Long, T>();
//        matchMap.putAll(this.headMap(firstKey()));

        SortedMap<Long, T> matchMap = this.tailMap(firstKey(), true);


        int count = 0;
        Set<T> matches = new LinkedHashSet<T>();
        for (T ci : matchMap.values()) {
            matches.add(ci);

            count++;
            if (count == size) {
                break;
            }
        }

        return matches;
    }

    public List<T> findNew(Long term, int size) {
        SortedMap<Long, T> matchMap = new TreeMap<Long, T>();
        matchMap.putAll(this.headMap(term));
//        SortedMap<Long, T> matchMap = this.headMap(term);


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

    public Set<T> findOld(Long term, int size) {
//        SortedMap<Long, T> matchMap = new TreeMap<Long, T>();
        SortedMap<Long, T> matchMap = this.tailMap(term, false);
//        matchMap.putAll(this.headMap(term));
//        matchMap.putAll(this.tailMap(term));

        int count = 0;
        Set<T> matches = new LinkedHashSet<T>();
        for (T ci : matchMap.values()) {
            matches.add(ci);

            count++;
            if (count == size) {
                break;
            }
        }

        return matches;
    }

}
