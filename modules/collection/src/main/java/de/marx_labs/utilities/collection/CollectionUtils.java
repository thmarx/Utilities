/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.marx_labs.utilities.collection;

import de.marx_labs.utilities.collection.filter.FilterChain;
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
}
