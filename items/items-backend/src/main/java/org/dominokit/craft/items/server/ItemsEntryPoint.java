package org.dominokit.craft.items.server;

import com.google.auto.service.AutoService;
import io.vertx.ext.web.FileUpload;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.items.shared.model.ImageResources;
import org.dominokit.craft.items.shared.model.ImageResources_MapperImpl;
import org.dominokit.domino.api.server.entrypoint.ServerAppEntryPoint;
import org.dominokit.domino.api.server.entrypoint.VertxContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@AutoService(ServerAppEntryPoint.class)
public class ItemsEntryPoint implements ServerAppEntryPoint<VertxContext> {
    @Override
    public void onModulesLoaded(VertxContext context) {
        context.router().post("/images/upload")
                .handler(routingContext -> {
                    Set<FileUpload> fileUploads = routingContext.fileUploads();
                    List<ImageResource> imageResourcesList = fileUploads.stream()
                            .map(this::createImageResource)
                            .map(this::executeUploadUseCase)
                            .collect(Collectors.toList());

                    ImageResources imageResources = new ImageResources();
                    imageResources.setImageResources(imageResourcesList);
                    routingContext.response().end(ImageResources_MapperImpl.INSTANCE.write(imageResources));
                });
    }

    private ImageResource executeUploadUseCase(ImageResource imageResource) {
        return UseCaseFactory.getInstance()
                .uploadImageUseCase()
                .execute(imageResource)
                .getCreatedImage();
    }

    private ImageResource createImageResource(FileUpload fileUpload) {
        ImageResource imageResource = new ImageResource();
        imageResource.setImagePath(fileUpload.uploadedFileName());
        return imageResource;
    }
}
