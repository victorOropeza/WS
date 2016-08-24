/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.epagoinc.client;

import com.epagoinc.clientswitchaccountbalanceservice.AccountBalanceQueryRequest;
import com.epagoinc.clientswitchaccountbalanceservice.AccountBalanceQueryResponse;
import com.epagoinc.clientswitchaccountbalanceservice.ArrayOfReferenceCollectionReferenceReference;
import com.epagoinc.clientswitchtransactionservicev2.ApplyTransactionRequest;
import com.epagoinc.clientswitchtransactionservicev2.ApplyTransactionResponse;
import com.epagoinc.clientswitchtransactionservicev2.CapturedReference;
import com.epagoinc.clientswitchtransactionservicev2.PaymentConcept;
import com.epagoinc.clientswitchtransactionservicev2.PaymentMethod;
import com.epagoinc.clientswitchtransactionservicev2.PaymentMethodPaymentType;
import com.epagoinc.clientswitchtransactionservicev2.PrepareTransactionRequest;
import com.epagoinc.clientswitchtransactionservicev2.PrepareTransactionResponse;
import com.epagoinc.clientswitchtransactionservicev2.PrintReference;
import com.epagoinc.clientswitchtransactionservicev2.TransactionPaymentMethod;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

/**
 *
 * @author Villa
 */
public class ApiClient {

    public Transaction tran = new Transaction();

