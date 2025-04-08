package com.vaddi.grpc.stock_trading_client;

import org.springframework.stereotype.Service;

import com.vaddi.stocktrading.StockRequest;
import com.vaddi.stocktrading.StockResponse;
import com.vaddi.stocktrading.StockTradingServiceGrpc;

import net.devh.boot.grpc.client.inject.GrpcClient;

@Service
public class StockClientService {

	
	//Retrieve gRPC - UNARY 
	

    @GrpcClient("stockService")
    private StockTradingServiceGrpc.StockTradingServiceBlockingStub serviceBlockingStub;

    public StockResponse getStockPrice(String stockSymbol) {
        StockRequest request = StockRequest.newBuilder().setStockSymbol(stockSymbol).build();
        return serviceBlockingStub.getStockPrice(request);
    }
}
