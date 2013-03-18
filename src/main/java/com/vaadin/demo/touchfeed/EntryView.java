package com.vaadin.demo.touchfeed;

import com.sun.syndication.feed.synd.SyndEntry;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.BorderStyle;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

public class EntryView extends NavigationView {
    private final SyndEntry entry;
    private final CssLayout layout = new CssLayout();

    public EntryView(SyndEntry entry) {
        this.entry = entry;
        layout.setStyleName("entry-content");
        setContent(layout);
        setCaption(entry.getTitle() + ", " + entry.getAuthor());

        getNavigationBar().setRightComponent(makeOpenInBrowserLink());
    }

    private Link makeOpenInBrowserLink() {
        Link addBlogLink = new Link("", new ExternalResource(entry.getLink()),
                "_blank", 0, 0, BorderStyle.DEFAULT);
        addBlogLink.addStyleName("open-external-link");
        return addBlogLink;
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        layout.removeAllComponents();
        Label content = new Label(entry.getDescription().getValue(),
                ContentMode.HTML);
        content.setStyleName("entry-content");
        layout.addComponent(content);
    }
}
