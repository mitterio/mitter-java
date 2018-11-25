package io.mitter.data.domain.application.properties;

/**
 * Placeholder for standard application property names referenced throughout the application.
 *
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public interface StandardApplicationProperty {
    String GoogleApiCredential = "google-api-credential";
    String GoogleOAuthCredential = "google-oauth-credential";
    String GoogleServiceAccountCredential = "google-service-account-credential";
    String AwsAccessCredentials = "aws-access-credentials";
    String ApnsProperties = "apns-properties";
    String FcmProperties = "fcm-properties";
    String OAuthIntegrationProperty = "oauth-integration";
    String TransactionalWebhookProperty = "transactional-webhook";
    String AwsSqsQueueProperty = "aws-sqs-queue";
    String AwsSnsTopicProperty = "aws-sns-topic";
    String AwsSqsEventBusProperty = "aws-sqs-event-bus";
    String AwsSnsEventBusProperty = "aws-sns-event-bus";
    String NonStandardProperty = "";
}
