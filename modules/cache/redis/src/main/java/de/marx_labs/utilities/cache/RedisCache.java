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

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;
import de.marx_labs.utilities.configuration.Configuration;
import de.marx_labs.utilities.cache.api.Cache;

public class RedisCache implements Cache {

	public static final String REDIS_HOST = "redis.host";
	public static final String REDIS_PORT = "redis.port";
	public static final String REDIS_TIMETOLIFE = "redis.timeToLife";

	private Configuration context;

	private int timeToLife = 2;

	private JedisPool pool;

	public RedisCache(Configuration context) {
		this.context = context;

		this.timeToLife = Integer.valueOf(this.context.get(REDIS_TIMETOLIFE,
				Integer.class, 2));
	}

	@Override
	public void init() {

		pool = new JedisPool(new JedisPoolConfig(), this.context.get(RedisCache.REDIS_HOST,
				String.class, "localhost"), Integer.valueOf(this.context.get(
				RedisCache.REDIS_PORT, Integer.class, 6379)));
	}

	@Override
	public void shutdown() {
		if (pool != null) {
			pool.destroy();
		}
	}

	@Override
	public void set(String key, String value, int timeToLife) {
		Jedis jedis = pool.getResource();
		try {
			jedis.set(key, value, "NX", "EX", timeToLife); //$NON-NLS-1$ //$NON-NLS-2$
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public void set(String key, String value) {
		set(key, value, timeToLife);
	}

	@Override
	public String get(String key) {
		Jedis jedis = pool.getResource();
		try {
			return jedis.get(key);
		} finally {
			pool.returnResource(jedis);
		}
	}

	@Override
	public boolean contains(String key) {
		return get(key) != null;
	}
}
