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

import de.marx_labs.utilities.utils.url.UrlShortener;
import de.marx_labs.utilities.utils.url.URLHelper;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class UrlShortenerTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
	}

	@Test
	public void test1 () {
		String google = "www.google.de";
		int hash = 52423;
		System.out.println("1: " + hash);
		String google2 = UrlShortener.encode(hash);
		System.out.println("2: " + google2);
		
		System.out.println("3: " + UrlShortener.unique());
	}
	
	@Test
	public void decode () {
		assertThat(URLHelper.encodeURIComponent("///hallo thorsten wie geht    es dir///", "-"), is("hallo-thorsten-wie-geht-es-dir"));
	}
}
