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

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Collection;

import de.marx_labs.utilities.common.service.Lookup;
import de.marx_labs.utilities.template.api.context.Context;
import de.marx_labs.utilities.template.api.provider.DirectiveProvider;
import de.marx_labs.utilities.template.api.provider.MethodProvider;
import freemarker.cache.TemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

/**
 * @author Thorsten Marx (thmarx@gmx.net)
 */
public class TemplateEngine {

	private Configuration config = null;

	public static class Builder {
		private TemplateLoader templateLoader = null;

		public Builder templateLoader(TemplateLoader templateLoader) {
			this.templateLoader = templateLoader;
			return this;
		}

		public TemplateEngine build() {
			Configuration config = new Configuration();
			config.setTemplateLoader(templateLoader);
			config.setObjectWrapper(new DefaultObjectWrapper());
			config.setTagSyntax(Configuration.SQUARE_BRACKET_TAG_SYNTAX);

			// loading MethodProviders
			Collection<? extends MethodProvider> methodProviders = Lookup
					.lookupAll(MethodProvider.class);
			
			for (MethodProvider mp : methodProviders) {
				config.setSharedVariable(mp.getName(), mp.getMethod());
			}
			// loading DirectiveProviders
			Collection<? extends DirectiveProvider> directiveProviders = Lookup
					.lookupAll(DirectiveProvider.class);
			
			for (DirectiveProvider mp : directiveProviders) {
				config.setSharedVariable(mp.getName(), mp.getDirective());
			}

			return new TemplateEngine(config);
		}

	}

	public static Builder builder() {
		return new Builder();
	}

	private TemplateEngine(Configuration config) {
		this.config = config;
	}
	
	public Configuration getConfig () {
		return config;
	}
	
	

	public void render(String template, Context context,
			Writer writer) throws IOException {
		try {
			if (context == null) {
				context = new Context();
			}
			Template temp = config.getTemplate(template);
			temp.process(context, writer);

		} catch (Exception e) {
			throw new IOException(e);
		}
	}

	public String render(String template, Context context)
			throws IOException {
		try {

			StringWriter sw = new StringWriter();
			BufferedWriter bw = new BufferedWriter(sw);
			render(template, context, bw);
			bw.flush();
			return sw.toString();
		} catch (Exception e) {
			throw new IOException(e);
		}
	}
}
