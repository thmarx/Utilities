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
package de.marx_labs.utilities.common.searchtree;

import java.util.List;
import java.util.Set;

public interface SearchTree<T> {

	public abstract T put(Long key, T value);

	public abstract Set<T> newest(int size);

	/**
	 * findet neuer Einträge, also mit einerm größeren Term-Wert
	 * @param term
	 * @param size
	 * @return
	 */
	public abstract List<T> findNew(Long term, int size);

	public abstract Set<T> findOld(Long term, int size);

}