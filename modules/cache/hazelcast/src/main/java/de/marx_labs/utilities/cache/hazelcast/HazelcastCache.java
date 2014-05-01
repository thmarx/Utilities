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
package de.marx_labs.utilities.cache.hazelcast;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import de.marx_labs.utilities.configuration.Configuration;
import de.marx_labs.utilities.cache.api.Cache;
import java.util.concurrent.TimeUnit;

public class HazelcastCache implements Cache {

	public static final String HAZELCAST_INSTANCENAME = "hazelcast.instancename";
    public static final String HAZELCAST_CACHE = "hazelcast.cache";
	public static final String REDIS_TIMETOLIFE = "redis.timeToLife";

	private final Configuration context;

	private int timeToLife = 2;

	private HazelcastInstance hzi = null;
    private IMap<String, String> cache = null;

	public HazelcastCache(final Configuration context) {
		this.context = context;

		this.timeToLife = this.context.get(REDIS_TIMETOLIFE,
                Integer.class, 2);
        
        hzi = Hazelcast.getHazelcastInstanceByName(this.context.getString(HAZELCAST_INSTANCENAME, "instance1"));
        if (hzi == null) {
            hzi = Hazelcast.newHazelcastInstance();
        }
	}

	@Override
	public void init() {
        cache = hzi.getMap(this.context.getString(HAZELCAST_CACHE, "cache"));
	}

	@Override
	public void shutdown() {
		// nothing to do
	}

	@Override
	public void set(String key, String value, int timeToLife) {
		cache.set(key, value, timeToLife, TimeUnit.MILLISECONDS);
	}

	@Override
	public void set(String key, String value) {
		set(key, value, timeToLife);
	}

	@Override
	public String get(String key) {
		return cache.get(key);
	}

	@Override
	public boolean contains(String key) {
		return cache.containsKey(key);
	}
}
