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
package de.marx_labs.utilities.template.api.methods;

import java.util.List;

import de.marx_labs.utilities.template.api.provider.MethodProvider;
import freemarker.template.SimpleNumber;
import freemarker.template.SimpleScalar;
import freemarker.template.TemplateMethodModel;
import freemarker.template.TemplateMethodModelEx;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;


/**
 * Created by IntelliJ IDEA.
 * User: marx
 * Date: 13.06.12
 * Time: 14:13
 * To change this template use File | Settings | File Templates.
 */


public class IndexOfMethod implements TemplateMethodModelEx, MethodProvider {

    public TemplateModel exec(List args) throws TemplateModelException {
        if (args.size() != 2) {
            throw new TemplateModelException("Wrong arguments");
        }
        return new SimpleNumber(
            ((SimpleScalar) args.get(1)).getAsString().indexOf(((SimpleScalar) args.get(0)).getAsString()));
    }

	@Override
	public String getName() {
		return "indexOf";  //To change body of implemented methods use File | Settings | File Templates.
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public TemplateMethodModelEx getMethod() {
		return this;
	}
}
