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
package de.marx_labs.utilities.common.template.impl.freemarker.test;


import java.util.HashMap;
import java.util.Map;

import de.marx_labs.utilities.common.template.TemplateManager;
import de.marx_labs.utilities.common.template.impl.freemarker.FMTemplateManager;



public class FmTest {
	public static void main (String [] args) throws Exception {
		
		
		Map root = new HashMap();
		root.put("server", "www.myserver1.de");
		
		
		TemplateManager tm = new FMTemplateManager();
		tm.init("templates");
		tm.registerTemplate("test", "banner/detail_spartopia.ftl");
		
		System.out.println(tm.processTemplate("test", root));
	}
}
