/*
 * Copyright 2014 marx.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.marx_labs.utilities.configuration;

import com.google.inject.Injector;
import java.util.Properties;

/**
 *
 * @author marx
 */
abstract public class BaseRuntimeContext {
	
	protected static Properties properties = new Properties();
	
	protected static Environment environment;

	protected static Injector injector;
	
	protected static Configuration context;
	
	public static Injector injector() {
		return injector;
	}
	public static void injector(Injector injector) {
		BaseRuntimeContext.injector = injector;
	}
	public static Environment environment() {
		return environment;
	}
	public static void environment(Environment enviroment) {
		BaseRuntimeContext.environment = enviroment;
	}

	public static void properties (Properties props) {
		properties = props;
	}
	public static Properties properties () {
		return properties;
	}
	public static void context(Configuration context) {
		BaseRuntimeContext.context = context;
	}
	public static Configuration context () {
		return context;
	}
}
