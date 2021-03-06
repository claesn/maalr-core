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
package de.uni_koeln.spinfo.maalr.common.shared;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import de.uni_koeln.spinfo.maalr.common.server.util.Configuration;


@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Overlays {
	
	@XmlTransient
	private Map<String, Overlay> overlays = new HashMap<String, Overlay>();
	
	@XmlElement(name="overlay")
	private List<Overlay> xmlList = new ArrayList<Overlay>();

	@XmlTransient
	private Map<String, OverlayEditor> editors = new HashMap<String, OverlayEditor>();
	
	private static Overlays instance;
	
	static {
		try {
			JAXBContext ctx = JAXBContext.newInstance(Overlays.class, OverlayEditor.class);
			Unmarshaller unmarshaller = ctx.createUnmarshaller();
			File overlayDefinition = new File(Configuration.getInstance().getConfigDirectory(),"overlays.xml");
			if(overlayDefinition.exists()) {
				instance = (Overlays) unmarshaller.unmarshal(new InputStreamReader(new FileInputStream(overlayDefinition), "UTF-8"));
				List<Overlay> overlays = instance.xmlList;
				for (Overlay overlay : overlays) {
					BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(overlay.getForm()), "UTF-8"));
					String line = null;
					StringBuilder sb = new StringBuilder();
					while((line = br.readLine()) != null) {
						sb.append(line);
						sb.append("\n");
					}
					br.close();
					overlay.setForm(sb.toString());
					String editorPath = overlay.getEditor();
					if(editorPath != null) {
						OverlayEditor editor = (OverlayEditor) unmarshaller.unmarshal(new InputStreamReader(new FileInputStream(editorPath), "UTF-8"));
						instance.editors.put(overlay.getType(), editor);
					}
					instance.overlays.put(overlay.getType(), overlay);
				}
			} else {
				instance = new Overlays();
			}
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	public static Overlay get(String overlayType) throws IOException {
		return instance.getOverlay(overlayType);
	}

	private Overlay getOverlay(String overlayType) throws IOException {
		Overlay overlay = overlays.get(overlayType);
		return overlay;
	}

	public static OverlayEditor getEditor(String overlayType) {
		if(instance.editors == null) return null;
		return instance.editors.get(overlayType);
	}

	public static ArrayList<String> getOverlayTypes(boolean firstLanguage) {
		ArrayList<String> types = new ArrayList<String>();
		if(instance.overlays == null) return types;
		Collection<Overlay> overlays = instance.overlays.values();
		for (Overlay overlay : overlays) {
			if(overlay.isFirstLanguage() == firstLanguage) {
				types.add(overlay.getType());
			}
		}
		return types;
	}

}
