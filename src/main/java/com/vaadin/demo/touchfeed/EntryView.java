package com.vaadin.demo.touchfeed;

import com.sun.syndication.feed.synd.SyndEntry;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.server.ExternalResource;
import com.vaadin.shared.ui.BorderStyle;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Label;
import com.vaadin.ui.Link;

/**
 * This view shows the content of an article/entry
 */
public class EntryView extends NavigationView {
    private final SyndEntry entry;
    private final CssLayout layout = new CssLayout();

    /**
     * Constructs the layouts and sets the caption.
     * 
     * @param entry
     *            the entry to show.
     */
    public EntryView(SyndEntry entry) {
        this.entry = entry;
        layout.setStyleName("entry-content");
        setContent(layout);
        setCaption(entry.getTitle() + ", " + entry.getAuthor());

        getNavigationBar().setRightComponent(makeOpenInBrowserLink());
    }

    /**
     * Creates a styled link that opens the original article in a new browser
     * window.
     * 
     * @return a Link that opens the article in a new window.
     */
    private Link makeOpenInBrowserLink() {
        Link addBlogLink = new Link("", new ExternalResource(entry.getLink()),
                "_blank", 0, 0, BorderStyle.DEFAULT);
        addBlogLink.addStyleName("open-external-link");
        return addBlogLink;
    }

    /**
     * Sets the content of the view to the article/entry text. Called when the
     * view is navigated to.
     */
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
