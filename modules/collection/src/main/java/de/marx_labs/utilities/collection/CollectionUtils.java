/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.marx_labs.utilities.collection;

import de.marx_labs.utilities.collection.filter.FilterChain;
import de.marx_labs.utilities.collection.sort.MergeSortForkJoin;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 *
 * @author marx
 */
public abstract class CollectionUtils {
    
    public static final ForkJoinPool pool = new ForkJoinPool(Runtime.getRuntime().availableProcessors());
    
    public static <B> FilterChain<B> filterChain(Class<B> clazz) {
        return FilterChain.filterChain(clazz);
    }
	
	public static <T extends Comparable<? super T>> List<T> sort(List<T> a) {
		T[] array = (T[])Array.newInstance(a.get(0).getClass() , a.size());
		array = (T[]) a.toArray(array);
		MergeSortForkJoin.sort(array, pool);
		
		return Arrays.asList(array);
	}
	
	public static <T extends Comparable<? super T>> void sort2(List<T> a) {
		T[] array = (T[])Array.newInstance(a.get(0).getClass() , a.size());
		array = (T[]) a.toArray(array);
		MergeSortForkJoin.sort(array, pool);
		
		a.clear();
		Collections.addAll(a, array);
	}
}
