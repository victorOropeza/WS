package com.epagoinc.client;

import com.epagoinc.clientswitchtransactionservicev2.ApplyVoidTransactionRequest;
import com.epagoinc.clientswitchtransactionservicev2.ApplyVoidTransactionResponse;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

class ExecuteApplyVoidTransaction {
    /*public void execute() {
        String clientSwitchVoidTransactionId = UUID.randomUUID().toString().toUpperCase();
        System.out.print("\n    clientSwitchVoidTransactionId: " + clientSwitchVoidTransactionId);
        System.out.print("\noriginalClientSwitchTransactionId: " + Globals.ClientSwitchTransactionId);
        System.out.print("\n       originalEPagoTransactionId: " + Globals.EPagoTransactionId);

        Calendar clientSwitchVoidTransactionDate = Calendar.getInstance();            // Get the current time

        // The following three lines are to format strDate for printout purposes only
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        f.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        String strDate = f.format(clientSwitchVoidTransactionDate.getTime());

        System.out.print("\n  clientSwitchVoidTransactionDate: " + strDate);

        String remarks = "Error del ?";

        System.out.print("\n                          remarks: " + remarks);


        ClientSwitchTransactionServiceV2 client = new ClientSwitchTransactionServiceV2(Globals.auth);
        ApplyVoidTransactionRequest request = new ApplyVoidTransactionRequest(
                clientSwitchVoidTransactionId,
                Globals.ClientSwitchTransactionId,
                Globals.EPagoTransactionId,
                clientSwitchVoidTransactionDate,
                remarks
        );
        ApplyVoidTransactionResponse response = null;

        /* Read the initial time. 
        DateTime startTime = new DateTime();

        try {
            response = client.applyVoidTransaction(request);
        } catch (RemoteException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        /* Read the end time. 
        DateTime stopTime = new DateTime();

            /* Compute the duration between the initial and the end time. 
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

        System.out.print("\n\n                    Response Time: " + elapsed);
        System.out.print(  "\n                       statusCode: " + response.getStatusCode());
        System.out.print(  "\n                    statusMessage: " + response.getStatusMessage());
    }*/
}
