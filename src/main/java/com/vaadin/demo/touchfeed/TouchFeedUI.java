package com.vaadin.demo.touchfeed;

import com.google.common.eventbus.EventBus;
import com.vaadin.addon.touchkit.ui.NavigationManager;
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

    /** The event bus responsible for routing events */
    private EventBus eventBus = new EventBus();

    /**
     * Sets the content of the main view to a navigation manager with a blog
     * list as its initial view.
     * 
     * @param request
     *            the request details, not used.
     */
    @Override
    protected void init(VaadinRequest request) {
        setContent(new NavigationManager(new BlogList()));
    }

    /**
     * @return The event bus used to route events.
     */
    public static EventBus getEventBus() {
        return ((TouchFeedUI) UI.getCurrent()).eventBus;
    }
}
