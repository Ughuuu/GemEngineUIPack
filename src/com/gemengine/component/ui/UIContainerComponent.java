package com.gemengine.component.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.utils.Align;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.ui.UIContainerSystem;
import com.gemengine.system.ui.UITrackerSystem;
import com.google.inject.Inject;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

/**
 * Component with libgdx Container Component. This layout type can hold exactly
 * one ui widget or layout. General use case is to parent to the entity that has
 * this component a widget or layout, and parent this component owner to an
 * entity containing a scene component. Also, the scene component must be
 * parented to a camera.
 * 
 * @author Dragos
 *
 */
@Log4j2
public class UIContainerComponent extends UILayoutComponent {
	@Getter
	private float fillX, fillY;
	@Getter
	private float padTop, padBottom, padLeft, padRight;
	@Getter
	private int align = Align.center;
	@Getter
	private String texturePath;
	private final UIContainerSystem uiContainerSystem;

	@Inject
	public UIContainerComponent(ComponentSystem componentSystem, UIContainerSystem uiContainerSystem) {
		super(componentSystem);
		this.uiContainerSystem = uiContainerSystem;
	}

	public void onChildNotify(UIComponent child) {
		uiContainerSystem.create(this, child);
	}

	public UIContainerComponent setFillX(float fillX) {
		this.fillX = fillX;
		doNotify("fill");
		return this;
	}

	public UIContainerComponent setFillY(float fillY) {
		this.fillY = fillY;
		doNotify("fill");
		return this;
	}

	public UIContainerComponent setPadTop(float padTop) {
		this.padTop = padTop;
		doNotify("pad");
		return this;
	}

	public UIContainerComponent setPadBottom(float padBottom) {
		this.padBottom = padBottom;
		doNotify("pad");
		return this;
	}

	public UIContainerComponent setPadLeft(float padLeft) {
		this.padLeft = padLeft;
		doNotify("pad");
		return this;
	}

	public UIContainerComponent setPadRight(float padRight) {
		this.padRight = padRight;
		doNotify("pad");
		return this;
	}

	public UIContainerComponent setAlign(int align) {
		this.align = align;
		doNotify("align");
		return this;
	}

	@Override
	public Actor getActor() {
		return uiContainerSystem.get(this);
	}
}
