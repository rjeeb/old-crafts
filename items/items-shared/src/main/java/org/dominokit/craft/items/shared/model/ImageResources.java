package org.dominokit.craft.items.shared.model;

import org.dominokit.jacksonapt.annotation.JSONMapper;

import java.util.ArrayList;
import java.util.List;

@JSONMapper
public class ImageResources {

    private List<ImageResource> imageResources = new ArrayList<>();

    public List<ImageResource> getImageResources() {
        return imageResources;
    }

    public void setImageResources(List<ImageResource> imageResources) {
        this.imageResources = imageResources;
    }
}
