package com.vaddi.grpc.stock_trading_client;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import net.devh.boot.grpc.client.channelfactory.GrpcChannelFactory;

@SpringBootApplication
public class StockTradingClientApplication implements CommandLineRunner{

	private StockClientService stockClientService;
	
	private GrpcChannelFactory grpcChannelFactory;

	public StockTradingClientApplication(StockClientService stockClientService,GrpcChannelFactory grpcChannelFactory ) {
		this.stockClientService = stockClientService;
		this.grpcChannelFactory = grpcChannelFactory;
	}

	public static void main(String[] args) {
		SpringApplication.run(StockTradingClientApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("Grpc client response : "+stockClientService.getStockPrice("RAG") );
	}

}
