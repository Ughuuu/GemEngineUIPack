package com.gemengine.component.ui;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.twod.SpriteSystem;
import com.gemengine.system.ui.UIStageSystem;
import com.gemengine.system.ui.UITrackerSystem;
import com.google.inject.Inject;

import lombok.extern.log4j.Log4j2;

/**
 * Stage component that contains all UI Components that are not contained by
 * other UI Components. Only first level of entities that contain ui components
 * are added into the stage.
 * Ex:
 *   Stage
 *     |    \
 *    UIa    UIc
 *     |
 *    UIb
 * Only UIa and UIc are added automatically by ui system into the stage. 
 * In this example, UIa should be a layout component(ex container).
 * @author Dragos
 *
 */
@Log4j2
public class UIStageComponent extends UILayoutComponent {
	private final UIStageSystem uiStageSystem;

	@Inject
	public UIStageComponent(ComponentSystem componentSystem, UIStageSystem uiStageSystem) {
		super(componentSystem);
		this.uiStageSystem = uiStageSystem;
	}

	@Override
	public String toString() {
		return "UIStageComponent []";
	}

	public void onChildNotify(UIComponent child) {
		uiStageSystem.addChild(this, child);
	}

	@Override
	public Actor getActor() {
		return null;
	}
}
