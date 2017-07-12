package com.gemengine.system.ui;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle;
import com.gemengine.component.Component;
import com.gemengine.component.ui.UIComponent;
import com.gemengine.component.ui.UIContainerComponent;
import com.gemengine.component.ui.UILabelComponent;
import com.gemengine.component.ui.UILayoutComponent;
import com.gemengine.component.ui.UIStageComponent;
import com.gemengine.entity.Entity;
import com.gemengine.listener.ComponentListener;
import com.gemengine.system.AssetSystem;
import com.gemengine.system.ComponentSystem;
import com.gemengine.system.EntitySystem;
import com.gemengine.system.base.ConstructorSystem;
import com.gemengine.system.base.SystemBase;
import com.gemengine.system.helper.ListenerHelper;
import com.google.inject.Inject;

import lombok.Getter;
import lombok.extern.log4j.Log4j2;

@Log4j2
public class UIContainerSystem extends SystemBase implements ComponentListener {
	private Map<Integer, Container> containers = new HashMap<Integer, Container>();
	private Map<Integer, Integer> containees = new HashMap<Integer, Integer>();
	@Getter
	private final Set<String> configuration = ListenerHelper.createConfiguration(UIContainerComponent.class);
	private final UIStageSystem uiStageSystem;
	private final UITrackerSystem uiTrackerSystem;

	@Inject
	public UIContainerSystem(ComponentSystem componentSystem, EntitySystem entitySystem, UIStageSystem uiStageSystem,
			UITrackerSystem uiTrackerSystem) {
		super(true, 10);
		this.uiStageSystem = uiStageSystem;
		this.uiTrackerSystem = uiTrackerSystem;
		componentSystem.addComponentListener(this);
	}

	public void create(UIContainerComponent containerComponent, UIComponent uiComponent) {
		Actor actor = uiComponent.getActor();
		if (actor == null) {
			return;
		}
		Integer containeeId = containees.get(containerComponent.getId());
		Container container = containers.get(containerComponent.getId());
		if (containeeId == null || containeeId != uiComponent.getId()) {
			container = new Container(actor);
			containers.put(containerComponent.getId(), container);
			containees.put(containerComponent.getId(), uiComponent.getId());
		} else {
			container.invalidate();
		}
		container.setFillParent(true);
		container.align(containerComponent.getAlign());
		container.pad(containerComponent.getPadTop(), containerComponent.getPadLeft(),
				containerComponent.getPadBottom(), containerComponent.getPadRight());
		container.fill(containerComponent.getFillX(), containerComponent.getFillY());
		Entity child = containerComponent.getOwner();
		UILayoutComponent layout = uiTrackerSystem.getTrackedComponentUp(child);
		if (layout == null) {
			return;
		}
		layout.onChildNotify(containerComponent);
	}

	public Container get(UIContainerComponent uiContainerComponent) {
		return containers.get(uiContainerComponent.getId());
	}

	@Override
	public <T extends Component> void onChange(ComponentChangeType event, T arg1) {
		UIContainerComponent containerComponent = (UIContainerComponent) arg1;
		Container container = get(containerComponent);
		if (container == null) {
			return;
		}
		switch (event) {
		case ADD:
			break;
		case DELETE:
			container.remove();
			break;
		default:
			break;
		}
	}

	@Override
	public <T extends Component> void onNotify(String event, T arg1) {
		UIContainerComponent containerComponent = (UIContainerComponent) arg1;
		Container container = get(containerComponent);
		if (container == null) {
			return;
		}
		container.setFillParent(true);
		container.align(containerComponent.getAlign());
		container.pad(containerComponent.getPadTop(), containerComponent.getPadLeft(),
				containerComponent.getPadBottom(), containerComponent.getPadRight());
		container.fill(containerComponent.getFillX(), containerComponent.getFillY());
	}

	@Override
	public <T extends Component> void onTypeChange(Class<T> arg0) {
	}
}
