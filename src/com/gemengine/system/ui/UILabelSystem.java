package com.gemengine.system.ui;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.gemengine.component.ui.UILabelComponent;
import com.gemengine.system.AssetSystem;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.EntitySystem;
import com.gemengine.system.base.ConstructorSystem;
import com.google.inject.Inject;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UILabelSystem extends ConstructorSystem<Label, UILabelComponent> {
	private final AssetSystem assetSystem;

	@Inject
	public UILabelSystem(AssetSystem assetSystem, ComponentSystem componentSystem, EntitySystem entitySystem) {
		super(componentSystem, entitySystem, true, 7, UILabelComponent.class);
		this.assetSystem = assetSystem;
	}

	@Override
	protected Label create(UILabelComponent component) {
		LabelStyle style = new LabelStyle();
		String fontPath = component.getFont();
		if (fontPath != null) {
			style.font = assetSystem.getAsset(fontPath);
		}
		if (style.font == null) {
			BitmapFont font = new BitmapFont();
			style.font = font;
		}
		String text = component.getText();
		if (text == null) {
			text = "";
		}
		Label label = new Label(text, style);
		label.setFontScale(component.getFontScale());
		return label;
	}

	@Override
	protected void onEvent(String event, UILabelComponent notifier, Label label) {
		switch (event) {
		case "text":
			label.setText(notifier.getText());
			break;
		case "font":
			add(notifier);
			break;
		case "fontScale":
			label.setFontScale(notifier.getFontScale());
			break;
		}
	}
}
