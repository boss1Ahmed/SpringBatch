package com.example.tpbatch.config;

import com.example.tpbatch.dto.CompteTransactionDto;
import com.example.tpbatch.model.Transaction;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.support.DefaultBatchConfiguration;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;



@Configuration
@EnableBatchProcessing
public class SpringBatchConfig extends DefaultBatchConfiguration {



    @Bean
    public Job job(ItemReader<CompteTransactionDto> reader,
                   ItemProcessor<CompteTransactionDto, Transaction> itemProcessor,
                   ItemWriter<Transaction> itemWriter){
        Step step = new StepBuilder("ETL-Read",jobRepository())
                .<CompteTransactionDto, Transaction>chunk(100,getTransactionManager())
                .reader(reader)
                .processor(itemProcessor)
                .writer(itemWriter)
                .build();
        return new JobBuilder("ETL-Load",jobRepository())
                .incrementer(new RunIdIncrementer())
                .start(step)
                .build();
    }

    @Bean
    public FlatFileItemReader<CompteTransactionDto> fileItemReader(){
        FlatFileItemReader<CompteTransactionDto> fileItemReader = new FlatFileItemReader<>();
        fileItemReader.setResource(new ClassPathResource("transactions.csv"));
        fileItemReader.setName("CSV-Reader");
        fileItemReader.setLinesToSkip(1);
        fileItemReader.setLineMapper(lineMapper());
        return fileItemReader;
    }

    @Bean
    public LineMapper<CompteTransactionDto> lineMapper() {
        DefaultLineMapper<CompteTransactionDto> defaultLineMapper =  new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("idTransaction","idCompte","montant","dateTransaction");

        BeanWrapperFieldSetMapper<CompteTransactionDto> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(CompteTransactionDto.class);

        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }




}
