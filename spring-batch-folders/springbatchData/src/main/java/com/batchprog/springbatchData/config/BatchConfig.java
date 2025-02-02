package com.batchprog.springbatchData.config;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.PlatformTransactionManager;

import com.batchprog.springbatchData.entity.Customer;
import com.batchprog.springbatchData.processor.CustomerProcesser;
import com.batchprog.springbatchData.processor.ProductProcessor;
import com.batchprog.springbatchData.repository.CustomerRepository;
import com.batchprog.springbatchData.repository.Product;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
@NoArgsConstructor
public class BatchConfig {

	@Bean
	public Job jobA() {
		return new JobBuilder("JobA", jobRepository)
				.incrementer(new RunIdIncrementer())
				.listener(listener())
				.start(stepA())
				.next(stepB())
				.build();
	}

	@Bean
	public JobExecutionListener listener() {
		return new JobExecutionListener() {
			public void beforeJob(JobExecution jobExecution) {
				System.out.println("Started Date and Time : ->" + new Date());
				System.out.println("Status at Starting: ->" + jobExecution.getStatus());
			}

			public void afterJob(JobExecution jobExecution) {
				System.out.println("End Date and Time : ->" + new Date());
				System.out.println("Status at Ending: ->" + jobExecution.getStatus());
			}
		};
	}


	@Autowired
	private JobRepository jobRepository;

	@Autowired
	private PlatformTransactionManager transactionManager;

	@Bean
	public Step stepA() {
		return new StepBuilder("stepA", jobRepository)
				.<Product, Product>chunk(10, transactionManager)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
	}


	@Bean
	public FlatFileItemReader<Product> reader(){
		FlatFileItemReader<Product> reader = new FlatFileItemReader<>();
		reader.setResource(new ClassPathResource("test.csv"));
		reader.setName("step1File");
		reader.setLinesToSkip(1);
		reader.setLineMapper(new DefaultLineMapper<>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setDelimiter(DELIMITER_COMMA);
				setNames("prodId","prodCode","prodCost");
			}});

			setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
				setTargetType(Product.class);
			}});
		}});

		return reader;
	}

	@SuppressWarnings("hiding")
	@Bean
	public ItemProcessor processor(){
		/*
		 * return item -> { double cost = item.getProdCost(); item.setProdDisc(cost *
		 * 12/100.0); item.setProdGst(cost * 13/100.0); return item; };
		 */
		ItemProcessor<Product, Product> itemProcessor;
		itemProcessor = new ProductProcessor();
		return new ProductProcessor();
	}
	@Autowired
	private DataSource dataSource;

	@Bean
	public JdbcBatchItemWriter<Product> writer(){
		JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<>();
		writer.setDataSource(dataSource);
		writer.setSql("INSERT INTO PRODUCT(prodId,prodCode,prodCost,prodDisc,prodGst) values (:prodId,:prodCode,:prodCost,:prodDisc,:prodGst)");
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
		return writer;
	}

	@Bean
	public void initilaizeBatchDatabaseScripts() {
		try( Connection con = dataSource.getConnection()) {
			System.out.println("Verify batch script table ... "); 
			ResultSet results = con.getMetaData().getTables(null, null, "BATCH_JOB_INSTANCE", null);
			if(!results.next()) { 
				System.out.println("Started executed the batch default scripts ...");
				ScriptUtils.executeSqlScript(con, new ClassPathResource("batch-default-scripts.sql"));
			}
		} catch(SQLException e) {
			throw new RuntimeException("Failed to initialize the batch database scripts ... "+ e);
		}
	}

	private Step stepB() {
		return new StepBuilder("stepB", jobRepository)
				.<Customer, Customer>chunk(10, transactionManager)
				.reader(reader1())
				.processor(processor1())
				.writer(writer1())
				.build();
	}

	private ItemProcessor<Customer, Customer> processor1() {
		ItemProcessor<Customer, Customer> itemProcessor;
		itemProcessor = new CustomerProcesser();
		return new CustomerProcesser();
	}

	private FlatFileItemReader<Customer> reader1() {
		FlatFileItemReader<Customer> fileReader = new FlatFileItemReader<>();
		fileReader.setResource(new FileSystemResource("src/main/resources/customer.csv"));
		fileReader.setName("csvReader");
		fileReader.setLinesToSkip(1);

		fileReader.setLineMapper(new DefaultLineMapper<>() {{
			setLineTokenizer(new DelimitedLineTokenizer() {{
				setDelimiter(DELIMITER_COMMA);
				setNames("id","first_name","last_name","gender","contact");
			}});

			setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
				setTargetType(Customer.class);
			}});
		}});
		return fileReader;
	}

	@Autowired
	private CustomerRepository customerRepository;
	private ItemWriter<Customer> writer1() {
		RepositoryItemWriter<Customer> writer = new RepositoryItemWriter<>();
		writer.setRepository(customerRepository);
		writer.setMethodName("save");
		return writer;
	}
}