package org.dominokit.craft.exception;

public class ImageNotFoundException extends RuntimeException {
    public ImageNotFoundException(String reference) {
        super(reference);
    }
}
