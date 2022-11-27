package com.cinemastore.mediaservice.grpc;

import com.cinemastore.mediaservice.dto.ResponseMediaDTO;
import com.cinemastore.mediaservice.exception.NoSuchMediaException;
import com.cinemastore.mediaservice.service.MongoMediaService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;

@GRpcService
public class MediaIdService extends StatusMessageServiceGrpc.StatusMessageServiceImplBase {

    private final MongoMediaService mongoService;

    public MediaIdService(MongoMediaService mongoService) {
        this.mongoService = mongoService;
    }

    @Override
    public void getStatus(MessageTO.MessageRequest request, StreamObserver<MessageTO.MessageResponse> responseObserver) {
        boolean status = false;
        String title = "";

        try {
            ResponseMediaDTO media = getMediaFromDb(request.getId());
            status = true;
            title = media.getTitle();
        } catch (NoSuchMediaException e) {
            System.err.println("Media doesn't exist");
        } finally {
            responseObserver.onNext(MessageTO.MessageResponse
                    .newBuilder()
                    .setStatus(status)
                    .setTitle(title)
                    .build());
            responseObserver.onCompleted();
        }
    }

    private ResponseMediaDTO getMediaFromDb(String id) throws NoSuchMediaException {
        return mongoService.findById(id);
    }

}
