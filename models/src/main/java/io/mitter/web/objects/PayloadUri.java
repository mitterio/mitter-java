package io.mitter.web.objects;

/**
 * @author Rohan Prabhu (rohan@mitter.io)
 */
public class PayloadUri {
    private String payloadUri;
    private String uriContext;

    public String getPayloadUri() {
        return payloadUri;
    }

    public PayloadUri setPayloadUri(String payloadUri) {
        this.payloadUri = payloadUri;
        return this;
    }

    public String getUriContext() {
        return uriContext;
    }

    public PayloadUri setUriContext(String uriContext) {
        this.uriContext = uriContext;
        return this;
    }
}
