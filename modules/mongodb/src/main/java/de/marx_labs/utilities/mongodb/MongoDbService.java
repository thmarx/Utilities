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

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import de.marx_labs.utilities.utils.exception.ServiceException;

public class MongoDbService {
	private static final Logger LOGGER = LoggerFactory.getLogger(MongoDbService.class);

	private DBCollection collection;

	public MongoDbService open(MongoDbConfiguration configuration) {
		collection = configuration.collection();

		for (String indexField : configuration.indexFields()) {
			collection.ensureIndex(indexField);
		}
		
		return this;
	}

	public void close() {
		// nothing to do
	}

	public void add(DBObject element) throws ServiceException {
		collection.save(element);
	}
	
	public void update(DBObject query, DBObject element) throws ServiceException {
		collection.update(query, element);
	}
	
	public DBObject get (DBObject query) {
		return collection.findOne(query);
	}
	
	public DBObject get (String name, Object value) {
		DBObject query = new BasicDBObject();
		query.put(name, value);
		return collection.findOne(query);
	}
	
	public List<DBObject> list(DBObject query) throws ServiceException {
		DBCursor cur = collection.find(query);
		List<DBObject> resultCollection = new ArrayList<DBObject>();
		try {
			while (cur.hasNext()) {
				resultCollection.add(cur.next());
			}
		} catch (Exception e) {
			LOGGER.error("", e);
		}

		return resultCollection;
	}

	
	public long count(DBObject query) {
		return collection.count(query);
	}

	
	public void delete(DBObject query) {
		collection.remove(query);
	}

	
	public void clear(DBObject query) {
		collection.remove(query);
	}
}
