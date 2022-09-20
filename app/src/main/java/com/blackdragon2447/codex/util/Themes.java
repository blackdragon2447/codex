package com.blackdragon2447.codex.util;

import javax.swing.LookAndFeel;

import com.formdev.flatlaf.FlatDarculaLaf;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.FlatLightLaf;

public enum Themes {
	
	FlatLight,
	FlatDark,
	FlatIntelliJ,
	FlatDarcula;
	
	public LookAndFeel getLookAndFeel() {
		switch (this) {
		case FlatLight:
			return new FlatLightLaf();
		case FlatDark:
			return new FlatDarkLaf();
		case FlatIntelliJ:
			return new FlatIntelliJLaf();
		case FlatDarcula:
			return new FlatDarculaLaf();
		default:
			return new FlatLightLaf();
		}
	}

}

