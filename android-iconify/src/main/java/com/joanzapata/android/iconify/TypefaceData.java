package com.joanzapata.android.iconify;

import java.util.Map;

import android.graphics.Typeface;

public class TypefaceData {

	private Typeface typeface;
	private String prefix;
	private int attributesId;
	private int attributeTypefaceNameId;
	private Map<String, Character> glyphsMap;

	public TypefaceData(Typeface typeface, String prefix, Map<String, Character> glyphsMap) {
		this.typeface = typeface;
		this.prefix = prefix;
		this.glyphsMap = glyphsMap;
	}
	
	public Typeface getTypeface() {
		return typeface;
	}

	public String getPrefix() {
		return prefix;
	}

	public int getAttributesId() {
		return attributesId;
	}

	public int getAttributeTypefaceNameId() {
		return attributeTypefaceNameId;
	}
	
	public char getGlyphCode(String key) {
		return glyphsMap.get(key);
	}
}
