package org.example.client;

import org.example.common.protocol.Response;

/** Выводит в консоль результат или сообщение об ошибке. */
public class ResponseHandler { //На вход ему приходит наш DTO Response(ok, result, error), и он выводит либо результат, либо ошибку
    public void handle(Response res) {
        if (res.isOk()) {
            if (res.getResult() != null) {
                System.out.println(res.getResult());
            }
        } else {
            System.err.println("Error: " + res.getError());
        }
    }
}


