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

import com.mongodb.DBCollection;

import de.marx_labs.utilities.configuration.Configuration;

public class MongoDbConfiguration {

	private Configuration context;
	
	private DBCollection collection;
	
	private List<String> indexFields = new ArrayList<String>();
	
	public MongoDbConfiguration () {
	}

	
	public Configuration context() {
		return context;
	}



	public MongoDbConfiguration context(Configuration context) {
		this.context = context;
		return this;
	}



	public List<String> indexFields() {
		return indexFields;
	}

	public MongoDbConfiguration indexFields(List<String> indexFields) {
		this.indexFields = indexFields;
		return this;
	}

	public MongoDbConfiguration indexField (String field) {
		indexFields.add(field);
		return this;
	}


	public DBCollection collection() {
		return collection;
	}

	public MongoDbConfiguration collection(DBCollection collection) {
		this.collection = collection;
		return this;
	}
	
}
