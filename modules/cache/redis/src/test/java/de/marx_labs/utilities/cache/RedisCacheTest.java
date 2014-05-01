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
package de.marx_labs.utilities.cache;

import de.marx_labs.utilities.cache.RedisCache;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import de.marx_labs.utilities.configuration.Configuration;

public class RedisCacheTest {
	
	private static RedisCache cache;
	private static Configuration context = Configuration.newInstance();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		cache = new RedisCache(context);
		cache.init();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		cache.shutdown();
	}

	@Test
	public void test1() {

		long before = System.currentTimeMillis();

		cache.set("key_", "value_");
		cache.get("key_");

		long after = System.currentTimeMillis();
		System.out.println("test1 took: " + (after - before) + "ms");
	}

	@Test
	public void test10000() {

		long before = System.currentTimeMillis();

		for (int i = 0; i < 10000; i++) {
			cache.set("key_" + i, "value_" + i);
			cache.get("key_" + i);
		}

		long after = System.currentTimeMillis();
		System.out.println("test2 took: " + (after - before) + "ms");
	}
	
	@Test
	public void test100000() {

		long before = System.currentTimeMillis();

		for (int i = 0; i < 100000; i++) {
			cache.set("key_" + i, "value_" + i);
			cache.get("key_" + i);
		}

		long after = System.currentTimeMillis();
		System.out.println("test2 took: " + (after - before) + "ms");
	}

}
