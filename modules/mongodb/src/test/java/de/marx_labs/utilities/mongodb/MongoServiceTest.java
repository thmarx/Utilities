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

package de.marx_labs.utilities.mongodb;


import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.github.fakemongo.Fongo;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.QueryBuilder;

import de.marx_labs.utilities.configuration.Configuration;

public class MongoServiceTest {
	private static MongoDbService service = null;
	private static DBCollection collection = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Configuration context = Configuration.newInstance();

		Fongo fongo = new Fongo("mongo server 1");
		collection = fongo.getDB("tracking").getCollection(
				"test");

		MongoDbConfiguration configuration = new MongoDbConfiguration()
				.context(context).collection(collection);

		service = new MongoDbService();
		service.open(configuration);
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test_1() throws Exception {
		BasicDBObject o1 = new BasicDBObject();
		
		o1.put("name", "thorsten");
		o1.put("age", 25);
		service.add(o1);
		
		DBObject query = QueryBuilder.start("name").is("thorsten").get();
		DBObject o2 = service.get(query);
		assertThat((String)o2.get("name"), is("thorsten"));
		
		o2 = service.get("age", 25);
		assertThat((String)o2.get("name"), is("thorsten"));
		
	}

}
