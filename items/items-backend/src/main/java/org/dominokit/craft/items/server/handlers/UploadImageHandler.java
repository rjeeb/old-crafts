package org.dominokit.craft.items.server.handlers;

import io.reactivex.Observable;
import io.reactivex.Single;
import org.dominokit.craft.items.server.UseCaseFactory;
import org.dominokit.craft.items.shared.model.ImageResource;
import org.dominokit.craft.items.shared.model.ImageResource_MapperImpl;
import org.dominokit.craft.response.UploadImageResponse;
import org.dominokit.domino.api.server.context.ExecutionContext;
import org.dominokit.domino.api.server.fileuploads.FileUpload;
import org.dominokit.domino.api.server.handler.Handler;
import org.dominokit.domino.api.server.handler.RequestHandler;
import org.dominokit.domino.api.shared.request.VoidRequest;

import java.util.Set;

@Handler("images/upload")
public class UploadImageHandler implements RequestHandler<VoidRequest, ImageResource> {

    @Override
    public void handleRequest(ExecutionContext<VoidRequest, ImageResource> executionContext) {
        Set<FileUpload> fileUploads = executionContext.fileUploads();
        Observable.fromIterable(fileUploads)
                .map(this::createImageResource)
                .flatMapSingle(this::executeUploadUseCase)
                .map(UploadImageResponse::getCreatedImage)
                .doOnSubscribe(disposable -> executionContext.endHandler(disposable::dispose))
                .doOnError(throwable -> handleError(executionContext, throwable))
                .doOnNext(response -> returnResponse(executionContext, response))
                .subscribe();
    }

    private void handleError(ExecutionContext<VoidRequest, ImageResource> routingContext, Throwable throwable) {
        routingContext.statusCode(400).end(throwable.getMessage());
    }

    private void returnResponse(ExecutionContext<VoidRequest, ImageResource> routingContext, ImageResource response) {
        routingContext
                .statusCode(200)
                .end(ImageResource_MapperImpl.INSTANCE.write(response));
    }

    private Single<UploadImageResponse> executeUploadUseCase(ImageResource imageResource) {
        return UseCaseFactory.create()
                .uploadImageUseCase()
                .execute(imageResource);
    }

    private ImageResource createImageResource(FileUpload fileUpload) {
        ImageResource imageResource = new ImageResource();
        imageResource.setImagePath(fileUpload.uploadedFileName());
        return imageResource;
    }
}
