package com.vaadin.demo.touchfeed.events;

import com.vaadin.demo.touchfeed.domain.Blog;

/**
 * An event object used when triggering an event whenever a new blog is added.
 */
public class BlogAddedEvent {
    private final Blog blog;

    public BlogAddedEvent(Blog blog) {
        this.blog = blog;
    }

    /**
     * @return The blog that was added.
     */
    public Blog getBlog() {
        return blog;
    }
}
