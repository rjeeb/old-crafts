package org.dominokit.craft.response;

import org.dominokit.craft.items.shared.model.ImageResource;

public class UploadImageResponse {
    private ImageResource createdImage;

    public ImageResource getCreatedImage() {
        return createdImage;
    }

    public void setCreatedImage(ImageResource createdImage) {
        this.createdImage = createdImage;
    }
}
