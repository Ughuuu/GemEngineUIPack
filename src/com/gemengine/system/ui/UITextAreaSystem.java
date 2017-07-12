package com.gemengine.system.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.ui.TextField.TextFieldStyle;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.gemengine.component.ui.UILabelComponent;
import com.gemengine.component.ui.UITextAreaComponent;
import com.gemengine.system.AssetSystem;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.EntitySystem;
import com.gemengine.system.base.ConstructorSystem;
import com.google.inject.Inject;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UITextAreaSystem extends ConstructorSystem<TextArea, UITextAreaComponent> {
	private final AssetSystem assetSystem;

	@Inject
	public UITextAreaSystem(AssetSystem assetSystem, ComponentSystem componentSystem, EntitySystem entitySystem) {
		super(componentSystem, entitySystem, true, 16, UITextAreaComponent.class);
		this.assetSystem = assetSystem;
	}

	@Override
	protected TextArea create(UITextAreaComponent component) {
		TextFieldStyle textFieldStyle = new TextFieldStyle();
		
		Pixmap pixmap = new Pixmap(2, 2, Format.RGBA8888);
		pixmap.setColor(Color.RED);
		pixmap.fillRectangle(0, 0, 2, 2);
		
		textFieldStyle.cursor = new TextureRegionDrawable(new TextureRegion(new Texture(pixmap, Format.RGB888, false)));
		String fontPath = component.getFont();
		textFieldStyle.fontColor = Color.WHITE;
		if (fontPath != null) {
			textFieldStyle.font = assetSystem.getAsset(fontPath);
		}
		if (textFieldStyle.font == null) {
			BitmapFont font = new BitmapFont();
			textFieldStyle.font = font;
		}
		String text = component.getText();
		if (text == null) {
			text = "";
		}
		TextArea textArea = new TextArea(text, textFieldStyle);
		textArea.setColor(Color.WHITE);
		return textArea;
	}

	@Override
	protected void onEvent(String event, UITextAreaComponent notifier, TextArea label) {
		switch (event) {
		case "text":
			label.setText(notifier.getText());
			break;
		case "font":
			add(notifier);
			break;
		}
	}
}