    public AccountBalanceQueryResponse executeAccountBalanceQuery(String conceptCode, String account) {
        ClientSwitchAccountBalanceService client = new ClientSwitchAccountBalanceService(Globals.auth);
        AccountBalanceQueryRequest request = new AccountBalanceQueryRequest(conceptCode, account);
        AccountBalanceQueryResponse response = null;
        //tran = new Transaction();
        tran.setJustDidSuccessfulQuery(false);
        //Globals.JustDidSuccessfulQuery = false;

        /* Read the initial time. */
        DateTime startTime = new DateTime();

        try {
            response = client.accountBalanceQuery(request);
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        /* Read the end time. */
        DateTime stopTime = new DateTime();

        /* Compute the duration between the initial and the end time. */
        Period duration = new Period(startTime, stopTime);

        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .minimumPrintedDigits(2)
                .appendHours()
                .appendSeparator(":")
                .minimumPrintedDigits(2)
                .appendMinutes()
                .appendSeparator(":")
                .minimumPrintedDigits(2)
                .appendSeconds()
                .appendSeparator(".")
                .appendMillis()
                .toFormatter();

        String elapsed = formatter.print(duration);

        System.out.print("\n        Response Time: " + elapsed);
        System.out.print("\n           statusCode: " + response.getBalanceRecord().getStatusCode());
        System.out.print("\n        statusMessage: " + response.getBalanceRecord().getStatusMessage());

        if (response.getBalanceRecord().getStatusCode() == 0) {
            tran.setJustDidSuccessfulQuery(true);
            tran.setConceptCode(conceptCode); // for PrepareTransaction.cs usage next
            tran.setAccount(response.getBalanceRecord().getAccount());
            tran.setSubTotalAmount(response.getBalanceRecord().getAmount());

            System.out.print("\n-------------------------------------------------------------------------------");
            System.out.print("\n              account: " + response.getBalanceRecord().getAccount());
            System.out.print("\n               amount: " + response.getBalanceRecord().getAmount());
            System.out.print("\n          paymentRule: " + response.getBalanceRecord().getPaymentRule());

            for (ArrayOfReferenceCollectionReferenceReference conceptReference : response.getBalanceRecord().getConceptReferences()) {
                System.out.print("\n\n<conceptReference>");
                System.out.print("\n          displayName: " + conceptReference.getDisplayName());
                System.out.print("\n                 name: " + conceptReference.getName());
                System.out.print("\n                value: " + conceptReference.getValue());
            }
        }
        return response;
    }

    public ApplyTransactionResponse executeTransaction(String conceptCode, String account, BigDecimal subTotalAmount, String DV) {
        String clientSwitchTransactionId = UUID.randomUUID().toString().toUpperCase();

        tran.setClientSwitchTransactionId(clientSwitchTransactionId); // for ExecuteApplyTransaction.java usage
        System.out.println("\nclientSwitchTransactionId: " + clientSwitchTransactionId);

        PaymentConcept paymentConcept = new PaymentConcept();
        paymentConcept.setConceptCode(conceptCode);
        paymentConcept.setAccount(account);
        //BigDecimal subTotalAmount = new BigDecimal(0.0);

        ClientSwitchTransactionServiceV2 client = new ClientSwitchTransactionServiceV2(Globals.auth);
        PrepareTransactionRequest request = new PrepareTransactionRequest(
                clientSwitchTransactionId,
                paymentConcept,
                subTotalAmount);
        PrepareTransactionResponse response = null;
        ApplyTransactionResponse response2 = null;

        /* Read the initial time. */
        DateTime startTime = new DateTime();

        try {
            response = client.prepareTransaction(request);
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
        /* Read the end time. */
        DateTime stopTime = new DateTime();

        /* Compute the duration between the initial and the end time. */
        Period duration = new Period(startTime, stopTime);

        PeriodFormatter formatter = new PeriodFormatterBuilder()
                .printZeroIfSupported()
                .minimumPrintedDigits(2)
                .appendHours()
                .appendSeparator(":")
                .minimumPrintedDigits(2)
                .appendMinutes()
                .appendSeparator(":")
                .minimumPrintedDigits(2)
                .appendSeconds()
                .appendSeparator(".")
                .appendMillis()
                .toFormatter();

        String elapsed = formatter.print(duration);

        System.out.print("\n            Response Time: " + elapsed);
        System.out.print("\n               statusCode: " + response.getStatusCode());
        System.out.print("\n            statusMessage: " + response.getStatusMessage());

        tran.JustDidSuccessfulQuery = false;

        if (response.getStatusCode() == 0) {
            tran.setNewTransactionApplied(false);
            tran.setConceptCode(paymentConcept.getConceptCode());// for ApplyTransaction.cs usage next
            tran.setEPagoTransactionId(response.getEPagoTransactionId()); // For ApplayTransaction.cs
            tran.setTotalAmount(response.getTotalAmount());  // For ApplayTransaction.cs
            
            System.out.print("\n\n            ApplyTransaction");
            /*Globals.ReferenceNames.add(reference.getName());
            Globals.ReferenceLabels.put(reference.getName(), reference.getLabel());
            Globals.ReferenceTypes.put(reference.getName(), reference.getType());
            Globals.ReferenceCurrentValues.put(reference.getName(), reference.getCurrentValue());
            Globals.ReferenceDefaultValues.put(reference.getName(), reference.getDefaultValue());*/
            SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
            f.setTimeZone(TimeZone.getTimeZone("GMT-5"));
            Calendar clientSwitchTransactionDate = Calendar.getInstance();            // Get the current time
            String strDate = f.format(clientSwitchTransactionDate.getTime());
            TransactionPaymentMethod paymentBreakdown[] = new TransactionPaymentMethod[]{
                new TransactionPaymentMethod(
                new PaymentMethod(
                PaymentMethodPaymentType.Cash // paymentType
                , null // creditCardType
                ), tran.getTotalAmount() // amount
                , "" //documentNumber,
                , "" //authorizationCode,
                , "" //bankRoutingNumber,
                , null //Date documentDate
                )
            };

            CapturedReference[] capturedReferences = null;
            /*int i;
            for (i=0; i < 1; i++) {
                capturedReferences[i] = new CapturedReference("DV", DV);
            }
             */
            if (DV != null) {
                capturedReferences = new CapturedReference[1];
                capturedReferences[0] = new CapturedReference("DV", DV);
            }

            //ClientSwitchTransactionServiceV2 client2 = new ClientSwitchTransactionServiceV2(Globals.auth);
            ApplyTransactionRequest request2 = new ApplyTransactionRequest(tran.getClientSwitchTransactionId(), clientSwitchTransactionDate, tran.getEPagoTransactionId(), tran.getConceptCode(), tran.getTotalAmount(), paymentBreakdown, capturedReferences);
            
            /* Read the initial time. */
            DateTime startTime2 = new DateTime();

            try {
                Globals.startTime = System.currentTimeMillis();
                response2 = client.applyTransaction(request2);

            } catch (RemoteException e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }

            /* Read the end time. */
            DateTime stopTime2 = new DateTime();

            /* Compute the duration between the initial and the end time. */
            Period duration2 = new Period(startTime2, stopTime2);

            String elapsed2 = formatter.print(duration2);

            System.out.print("\n\n              Response Time: " + elapsed2);
            System.out.print("\n                 statusCode: " + response2.getStatusCode());
            System.out.print("\n              statusMessage: " + response2.getStatusMessage());

            tran.setJustDidSuccessfulQuery(false);

            if (response2.getStatusCode() == 0) {

                System.out.print("\n-------------------------------------------------------------------------------");
                System.out.print("\n  clientSwitchTransactionId: " + response2.getClientSwitchTransactionId());
                System.out.print("\n         ePagoTransactionId: " + response2.getEPagoTransactionId());

                // The following two lines are to format strDate for printout purposes only
                clientSwitchTransactionDate = response2.getEPagoTransactionDate();     // Get the time returned by the ePago Transactional Switch
                strDate = f.format(clientSwitchTransactionDate.getTime());

                System.out.print("\n       ePagoTransactionDate: " + strDate);
                System.out.print("\n           commissionAmount: " + response2.getCommissionAmount());

                tran.setNewTransactionApplied(true);

                System.out.print("\n\n<printReferences>");

                for (PrintReference printReference : response2.getPrintReferences()) {
                    System.out.print("\n\n    <reference>");
                    System.out.print("\n         label: ");
                    if ((printReference.getLabel().length() > 0) && (!"\n      ".equals(printReference.getLabel()))) {
                        System.out.print(printReference.getLabel());
                    }
                    System.out.print("\n         value: " + printReference.getValue());
                }

                System.out.print("\n\n       account: " + response2.getAccount());
            }
            
        }
        return response2;
    }
}
