package com.amalitech.dydamodbtodoapp.dynamodb_todo_app.config;

import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DynamoDBConfig {

    @Value("${app.aws.dynamodb-endpoint}")
    private String dynamodbEndpoint;
    @Value("${app.aws.region}")
    private String awsRegion;
    @Value("${app.aws.access-key}")
    private String awsAccessKey;
    @Value("${app.aws.secret-key}")
    private String awsSecretKey;

    @Bean
    public DynamoDBMapper dynamoDBMapper() {
        return new DynamoDBMapper(amazonDynamoDB());
    }

    private AmazonDynamoDB amazonDynamoDB() {
        return AmazonDynamoDBClientBuilder.standard()
                .withCredentials(new AWSStaticCredentialsProvider(
                        new BasicAWSCredentials(awsAccessKey, awsSecretKey)
                ))
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(
                        dynamodbEndpoint, awsRegion
                ))
                .build();
    }
}
