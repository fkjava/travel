package org.fkjava.travel.commons.domain;

public class OperationResult {

    private boolean succeed;
    private String message;

    public OperationResult(boolean succeed) {
        super();
        this.succeed = succeed;
    }

    public OperationResult(boolean succeed, String message) {
        super();
        this.succeed = succeed;
        this.message = message;
    }

    public boolean isSucceed() {
        return succeed;
    }

    public void setSucceed(boolean succeed) {
        this.succeed = succeed;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
