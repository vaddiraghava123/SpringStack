package com.batchprog.springbatchData.partitioning;

import java.util.HashMap;
import java.util.Map;

import org.springframework.batch.core.partition.support.Partitioner;
import org.springframework.batch.item.ExecutionContext;

public class CustomerParition implements Partitioner{

	@Override
	public Map<String, ExecutionContext> partition(int gridSize) {
		int min =1;
		int max= 2000;
		int targetSize = (max - min)/gridSize + 1;//
		
		int num =0;
		int start = min;
		int end = start + targetSize -1;
		
		Map<String,ExecutionContext> result = new HashMap();
		while(start <= max) {
			ExecutionContext value = new ExecutionContext();
			result.put("partition - "+num, value);
			if(end >= max) {
				end = max;
			}
			
			value.putInt("minvalue", start);
			value.putInt("maxvalue", end);
			
			start += targetSize;
			end += targetSize;
			
			num++;
		}
		
		return result;
	}

}
