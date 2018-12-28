package org.dominokit.craft.items.client.ui;

import elemental2.dom.HTMLDivElement;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.items.shared.model.ImageResources;
import org.dominokit.craft.items.shared.model.ImageResources_MapperImpl;
import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.domino.ui.cards.Card;
import org.dominokit.domino.ui.grid.Column;
import org.dominokit.domino.ui.grid.Row;
import org.dominokit.domino.ui.icons.Icons;
import org.dominokit.domino.ui.upload.FileUpload;
import org.dominokit.domino.ui.utils.BaseDominoElement;
import org.dominokit.domino.ui.utils.DominoElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.jboss.gwt.elemento.core.Elements.*;

public class ItemPhotosSection extends BaseDominoElement<HTMLDivElement, ItemPhotosSection> {

    private DominoElement<HTMLDivElement> element = DominoElement.div();
    private List<ImageResource> uploadedImageResources = new ArrayList<>();

    public ItemPhotosSection() {
        element.appendChild(Card.create("Photos", "Add as many as you can so buyers can see every detail.")
                .appendChild(Row.create()
                        .appendChild(Column.span3()
                                .appendChild(h(6).textContent("Photos *"))
                                .appendChild(p().textContent("Use up to ten photos to show your item's most important qualities."))
                                .appendChild(p().textContent("Tips: "))
                                .appendChild(ul()
                                        .add(li().textContent("Use natural light and no flash."))
                                        .add(li().textContent("Include a common object for scale."))
                                        .add(li().textContent("Show the item being held, worn, or used."))
                                        .add(li().textContent("Shoot against a clean, simple background."))
                                )
                        )
                        .appendChild(Column.span9()
                                .appendChild(FileUpload.create()
                                        .setIcon(Icons.ALL.camera_alt())
                                        .multipleFiles()
                                        .setUrl("http://localhost:8080/images/upload")
                                        .onAddFile(fileItem -> {
                                            fileItem.addSuccessUploadHandler(request -> {
                                                ImageResources imageResources = ImageResources_MapperImpl.INSTANCE.read(request.responseText);
                                                uploadedImageResources.addAll(imageResources.getImageResources());
                                            });
                                        })
                                        .appendChild(h(3).textContent("Drop files here or click to upload."))
                                        .appendChild(em().textContent("Upload up to 10 images for item"))
                                )
                        )
                )

        );
        init(this);
    }

    @Override
    public HTMLDivElement asElement() {
        return element.asElement();
    }

    public void save(ItemResource itemResource) {
        itemResource.setImageReferences(uploadedImageResources
                .stream()
                .map(ImageResource::getReference)
                .collect(Collectors.toList()));
    }
}
