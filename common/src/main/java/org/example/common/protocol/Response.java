package org.example.common.protocol;

public class Response {
    private boolean ok;
    private Object result;
    private String error;

    public Response() { }

    public Response(boolean ok, Object result, String error) {
        this.ok     = ok;
        this.result = result;
        this.error  = error;
    }

    public boolean isOk() {
        return ok;
    }
    public Object getResult() {
        return result;
    }
    public String getError() {
        return error;
    }

    public void setOk(boolean ok) {
        this.ok = ok;
    }
    public void setResult(Object result) {
        this.result = result;
    }
    public void setError(String error) {
        this.error = error;
    }
}
