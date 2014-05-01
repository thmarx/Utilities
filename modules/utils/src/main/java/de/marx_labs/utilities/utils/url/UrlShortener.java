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
package de.marx_labs.utilities.utils.url;

import java.util.Random;

public class UrlShortener {
	
	private static final int DEFAULT_SIZE = 6;
	
	private static final Random random = new Random(System.nanoTime());
	
	private static final String[] elements = { "a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n",
			"o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z", "1", "2", "3", "4", "5", "6", "7", "8", "9",
			"0", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T",
			"U", "V", "W", "X", "Y", "Z" };

	public static String encode(int num) {
		String tempVal = num == 0 ? "0" : "";
		long mod = 0;
		int base = elements.length;

		while (num != 0) {
			mod = (num % base);
			tempVal = elements[(int) mod] + tempVal;
			num = num / base;
		}

		return tempVal;
	}
	
	public static String unique (int size) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(elements[random.nextInt(62)]);
		}
		return sb.toString();
	}
	
	public static String unique () {
		return unique(DEFAULT_SIZE);
	}
}
