package com.vaadin.demo.touchfeed;

import com.sun.syndication.feed.synd.SyndEntry;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.demo.touchfeed.domain.Blog;

/**
 * This view shows the articles in a blog.
 */
public class BlogView extends NavigationView {

    private final Blog blog;
    private final VerticalComponentGroup componentGroup;

    /**
     * Constructs the layouts and sets the caption.
     * 
     * @param blog
     *            The blog to show in this view.
     */
    public BlogView(Blog blog) {
        this.blog = blog;
        componentGroup = new VerticalComponentGroup();
        setContent(componentGroup);
        setCaption(blog.getFeed().getTitle());
    }

    /**
     * Adds NavigationButtons for all articles/entries. Called when the view is
     * navigated to.
     */
    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        for (SyndEntry entry : blog.getEntries()) {
            addEntryButton(entry);
        }
    }

    /**
     * Adds a button for a single article/entry.
     * 
     * @param entry
     *            the entry to add a button for.
     */
    private void addEntryButton(SyndEntry entry) {
        NavigationButton blogButton = new NavigationButton(entry.getTitle(),
                new EntryView(entry));
        componentGroup.addComponent(blogButton);
    }
}
