package com.epagoinc.client;

import com.epagoinc.clientswitchdepositnotificationservice.VoidNotificationRequest;
import com.epagoinc.clientswitchdepositnotificationservice.VoidNotificationResponse;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.UUID;

class ExecuteVoidNotification {
    /*public void execute() {

        String originalClientSwitchNotificationId = Globals.ClientSwitchNotificationId;
        System.out.print("\noriginalclientSwitchNotificationId: " + originalClientSwitchNotificationId);

        String originalEPagoNotificationId = Globals.EPagoNotificationId;
        System.out.print("\n       originalEPagoNotificationId: " + originalEPagoNotificationId);

        String clientSwitchVoidNotificationId = UUID.randomUUID().toString().toUpperCase();
        System.out.print("\n    clientSwitchVoidNotificationId: " + clientSwitchVoidNotificationId);

        // The following four lines are to format strDate for printout purposes only
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        f.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        Calendar clientSwitchVoidNotificationDate = Calendar.getInstance();            // Get the current time
        String strDate = f.format(clientSwitchVoidNotificationDate.getTime());

        System.out.print("\n  clientSwitchVoidNotificationDate: " + strDate);

        String remarks = "Error del ?";

        System.out.print("\n                           remarks: " + remarks);

        ClientSwitchDepositNotificationService client = new ClientSwitchDepositNotificationService(Globals.auth);
        VoidNotificationRequest request = new VoidNotificationRequest(
                originalClientSwitchNotificationId,
                originalEPagoNotificationId,
                clientSwitchVoidNotificationId,
                clientSwitchVoidNotificationDate,
                remarks);

        VoidNotificationResponse response = null;

        /* Read the start time. 
        DateTime startTime = new DateTime();

        try {
            response = client.voidNotification(request);
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

        System.out.print("\n\n                     Response Time: " + elapsed);
        System.out.print("\n                        statusCode: " + response.getStatusCode());
        System.out.print("\n                     statusMessage: " + response.getStatusmessage());
    }*/
}
