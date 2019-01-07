package org.dominokit.craft.repository.entity;

import org.dominokit.jacksonapt.annotation.JSONMapper;

@JSONMapper
public class ImageEntity {
    private String reference;
    private String itemReference;
    private String path;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getItemReference() {
        return itemReference;
    }

    public void setItemReference(String itemReference) {
        this.itemReference = itemReference;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
