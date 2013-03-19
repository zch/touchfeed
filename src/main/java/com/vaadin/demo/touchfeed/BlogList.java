package com.vaadin.demo.touchfeed;

import java.util.List;

import com.google.common.eventbus.Subscribe;
import com.vaadin.addon.touchkit.ui.NavigationButton;
import com.vaadin.addon.touchkit.ui.NavigationView;
import com.vaadin.addon.touchkit.ui.VerticalComponentGroup;
import com.vaadin.demo.touchfeed.domain.Blog;
import com.vaadin.demo.touchfeed.events.BlogAddedEvent;
import com.vaadin.shared.ui.label.ContentMode;
import com.vaadin.ui.Button;
import com.vaadin.ui.Label;

/**
 * This is the main view, which lists all stored blogs.
 */
public class BlogList extends NavigationView {

    private final VerticalComponentGroup componentGroup = new VerticalComponentGroup();
    private final List<Blog> blogs;

    /**
     * Creates the layouts and either adds the blogs or a notice on how to add
     * blogs.
     */
    public BlogList() {
        setCaption("Blogs");
        TouchFeedUI.getEventBus().register(this);

        setContent(componentGroup);

        getNavigationBar().setRightComponent(makeAddBlogButton());

        blogs = Blog.findAll();
        if (blogs.isEmpty()) {
            addNoBlogsAvailableLabel();
        }
        addBlogs();
    }

    /**
     * Creates and adds a label that describes how to add new blogs.
     */
    private void addNoBlogsAvailableLabel() {
        Label noBlogsLabel = new Label(
                "<h1>You have not added any blogs yet.</h1> Add new blogs using the button above.",
                ContentMode.HTML);
        noBlogsLabel.setStyleName("no-blogs-label");
        setContent(noBlogsLabel);
    }

    /**
     * Builds a button that adds a new blog.
     * 
     * @return The button that adds a new blog.
     */
    private Button makeAddBlogButton() {
        Button addBlogButton = new Button("+");
        addBlogButton.setStyleName("iconbutton");
        // Use the icon-plus-sign from Font-Awesome
        addBlogButton.addStyleName("icon-plus-sign");

        // When clicked, show a NewBlogPopover
        addBlogButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                NewBlogPopover popover = new NewBlogPopover();
                popover.showRelativeTo(event.getButton());
            }
        });

        return addBlogButton;
    }

    /**
     * Adds a button for each blog in the database.
     */
    private void addBlogs() {
        for (Blog blog : blogs) {
            addBlogButton(blog);
        }
    }

    /**
     * Adds a button for a single blog
     * 
     * @param blog
     *            the blog to add a button for.
     */
    private void addBlogButton(Blog blog) {
        NavigationButton blogButton = new NavigationButton(blog.getFeed()
                .getTitle(), new BlogView(blog));
        blogButton.setDescription("" + blog.getEntries().size());
        componentGroup.addComponent(blogButton);
    }

    /**
     * Adds a new blog button for a newly added blog. Triggered when a
     * BlogAddedEvent is fired.
     * 
     * @param e
     *            the BlogAddedEvent
     */
    @Subscribe
    public void onBlogAdded(BlogAddedEvent e) {
        // If no blogs are available there is only a label.
        if (getContent() != componentGroup) {
            setContent(componentGroup);
            // if we don't mark as dirty, nothing will update.
            markAsDirtyRecursive();
        }
        addBlogButton(e.getBlog());
    }
}
