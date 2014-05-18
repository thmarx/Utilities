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
public class AndCriteria<T> implements Criteria<T> {

   private final Criteria<T> criteria;
   private final Criteria<T> otherCriteria;

   public AndCriteria(Criteria<T> criteria, Criteria<T> otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }

   @Override
   public boolean meetCriteria(T object) {
      boolean result = criteria.meetCriteria(object);		
      return result && otherCriteria.meetCriteria(object);
   }
}
