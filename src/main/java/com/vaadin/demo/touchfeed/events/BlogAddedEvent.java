package com.vaadin.demo.touchfeed.events;

import com.vaadin.demo.touchfeed.domain.Blog;

public class BlogAddedEvent {
    private final Blog blog;

    public BlogAddedEvent(Blog blog) {
        this.blog = blog;
    }

    public Blog getBlog() {
        return blog;
    }
}
