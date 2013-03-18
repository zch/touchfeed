package com.vaadin.demo.touchfeed;

import com.vaadin.addon.touchkit.gwt.client.theme.StyleNames;
import com.vaadin.addon.touchkit.ui.Popover;
import com.vaadin.addon.touchkit.ui.UrlField;
import com.vaadin.demo.touchfeed.domain.Blog;
import com.vaadin.demo.touchfeed.events.BlogAddedEvent;
import com.vaadin.ui.Button;
import com.vaadin.ui.CssLayout;
import com.vaadin.ui.Panel;

public class NewBlogPopover extends Popover {

    private final UrlField urlField = new UrlField();
    private final Button cancelButton = new Button("Cancel");
    private final Button addButton = new Button("Add");

    public NewBlogPopover() {
        Panel p = new Panel();
        CssLayout layout = new CssLayout();
        layout.setStyleName("new-blog");
        p.setContent(layout);
        setContent(p);

        urlField.setStyleName("new-blog-url");
        urlField.setValue("http://");
        addButton.setStyleName(StyleNames.BUTTON_GREEN);

        layout.addComponent(urlField);
        layout.addComponent(cancelButton);
        layout.addComponent(addButton);

        cancelButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                close();
            }
        });
        addButton.addClickListener(new Button.ClickListener() {
            @Override
            public void buttonClick(Button.ClickEvent event) {
                addBlogAndClose();
            }
        });

        urlField.focus();
    }

    private void addBlogAndClose() {
        Blog blog = new Blog();
        blog.setUrl(urlField.getValue());
        blog.store();
        close();
        TouchFeedUI.getEventBus().post(new BlogAddedEvent(blog));
    }
}
