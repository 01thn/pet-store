package com.cinemastore.privateservice.grpc.impl;

import com.cinemastore.mediaservice.grpc.MessageTO;
import com.cinemastore.mediaservice.grpc.StatusMessageServiceGrpc;
import com.cinemastore.privateservice.grpc.MediaIdClient;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.springframework.stereotype.Service;

@Service
public class MediaIdClientImpl implements MediaIdClient {

    private static final String HOST = "localhost";
    private static final int PORT = 7777;
    private static final String TARGET = HOST + ":" + PORT;

    private StatusMessageServiceGrpc.StatusMessageServiceBlockingStub stub;

    public MediaIdClientImpl() {
        ManagedChannel channel = ManagedChannelBuilder.forTarget(TARGET)
                .usePlaintext()
                .build();
        stub = StatusMessageServiceGrpc.newBlockingStub(channel);
    }

    @Override
    public String getStatus(String id) {
        MessageTO.MessageResponse status = stub.getStatus(MessageTO.MessageRequest
                .newBuilder()
                .setId(id)
                .build());

        return status.getStatus() ? status.getTitle() : null;
    }
}
