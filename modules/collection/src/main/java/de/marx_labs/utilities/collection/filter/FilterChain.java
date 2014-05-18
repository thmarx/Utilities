/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.marx_labs.utilities.collection.filter;

import de.marx_labs.utilities.collection.CollectionUtils;
import de.marx_labs.utilities.collection.filter.criteria.Criteria;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.RecursiveAction;

/**
 *
 * @author marx
 * @param <T>
 */
public class FilterChain<T> {

    private final List<Criteria<T>> criterias = new ArrayList<>();

    public static <B> FilterChain<B> filterChain(Class<B> clazz) {
        return new FilterChain<>();
    }

    private FilterChain() {
    }

    public FilterChain<T> addCriteria(Criteria<T> criteria) {
        if (criteria != null) {
            criterias.add(criteria);
        }

        return this;
    }

    public FilterChain<T> addCriterias(Criteria<T>... criteria) {
        criterias.addAll(criterias);
        return this;
    }

    public List<T> filter(List<T> objects) {
        List<T> result = new ArrayList<>();

        for (T object : objects) {
            for (Criteria<T> criteria : criterias) {
                if (!criteria.meetCriteria(object)) {
                    break;
                }
                result.add(object);
            }
        }

        return result;
    }
    
    public List<T> filterParallel (List<T> objects) throws InterruptedException, ExecutionException {
        List<T> result = new ArrayList<>();
        
        FilterTask<T> rootTask = new FilterTask<>(objects, result, 0, objects.size(), 1000, this);
        CollectionUtils.pool.invoke(rootTask);
        rootTask.join();
        return result;
    }

    private static class FilterTask<T> extends RecursiveAction {

        final List<T> objects;
        final List<T> result;
        final int threshold;
        final int start;
        final int end;
        final FilterChain<T> filterChain;

        private FilterTask(List<T> objects, List<T> result, int start, int end, int threshold, FilterChain<T> filterChain) {
            this.objects = objects;
            this.result = result;
            this.threshold = threshold;
            this.start = start;
            this.end = end;
            this.filterChain = filterChain;
        }

        @Override
        protected void compute() {

            if (end - start <= threshold) {
                result.addAll(filterChain.filter(objects.subList(start, end)));
                return;
            }

            int mid = start + (end - start) / 2;
            
            FilterTask<T> task1 = new FilterTask<>(objects, result, start, mid, threshold, filterChain);
            FilterTask<T> task2 = new FilterTask<>(objects, result, mid, end, threshold, filterChain);
            invokeAll(
                    task1,
                    task2
            );
            task1.join();
            task2.join();
        }

    }
}
