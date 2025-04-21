package org.example.common.protocol;

import java.util.List;

public class Request {
    private String commandName;
    private List<String> args;
    private Object payload;

    public Request() { }

    public Request(String commandName, List<String> args, Object payload) {
        this.commandName = commandName;
        this.args      = args;
        this.payload   = payload;
    }

    public String getCommandName() {
        return commandName;
    }
    public List<String> getArgs() {
        return args;
    }
    public Object getPayload() {
        return payload;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }
    public void setArgs(List<String> args) {
        this.args = args;
    }
    public void setPayload(Object payload) {
        this.payload = payload;
    }
}
