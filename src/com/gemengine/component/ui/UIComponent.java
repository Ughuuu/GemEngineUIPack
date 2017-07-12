package com.gemengine.component.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gemengine.component.base.DrawableComponent;
import com.gemengine.system.ComponentSystem;

import lombok.Getter;

/**
 * Class that provides base for UIComponent. This is used in different UI
 * Systems to differentiate between generic ui components and other types of
 * components.
 * 
 * @author Dragos
 *
 */
public abstract class UIComponent extends DrawableComponent {
	@Getter
	protected int align = Align.center;

	public UIComponent(ComponentSystem componentSystem) {
		super(componentSystem);
	}

	/**
	 * This is used to return the actual Actor this UI Component represents.
	 * This is here to make the UI Components easier to extend. By this, the
	 * systems just query this and get the actor, while adding it also to the
	 * scene/layout it needs to.
	 * 
	 * @return
	 */
	@JsonIgnore
	public abstract Actor getActor();
}
