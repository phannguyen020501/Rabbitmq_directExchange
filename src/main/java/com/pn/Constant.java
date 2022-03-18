package com.pn;

public final class Constant {

    //exchange
    public static final String EXCHANGE_NAME  = "direct_exchange";

    //routing key
    public static final String DEV_ROUTING_KEY = "devGroup";
    public static final String MANAGER_ROUTING_KEY = "managerGroup";
    public static final String GENERAL_ROUTING_KEY = "generalGroup";

    //queue
    public static final String DEV_QUEUE_NAME = "QDev";
    public static final String MANAGER_QUEUE_NAME = "QManager";
    public static final String GENERAL_QUEUE_NAME = "QManager";

    private Constant(){
        super();
    }
}
