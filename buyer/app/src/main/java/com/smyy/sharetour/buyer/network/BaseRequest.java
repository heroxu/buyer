package com.smyy.sharetour.buyer.network;

public class BaseRequest {

    private String grant_type;
    private String scope;

    public BaseRequest() {
        this.setGrant_type("");
        this.setScope("");
    }

    public BaseRequest(String grant_type, String scope) {
        this.setGrant_type(grant_type);
        this.setScope(scope);
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
