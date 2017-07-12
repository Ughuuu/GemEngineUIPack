package com.gemengine.component.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.ui.UILabelSystem;
import com.gemengine.system.ui.UITextAreaSystem;
import com.google.inject.Inject;

import lombok.Getter;

public class UITextAreaComponent extends UIWidgetComponent {
	private final UITextAreaSystem uiTextAreaSystem;

	@Getter
	private String font = "";

	@Getter
	private String text = "";

	@Inject
	public UITextAreaComponent(ComponentSystem componentSystem, UITextAreaSystem uiTextAreaSystem) {
		super(componentSystem);
		this.uiTextAreaSystem = uiTextAreaSystem;
	}

	@Override
	@JsonIgnore
	public Actor getActor() {
		return uiTextAreaSystem.get(this);
	}

	public UITextAreaComponent setFont(String fontName) {
		font = fontName;
		doNotify("font");
		return this;
	}

	public UITextAreaComponent setText(String text) {
		this.text = text;
		doNotify("text");
		return this;
	}

	@Override
	public String toString() {
		return "UITextAreaComponent [font=" + font + ", text=" + text + "]";
	}
}
