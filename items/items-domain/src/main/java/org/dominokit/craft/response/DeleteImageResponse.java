package org.dominokit.craft.response;

import org.dominokit.craft.items.shared.model.ImageResource;

public class DeleteImageResponse {
    private ImageResource deletedImage;

    public void setDeletedImage(ImageResource deletedImage) {
        this.deletedImage = deletedImage;
    }

    public ImageResource getDeletedImage() {
        return deletedImage;
    }
}
