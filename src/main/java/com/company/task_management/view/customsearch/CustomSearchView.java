package com.company.task_management.view.customsearch;


import com.company.task_management.entity.Customer;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.router.Route;
import io.jmix.flowui.model.CollectionContainer;
import io.jmix.flowui.view.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Route(value = "custom-search-view", layout = MainView.class)
@ViewController("tm_CustomSearchView")
@ViewDescriptor("custom-search-view.xml")
public class CustomSearchView extends StandardView {

    @ViewComponent
    private CollectionContainer<Customer> customersDc;

    private static final Logger log = LoggerFactory.getLogger(CustomSearchView.class);

    @Subscribe
    public void onInit(final InitEvent event) {
        log.info("InitEvent - customers in container: " + customersDc.getItems().size());
    }
    
    
}