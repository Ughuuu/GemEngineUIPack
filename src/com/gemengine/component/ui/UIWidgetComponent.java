package com.gemengine.component.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Align;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.gemengine.component.Component;
import com.gemengine.component.base.PointComponent;
import com.gemengine.component.twod.CameraComponent;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.ui.UIActorSystem;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.log4j.Log4j2;

/**
 * Widget component is used in ui systems to differentiate between components
 * that are used for layout and components that represent widgets.
 * 
 * @author Dragos
 *
 */
@Log4j2
public abstract class UIWidgetComponent extends UIComponent {

	public UIWidgetComponent(ComponentSystem componentSystem) {
		super(componentSystem);
	}

	@Override
	public <T extends Component> void onNotify(String arg0, T arg1) {
		/*
		 * PointComponent point = (PointComponent) arg1; if (!(point instanceof
		 * PointComponent)) { return; } Actor actor = getActor(); if (actor ==
		 * null) return; actor.setOrigin(align);
		 * actor.setPosition(point.getPosition().x, point.getPosition().y);
		 * actor.setZIndex(depth); if (point.getScale().isZero() &&
		 * point.getRotation().isZero()) { } else {
		 * actor.setScale(point.getScale().x, point.getScale().y);
		 * actor.setRotation(point.getRotation().z); }
		 */
	}
}
