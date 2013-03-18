package com.vaadin.demo.touchfeed;

import com.vaadin.addon.touchkit.ui.NavigationManager;

public class TouchFeedManager extends NavigationManager {

    private final BlogList rootView;

    public TouchFeedManager() {
        rootView = new BlogList();
        setCurrentComponent(rootView);
    }

}
