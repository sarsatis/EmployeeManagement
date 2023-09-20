 package com.employee.employeemanagement.config;

 import lombok.AllArgsConstructor;
 import org.springframework.batch.core.Job;
 import org.springframework.batch.core.Step;

 import org.springframework.batch.core.job.builder.JobBuilder;
 import org.springframework.batch.core.launch.support.RunIdIncrementer;
 import org.springframework.batch.core.repository.JobRepository;
 import org.springframework.batch.core.step.builder.StepBuilder;
 import org.springframework.batch.item.data.RepositoryItemWriter;
 import org.springframework.batch.item.file.FlatFileItemReader;
 import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
 import org.springframework.batch.item.file.mapping.DefaultLineMapper;
 import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;

 import org.springframework.context.annotation.Bean;
 import org.springframework.context.annotation.Configuration;
 import org.springframework.core.io.ClassPathResource;
 import org.springframework.transaction.PlatformTransactionManager;

import com.employee.employeemanagement.batch.utility.EmployeeProcessor;
import com.employee.employeemanagement.model.Employee;
import com.employee.employeemanagement.repository.EmployeeRepository;


 /**
  * @author Sarthak Satish
  *
  * @class SpringBatchConfig
  *
  * @description This is a Configuration File which performs batch operation to read all the employee details from CSV in class path and insert
  * into mysql db
  *
  */
 @Configuration
 @AllArgsConstructor
 public class SpringBatchConfig {

     private EmployeeRepository employeeRepository;

	
 	@Bean
     public FlatFileItemReader<Employee> reader() {
         FlatFileItemReader<Employee> reader = new FlatFileItemReader<>();
         reader.setResource(new ClassPathResource("employees.csv"));
         reader.setLinesToSkip(1);
         reader.setLineMapper(new DefaultLineMapper<>() {{
             setLineTokenizer(new DelimitedLineTokenizer() {{
                 setNames(new String[] { "employeename", "title","businessunit","place","supervisorid","competencies","salary"});
             }});
             setFieldSetMapper(new BeanWrapperFieldSetMapper() {{
                 setTargetType(Employee.class);
             }});
         }});
         return reader;
     }


     @Bean
     public EmployeeProcessor processor() {
         return new EmployeeProcessor();
     }
    
     @Bean
     public RepositoryItemWriter<Employee> writer() {
         RepositoryItemWriter<Employee> writer = new RepositoryItemWriter();
         writer.setRepository(employeeRepository);
         writer.setMethodName("save");
         return writer;
     }


     @Bean
     public Step step1(JobRepository jobRepository, PlatformTransactionManager platformTransactionManager) {
         return new StepBuilder("csv-step",jobRepository)
                 .<Employee, Employee> chunk(10,platformTransactionManager)
                 .reader(reader())
                 .processor(processor())
                 .writer(writer())
                 .build();
     }

     @Bean
     public Job insertEmployeeJob(JobRepository jobRepository,PlatformTransactionManager platformTransactionManager) {
         return new JobBuilder("insertEmployeeJob",jobRepository)
                 .incrementer(new RunIdIncrementer())
                 .flow(step1(jobRepository,platformTransactionManager))
                 .end()
                 .build();
     }


 }