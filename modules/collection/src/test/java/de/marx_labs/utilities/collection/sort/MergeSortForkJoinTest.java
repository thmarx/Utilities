/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package de.marx_labs.utilities.collection.sort;

import java.util.Arrays;
import java.util.Collections;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author marx
 */
public class MergeSortForkJoinTest {
	
	public MergeSortForkJoinTest() {
	}
	
	@BeforeClass
	public static void setUpClass() {
	}
	
	@AfterClass
	public static void tearDownClass() {
	}
	
	@Before
	public void setUp() {
	}
	
	@After
	public void tearDown() {
	}

	/**
	 * Test of sort method, of class MergeSortForkJoin.
	 */
	@Test
	public void testSort() {
		System.out.println("sort");
		Integer[] a = new Integer[3];
		a[0] = 4;
		a[1] = 1;
		a[2] = 8;
		
		MergeSortForkJoin.sort(a);
		
		System.out.println(a[0] + " " + a[1] + " " + a[2]);
	}
	
}
