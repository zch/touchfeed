package com.vaadin.demo.touchfeed;

import com.sun.syndication.feed.synd.SyndEntry;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.demo.touchfeed.domain.Blog;

public class BlogView extends NavigationView {

    private final Blog blog;
    private final VerticalComponentGroup componentGroup;

    public BlogView(Blog blog) {
        this.blog = blog;
        componentGroup = new VerticalComponentGroup();
        setContent(componentGroup);
        setCaption(blog.getFeed().getTitle());
    }

    @Override
    protected void onBecomingVisible() {
        super.onBecomingVisible();
        for (SyndEntry entry : blog.getEntries()) {
            addEntryButton(entry);
        }
    }

    private void addEntryButton(SyndEntry entry) {
        NavigationButton blogButton = new NavigationButton(entry.getTitle(),
                new EntryView(entry));
        componentGroup.addComponent(blogButton);
    }
}
