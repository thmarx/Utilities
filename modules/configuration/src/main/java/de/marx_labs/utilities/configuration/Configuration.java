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
package de.marx_labs.utilities.configuration;

import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import de.marx_labs.utilities.configuration.properties.Properties2;
import java.io.File;
import java.net.URL;

public class Configuration extends HashMap<String, Object> {

	private static final Logger logger = LoggerFactory.getLogger(Configuration.class);

	public static Configuration newInstance(){
		return new Configuration();
	}
	
	public static Configuration newInstance(URL configfile) throws IOException {
		
		Configuration context = new Configuration();
		
		if (configfile != null) {
			Properties properties = Properties2.loadProperties(configfile);

			for (final String name : properties.stringPropertyNames()) {
				context.put(name, properties.getProperty(name));
			}
		}

		return context;
	}
	
	public static Configuration newInstance(String configfile) throws IOException {
		return newInstance(new File(configfile).toURI().toURL());
//		Configuration context = new Configuration();
//		
//		if (configfile != null) {
//			Properties properties = Properties2.loadProperties(configfile);
//
//			for (final String name : properties.stringPropertyNames()) {
//				context.put(name, properties.getProperty(name));
//			}
//		}
//
//		return context;
	}

	private Configuration() {
		super();
	}

	public String getString(String key, String defaultValue) {
		return get(key, String.class, defaultValue);
	}
	public String getString(String key) {
		return get(key, String.class, null);
	}
	public int getInteger(String key, int defaultValue) {
		return get(key, Integer.class, defaultValue);
	}
	public int getInteger(String key) {
		return get(key, Integer.class, null);
	}
	public boolean getBoolean(String key, boolean defaultValue) {
		return get(key, Boolean.class, defaultValue);
	}
	public boolean getBoolean(String key) {
		return get(key, Boolean.class, null);
	}
	public long getLong(String key, long defaultValue) {
		return get(key, Long.class, defaultValue);
	}
	public long getLong(String key) {
		return get(key, Long.class, null);
	}
	
	public <T> T get(String key, Class<T> to, T defaultValue) {
		Object value = get(key);

		if (value == null) {
			return defaultValue;
		}

		if (to.isAssignableFrom(value.getClass())) {
			return to.cast(value);
		} else {
			// try to convert value
			try {
				if (value instanceof String) {
					String svalue = (String)value;
					if (to.equals(Boolean.class)) {
						return to.cast(Boolean.valueOf(svalue));
					} else if (to.equals(Integer.class)) {
						return to.cast(Integer.valueOf(svalue));
					} else if (to.equals(Long.class)) {
						return to.cast(Long.valueOf(svalue));
					} else if (to.equals(Float.class)) {
						return to.cast(Float.valueOf(svalue));
					} else if (to.equals(Double.class)) {
						return to.cast(Double.valueOf(svalue));
					} else if (to.equals(Short.class)) {
						return to.cast(Short.valueOf(svalue));
					}
				}
				
			} catch (Exception e) {
				logger.error("trying to convert value: " + value + " to " + to.toString(), e);
			}
		}

		return defaultValue;
	}
}
