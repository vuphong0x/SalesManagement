package com.teamone.salesmanagement.database;

public class ConstantKey {
    public final static String COLUMN_ID = "ID";

    //==============| Customer Model |==============
    public final static String CUSTOMER_TABLE_NAME ="customer_table";
    public final static String CUSTOMER_PHONE ="customer_phone";
    public final static String CUSTOMER_NAME ="customer_name";
    public final static String CUSTOMER_DATE_OF_BIRTH = "customer_date_of_birth";
    public final static String CUSTOMER_ADDRESS = "customer_address";

    protected final static String CREATE_CUSTOMER_TABLE = "CREATE TABLE "+CUSTOMER_TABLE_NAME +"("
            +COLUMN_ID +"INTEGER PRIMARY KEY AUTOINCREMENT, "
            +CUSTOMER_PHONE + "TEXT, "
            +CUSTOMER_NAME +"TEXT, "
            +CUSTOMER_DATE_OF_BIRTH + "TEXT, "
            +CUSTOMER_ADDRESS + "TEXT )";
    protected final static String DROP_CUSTOMER_TABLE = "DROP TABLE IF EXISTS "+ CUSTOMER_TABLE_NAME + "";
    public final static String SELECT_CUSTOMER_TABLE = "SELECT * FROM " + CUSTOMER_TABLE_NAME;

    //==============| Product Model |==============
    public final static String PRODUCT_TABLE_NAME = "product_table";
    public final static String PRODUCT_ID = "product_id";
    public final static String PRODUCT_NAME = "product_name";
    public final static String PRODUCT_PRICE = "product_price";
    public final static String PRODUCT_SIZE = "product_size";

    protected final static String CREATE_PRODUCT_TABLE = "CREATE TABLE" + PRODUCT_TABLE_NAME +"("
            +COLUMN_ID + "INTEGER PRIMARY KEY AUTOINCREMENT, "
            +PRODUCT_ID + "TEXT, "
            +PRODUCT_NAME + "TEXT, "
            +PRODUCT_PRICE + "TEXT, "
            +PRODUCT_SIZE + "TEXT )";
    protected final static String DROP_PRODUCT_TABLE = "DROP TABLE IF EXISTS "+PRODUCT_TABLE_NAME + "";
    public final static String SELECT_PRODUCT_TABLE = "SELECT * FROM " + PRODUCT_TABLE_NAME;
}
