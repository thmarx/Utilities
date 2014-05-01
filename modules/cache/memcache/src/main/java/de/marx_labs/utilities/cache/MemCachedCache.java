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

import net.spy.memcached.AddrUtil;
import net.spy.memcached.MemcachedClient;
import de.marx_labs.utilities.configuration.Configuration;
import de.marx_labs.utilities.cache.api.Cache;

public class MemCachedCache implements Cache {

	private Configuration context;

	private int timeToLife = 2;
	
	private MemcachedClient client;

	public MemCachedCache(Configuration context) {
		this.context = context;
		
		this.timeToLife = Integer.valueOf(this.context.get("memcache.timeToLife", Integer.class, 2));
		
		
	}

	@Override
	public void init() throws Exception {
		client = new MemcachedClient(AddrUtil.getAddresses(context.get("memcache.address", String.class, "localhost:11211")));
	}

	@Override
	public void shutdown() {
		client.shutdown();
	}

	@Override
	public void set(String key, String value, int timeToLife) {
		client.set(key, timeToLife, value);
	}

	@Override
	public void set(String key, String value) {
		set(key, value, timeToLife);
	}

	@Override
	public String get(String key) {
		return (String) client.get(key);
	}

	@Override
	public boolean contains(String key) {
		return get(key) != null;
	}

}
