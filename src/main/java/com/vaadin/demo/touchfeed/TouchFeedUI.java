package com.vaadin.demo.touchfeed;

import com.google.common.eventbus.EventBus;
import com.vaadin.annotations.PreserveOnRefresh;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;

/**
 * The Application's "main" class
 */
@SuppressWarnings("serial")
@Theme("touchfeed")
@PreserveOnRefresh
@Title("TouchFeed RSS")
public class TouchFeedUI extends UI {

    private EventBus eventBus = new EventBus();

    @Override
    protected void init(VaadinRequest request) {
        setContent(new TouchFeedManager());
    }

    public static EventBus getEventBus() {
        return ((TouchFeedUI) UI.getCurrent()).eventBus;
    }
}
