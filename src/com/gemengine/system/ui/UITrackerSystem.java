package com.gemengine.system.ui;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.gemengine.component.ui.UIComponent;
import com.gemengine.component.ui.UILayoutComponent;
import com.gemengine.component.ui.UIStageComponent;
import com.gemengine.component.ui.UIWidgetComponent;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.EntitySystem;
import com.gemengine.system.base.ComponentTrackerSystem;
import com.gemengine.system.helper.ListenerHelper;
import com.google.inject.Inject;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class UITrackerSystem extends ComponentTrackerSystem<UILayoutComponent, UIComponent> {
	@Inject
	public UITrackerSystem(ComponentSystem componentSystem, EntitySystem entitySystem) {
		super(componentSystem, entitySystem, ListenerHelper.createConfiguration(UILayoutComponent.class), true, 12,
				UILayoutComponent.class, UIComponent.class, false);
	}
}
