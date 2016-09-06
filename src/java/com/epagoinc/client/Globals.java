package com.epagoinc.client;

import com.epagoinc.clientcatalogqueryservice.AuthorizationHeader;

import java.io.FileInputStream;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;


public class Globals {
    // Transaction State
    //public static String ClientSwitchTransactionId = null;
    //public static String EPagoTransactionId = null;
    //public static String ConceptCode = null;
    //public static String Account = null;
    //public static BigDecimal SubTotalAmount = new BigDecimal(-0.01);
    //public static BigDecimal TotalAmount = new BigDecimal(0.00);

    //public static String ClientSwitchNotificationId = null;
    //public static String EPagoNotificationId = null;

    //public static boolean NewTransactionApplied = false;
    public static long startTime;
    public static String branchname = "SAECV";

    public static int MaximumNumberOfAvailableConcepts = 0;
    public static int MaximumNumberOfBankAccounts = 0;

    public static ArrayList<String> AvailableConceptCodes = new ArrayList<String>();
    public static ArrayList<String> AvailableConceptShortNames = new ArrayList<String>();
    public static HashMap<String, Boolean> AvailableConceptsThatHaveQuery = new HashMap<String, Boolean>();

    public static ArrayList<String> AccountNumbers = new ArrayList<String>();
    public static ArrayList<String> BankCodes = new ArrayList<String>();

    public static ArrayList<String> ReferenceNames = new ArrayList<String>();
    public static HashMap<String, String> ReferenceLabels = new HashMap<String, String>();
    public static HashMap<String, String> ReferenceTypes = new HashMap<String, String>();
    public static HashMap<String, String> ReferenceCurrentValues = new HashMap<String, String>();
    public static HashMap<String, String> ReferenceDefaultValues = new HashMap<String, String>();

    //public static boolean JustDidSuccessfulQuery = false;

    // Console.Write("\n    possibleValues: " + reference.possibleValues);

    public static AuthorizationHeader auth = new AuthorizationHeader();

    static {
        //load a properties file
        PropertiesLoader settings = new PropertiesLoader("app.config.properties");
        auth.setClientSwitchUser("agenerico");
        auth.setClientSwitchPassword("Prueba2015$");
        auth.setClientSwitchChannel("DEV001");

        auth.setTerminalId("00100001");
        auth.setTerminalDescription("Switch Concentrador: Cadena Comercial");
        auth.setCashierName("G-POS1");
    }

    public static String ClientCatalogQueryAddress;
    public static String ClientSwitchDepositNotificationServiceAddress;
    public static String ClientSwitchTransactionServiceAddress;
    public static String ClientSwitchAccountBalanceServiceAddress;

    public static class PropertiesLoader {
        Properties prop = new Properties();

        public PropertiesLoader(String file) {

            try {
                //load a properties file
                prop.load(new FileInputStream(file));
                //prop.load(Globals.class.getResourceAsStream(file));
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }

        public String get(String key) {
            return prop.getProperty(key);
        }

        public boolean exists(String key) {
            return prop.getProperty(key) != null;
        }
    }

    public static String NoNull(String value ) {
        return (null == value ? "" : value );
    }
}
