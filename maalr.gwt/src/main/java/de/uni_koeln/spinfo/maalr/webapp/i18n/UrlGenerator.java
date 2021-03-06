/*******************************************************************************
 * Copyright 2013 Sprachliche Informationsverarbeitung, University of Cologne
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package de.uni_koeln.spinfo.maalr.webapp.i18n;

import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


public class UrlGenerator {
	
	private static HttpSession session() {
	    ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
	    return attr.getRequest().getSession(false);
	}
	
	public static String getHelpLink() {
//		String lang = (String) session().getAttribute("lang");
		String lang = (String) session().getAttribute("pl");
		if("de".equals(lang)) {
			return linkTo("hilfe.html", "Hilfe");	
		}
		if("en".equals(lang)) {
			return linkTo("help.html", "Help");	
		}
		return linkTo("agid.html", "agid");
	}

	private static String linkTo(String page, String title) {
		return "<a href=\"/"+ page + "\">" +  title + "</a>"; 
	}
	
}
