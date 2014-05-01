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
package de.marx_labs.utilities.template.api;

import de.marx_labs.utilities.template.api.TemplateEngine;
import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Test;

import de.marx_labs.utilities.template.api.context.Context;
import de.marx_labs.utilities.template.api.context.SimpleContext;
import freemarker.cache.StringTemplateLoader;

/**
 * Created by IntelliJ IDEA. User: marx Date: 13.06.12 Time: 11:49 To change
 * this template use File | Settings | File Templates.
 */
public class TemplateEngineTest {

	@Test
	public void testRender() throws IOException {
		StringTemplateLoader loader = new StringTemplateLoader();

		loader.putTemplate("test1", "Hi ${name?if_exists}, How are you?");
		loader.putTemplate("greetTemplate", "[#macro greet]Hello[/#macro]");
		loader.putTemplate("myTemplate",
				"[#include \"greetTemplate\" ][@greet/] World!");

		Context context = new SimpleContext();
		context.put("name", "Joe");

		TemplateEngine manager = TemplateEngine.builder()
				.templateLoader(loader).build();

		assertEquals("Hi Joe, How are you?", manager.render("test1", context));

		assertEquals("Hello World!", manager.render("myTemplate", context));
	}

	@Test
	public void testMethod() throws IOException {
		StringTemplateLoader loader = new StringTemplateLoader();

		loader.putTemplate("test1",
				"${indexOf(\"met\", x)}");

		Context context = new SimpleContext();
		context.put("x", "something");

		TemplateEngine manager = TemplateEngine.builder()
				.templateLoader(loader).build();

		assertEquals("2", manager.render("test1", context));
	}
	
	@Test
	public void testUpperDirective() throws IOException {
		StringTemplateLoader loader = new StringTemplateLoader();

		loader.putTemplate("test1",
				"[@upper]${x}[/@upper]");

		String totest = "something";
		
		Context context = new SimpleContext();
		context.put("x", totest);

		TemplateEngine manager = TemplateEngine.builder()
				.templateLoader(loader).build();

		assertEquals(totest.toUpperCase(), manager.render("test1", context));
	}
	
	@Test
	public void testRepeatDirective() throws IOException {
		StringTemplateLoader loader = new StringTemplateLoader();

		loader.putTemplate("test1",
				"[@repeat count=3]${x}[/@repeat]");

		String totest = "something";
		
		Context context = new SimpleContext();
		context.put("x", totest);

		TemplateEngine manager = TemplateEngine.builder()
				.templateLoader(loader).build();

		assertEquals(totest+totest+totest, manager.render("test1", context));
	}
}
