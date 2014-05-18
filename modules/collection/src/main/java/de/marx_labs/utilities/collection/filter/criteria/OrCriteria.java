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
public class OrCriteria<T> implements Criteria<T> {

   private final Criteria<T> criteria;
   private final Criteria<T> otherCriteria;

   public OrCriteria(Criteria<T> criteria, Criteria<T> otherCriteria) {
      this.criteria = criteria;
      this.otherCriteria = otherCriteria; 
   }

   @Override
   public boolean meetCriteria(T object) {
      boolean firstCriteriaItems = criteria.meetCriteria(object);
      
      return firstCriteriaItems || otherCriteria.meetCriteria(object);
   }
}
