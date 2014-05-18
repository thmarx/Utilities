/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.marx_labs.utilities.collection.filter.example;

import de.marx_labs.utilities.collection.filter.criteria.Criteria;

/**
 *
 * @author marx
 */
public class CriteriaFemale implements Criteria<Person> {

    @Override
    public boolean meetCriteria(Person person) {
        return person.getGender().equalsIgnoreCase("FEMALE");
    }
}
