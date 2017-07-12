package com.gemengine.system.ui;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.WidgetGroup;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.gemengine.component.ui.UIComponent;
import com.gemengine.component.ui.UILayoutComponent;
import com.gemengine.component.ui.UIStageComponent;
import com.gemengine.component.ui.UIWidgetComponent;
import com.gemengine.entity.Entity;
import com.gemengine.system.base.ComponentTrackerSystem;
import com.gemengine.system.base.SystemBase;
import com.gemengine.system.listener.base.ComponentTrackerListener;
import com.gemengine.system.helper.ListenerHelper;
import com.google.inject.Inject;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UIActorSystem extends SystemBase implements ComponentTrackerListener<UILayoutComponent, UIComponent> {
	private final UIStageSystem uiStageSystem;
	private final Map<Integer, Table> actorToTable = new HashMap<Integer, Table>();
	@Getter
	private final Set<String> configuration = ListenerHelper.createConfiguration(UILayoutComponent.class);

	@Inject
	public UIActorSystem(UITrackerSystem uiStageTrackerSystem, UIStageSystem uiStageSystem) {
		super(true, 100);
		this.uiStageSystem = uiStageSystem;
		uiStageTrackerSystem.addListener(this);
	}

	@Override
	public void onFound(UILayoutComponent notifier, Entity component) {
		List<UIComponent> widgets = component.getComponents(UIComponent.class);
		if (widgets == null) {
			return;
		}
		for (UIComponent widget : widgets) {
			notifier.onChildNotify(widget);
		}
	}

	@Override
	public void onLost(Entity component) {
	}
}
