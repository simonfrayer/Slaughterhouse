package via.sdj3.slaughterhouse.gRPCServer;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import via.sdj3.slaughterhouse.protobuf.Product;
import via.sdj3.slaughterhouse.protobuf.SlaughterhouseServerGrpc;

public class TestClient {
    public static void main(String[] args) {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8060)
                .usePlaintext()
                .build();

        SlaughterhouseServerGrpc.SlaughterhouseServerBlockingStub stub
                = SlaughterhouseServerGrpc.newBlockingStub(channel);
    }
}
