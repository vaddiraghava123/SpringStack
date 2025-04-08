package com.vaddi.grpc.stock_trading_server;

import org.springframework.stereotype.Service;

import com.vaddi.stocktrading.StockRequest;
import com.vaddi.stocktrading.StockResponse;
import com.vaddi.stocktrading.StockTradingServiceGrpc;

import io.grpc.stub.StreamObserver;

@Service
public class StockServiceImpl extends StockTradingServiceGrpc.StockTradingServiceImplBase {

    private final StockRepository stockRepository;

    public StockServiceImpl(StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Override
    public void getStockPrice(StockRequest request,
                              StreamObserver<StockResponse> responseObserver) {

        //stockName -> DB -> map response -> return
        String stockSymbol = request.getStockSymbol();
        Stock stockEntity = stockRepository.findByStockSymbol(stockSymbol);

        StockResponse stockResponse = StockResponse.newBuilder()
                .setStockSymbol(stockEntity.getStockSymbol())
                .setPrice(stockEntity.getPrice())
                .setTimestamp(stockEntity.getLastUpdated().toString())
                .build();

        responseObserver.onNext(stockResponse);
        responseObserver.onCompleted();

    }
}
