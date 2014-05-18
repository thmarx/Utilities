/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.marx_labs.utilities.collection.filter.criteria;

/**
 *
 * @author marx
 * @param <T>
 */
public interface Criteria<T> {
   public boolean meetCriteria(T object);
}
