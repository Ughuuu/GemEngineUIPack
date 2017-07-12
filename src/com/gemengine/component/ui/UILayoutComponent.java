package com.gemengine.component.ui;

import com.badlogic.gdx.utils.Align;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.ui.UITrackerSystem;
import com.google.inject.Inject;

import lombok.Getter;

public abstract class UILayoutComponent extends UIComponent {
	@Inject
	public UILayoutComponent(ComponentSystem componentSystem) {
		super(componentSystem);
	}

	public abstract void onChildNotify(UIComponent child);
}
