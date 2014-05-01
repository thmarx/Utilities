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
package de.marx_labs.utilities.cache.api;

public interface Cache {

	/**
	 * Initialisierungs
	 */
	public void init () throws Exception;
	/**
	 * Ggf. Aufräumen, Objekte freigeben und Datenbankverbindung beenden
	 */
	public void shutdown();
	
	/**
	 * Schreibt einen Wert in den Cache
	 * 
	 * @param key Der Key
	 * @param value Der Wert
	 * @param timeToLife Die Zeit nach der der Key aus dem Cache geworfen werden soll. In Sekunden
	 */
	public void set (String key, String value, int timeToLife);
	/**
	 * Schreibt einen Wert in den Cache
	 * Als timeToLife wird ein Default-Wert verwendet
	 * 
	 * @param key Der Key
	 * @param value Der Wert
	 */
	public void set (String key, String value);
	
	/**
	 * läd einen Wert aus dem Cache
	 * 
	 * @param key
	 * @return
	 */
	public String get (String key);
	
	public boolean contains (String key);
}
