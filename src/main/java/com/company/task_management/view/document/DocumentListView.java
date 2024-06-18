package com.company.task_management.view.document;

import com.company.task_management.entity.Document;
import com.company.task_management.view.main.MainView;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.data.renderer.ComponentRenderer;
import com.vaadin.flow.data.renderer.Renderer;
import com.vaadin.flow.router.Route;
import io.jmix.core.FileRef;
import io.jmix.flowui.UiComponents;
import io.jmix.flowui.download.Downloader;
import io.jmix.flowui.kit.component.button.JmixButton;
import io.jmix.flowui.view.*;
import org.springframework.beans.factory.annotation.Autowired;

@Route(value = "documents", layout = MainView.class)
@ViewController("tm_Document.list")
@ViewDescriptor("document-list-view.xml")
@LookupComponent("documentsDataGrid")
@DialogMode(width = "64em")
public class DocumentListView extends StandardListView<Document> {

    @Autowired
    private UiComponents uiComponents;
    @Autowired
    private Downloader downloader;

    @Supply(to = "documentsDataGrid.file", subject = "renderer")
    private Renderer<Document> documentsDataGridFileRenderer() {

        return new ComponentRenderer<>(document -> {
            FileRef documentFile = document.getFile();

            if (documentFile != null) {
                JmixButton btn = uiComponents.create(JmixButton.class);
                btn.setText(documentFile.getFileName());
                btn.addThemeVariants(ButtonVariant.LUMO_TERTIARY_INLINE);
                btn.addClickListener(click -> downloader.download(documentFile));

                return btn;
            }
            return null;
        });
    }
}