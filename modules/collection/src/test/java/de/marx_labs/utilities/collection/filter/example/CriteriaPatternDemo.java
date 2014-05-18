/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.marx_labs.utilities.collection.filter.example;

import de.marx_labs.utilities.collection.CollectionUtils;
import de.marx_labs.utilities.collection.filter.FilterChain;
import de.marx_labs.utilities.collection.filter.criteria.AndCriteria;
import de.marx_labs.utilities.collection.filter.criteria.Criteria;
import de.marx_labs.utilities.collection.filter.criteria.OrCriteria;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author marx
 */
public class CriteriaPatternDemo {

    public static void main(String[] args) throws Exception {
        List<Person> persons = new ArrayList<>();

        persons.add(new Person("Robert", "Male", "Single"));
        persons.add(new Person("John", "Male", "Married"));
        persons.add(new Person("Laura", "Female", "Married"));
        persons.add(new Person("Diana", "Female", "Single"));
        persons.add(new Person("Mike", "Male", "Single"));
        persons.add(new Person("Bobby", "Male", "Single"));

        Criteria<Person> male = new CriteriaMale();
        Criteria<Person> female = new CriteriaFemale();
        Criteria<Person> single = new CriteriaSingle();
        Criteria<Person> singleMale = new AndCriteria<>(single, male);
        Criteria<Person> singleOrFemale = new OrCriteria<>(single, female);

        FilterChain<Person> filterChain = CollectionUtils.filterChain(Person.class);
        filterChain.addCriteria(male);
        System.out.println("Males: ");
        printPersons(filterChain.filter(persons));

        filterChain = CollectionUtils.filterChain(Person.class);
        filterChain.addCriteria(female);
        System.out.println("\nFemales: ");
        printPersons(filterChain.filter(persons));

        filterChain = CollectionUtils.filterChain(Person.class);
        filterChain.addCriteria(singleMale);
        System.out.println("\nSingle Males: ");
        printPersons(filterChain.filter(persons));

        filterChain = CollectionUtils.filterChain(Person.class);
        filterChain.addCriteria(singleOrFemale);
        System.out.println("\nSingle Or Females: ");
        printPersons(filterChain.filter(persons));
        System.out.println("\nSingle Or Females (parallel): ");
        printPersons(filterChain.filterParallel(persons));
    }

    public static void printPersons(Collection<Person> persons) {
        for (Person person : persons) {
            System.out.println("Person : [ Name : " + person.getName()
                    + ", Gender : " + person.getGender()
                    + ", Marital Status : " + person.getMaritalStatus()
                    + " ]");
        }
    }
}
