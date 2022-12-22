package com.topcualperen.core.utilities.results;

public class DataResult<T> extends Result{

    private T data; // Birden fazla veri tipi ile (ürün,category vb) çalıştığımız için generic yazarız
    public DataResult(T data, boolean success, String message) {
        super(success, message);
        this.data = data;
    }

    public DataResult(T data, boolean success) {
        super(success);
        this.data = data;
    }

    public DataResult(T data, String message){
        super(message);
        this.data = data;
    }

    public DataResult(T data){
        this.data = data;
    }

    public T getData(){
        return this.data;
    }

}

