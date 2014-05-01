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
package de.marx_labs.utilities.template.api.directive;

import java.io.IOException;
import java.io.Writer;
import java.util.Iterator;
import java.util.Map;

import de.marx_labs.utilities.template.api.provider.DirectiveProvider;
import freemarker.core.Environment;
import freemarker.template.SimpleNumber;
import freemarker.template.TemplateBooleanModel;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import freemarker.template.TemplateModelException;
import freemarker.template.TemplateNumberModel;

public class RepeatDirective implements DirectiveProvider, TemplateDirectiveModel {

	private static final String PARAM_NAME_COUNT = "count";
    private static final String PARAM_NAME_HR = "hr";
	
	@Override
	public String getName() {
		return "repeat";
	}

	@Override
	public String getDescription() {
		return "";
	}

	@Override
	public TemplateDirectiveModel getDirective() {
		return this;
	}

	@Override
	public void execute(Environment env,
            Map params, TemplateModel[] loopVars,
            TemplateDirectiveBody body)
            throws TemplateException, IOException {
        
        // ---------------------------------------------------------------------
        // Processing the parameters:
        
        int countParam = 0;
        boolean countParamSet = false;
        boolean hrParam = false;
        
        Iterator paramIter = params.entrySet().iterator();
        while (paramIter.hasNext()) {
            Map.Entry ent = (Map.Entry) paramIter.next();
            
            String paramName = (String) ent.getKey();
            TemplateModel paramValue = (TemplateModel) ent.getValue();
            
            if (paramName.equals(PARAM_NAME_COUNT)) {
                if (!(paramValue instanceof TemplateNumberModel)) {
                    throw new TemplateModelException(
                            "The \"" + PARAM_NAME_HR + "\" parameter "
                            + "must be a number.");
                }
                countParam = ((TemplateNumberModel) paramValue)
                        .getAsNumber().intValue();
                countParamSet = true;
                if (countParam < 0) {
                    throw new TemplateModelException(
                            "The \"" + PARAM_NAME_HR + "\" parameter "
                            + "can't be negative.");
                }
            } else if (paramName.equals(PARAM_NAME_HR)) {
                if (!(paramValue instanceof TemplateBooleanModel)) {
                    throw new TemplateModelException(
                            "The \"" + PARAM_NAME_HR + "\" parameter "
                            + "must be a boolean.");
                }
                hrParam = ((TemplateBooleanModel) paramValue)
                        .getAsBoolean();
            } else {
                throw new TemplateModelException(
                        "Unsupported parameter: " + paramName);
            }
        }
        if (!countParamSet) {
                throw new TemplateModelException(
                        "The required \"" + PARAM_NAME_COUNT + "\" paramter"
                        + "is missing.");
        }
        
        if (loopVars.length > 1) {
                throw new TemplateModelException(
                        "At most one loop variable is allowed.");
        }
        
        // Yeah, it was long and boring...
        
        // ---------------------------------------------------------------------
        // Do the actual directive execution:
        
        Writer out = env.getOut();
        if (body != null) {
            for (int i = 0; i < countParam; i++) {
                // Prints a <hr> between all repetations if the "hr" parameter
                // was true:
                if (hrParam && i != 0) {
                    out.write("<hr>");
                }
                
                // Set the loop variable, if there is one:
                if (loopVars.length > 0) {
                    loopVars[0] = new SimpleNumber(i + 1);
                }
                
                // Executes the nested body (same as <#nested> in FTL). In this
                // case we don't provide a special writer as the parameter:
                body.render(env.getOut());
            }
        }
    }
	
}
