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
package de.uni_koeln.spinfo.maalr.webapp.ui.common.client;

import com.google.gwt.dom.client.Element;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Command;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Anchor;

public class HiJax {

	public static Anchor getAnchor(String elementId) {
		try {
			Element element = DOM.getElementById(elementId);
			Anchor anchor = Anchor.wrap(element);
			anchor.setStylePrimaryName("anchorwrapper");
			return anchor;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public static void hijackAnchor(String anchorId, final Command alternative) {
		hijackAnchor(getAnchor(anchorId), alternative);
	}
	
	public static void hijackAnchor(Anchor anchor, final Command alternative) {
		anchor.addClickHandler(new ClickHandler() {
			
			@Override
			public void onClick(ClickEvent event) {
				try {
					alternative.execute();
				} catch (Exception e) {
					Window.alert("Failed to execute action: " + e);
				} finally {
					event.getNativeEvent().preventDefault();
					event.getNativeEvent().stopPropagation();

				}
			}
		});
	}
	
}
