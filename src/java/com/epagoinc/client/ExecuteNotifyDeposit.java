package com.epagoinc.client;

import com.epagoinc.clientswitchdepositnotificationservice.NotifyDepositRequest;
import com.epagoinc.clientswitchdepositnotificationservice.NotifyDepositResponse;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Random;
import java.util.TimeZone;
import java.util.UUID;

class ExecuteNotifyDeposit {
    /*public void execute() {
        String clientSwitchNotificationId = UUID.randomUUID().toString().toUpperCase();

        Globals.ClientSwitchNotificationId = clientSwitchNotificationId; // for ExecuteVoidNotification.java usage

        System.out.print("\n  clientSwitchNotificationId: " + clientSwitchNotificationId);

        // The following four lines are to format strDate for printout purposes only
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
        f.setTimeZone(TimeZone.getTimeZone("GMT-5"));
        Calendar clientSwitchNotificationDate = Calendar.getInstance();            // Get the current time
        String strDate = f.format(clientSwitchNotificationDate.getTime());

        System.out.print("\nclientSwitchNotificationDate: " + strDate);

        int maxElement = Globals.MaximumNumberOfBankAccounts;
        Random random = new Random();
        int rInt = random.nextInt(maxElement);

        String accountNumber = Globals.AccountNumbers.get(rInt);
        String bankCode = Globals.BankCodes.get(rInt);

        System.out.print("\n               accountNumber: " + accountNumber);
        System.out.print("\n                    bankCode: " + bankCode);

        String reference = "";

        for (int i = 0; i < 10; i++) {
            reference = String.format("%d", random.nextInt(9)).concat(reference);
        }

        System.out.print("\n                   reference: " + reference);

        System.out.print("\n                      amount: ");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String inputBuffer = null;
        try {
            inputBuffer = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        BigDecimal amount = null;
        try {
            amount = new BigDecimal(inputBuffer);
        } catch (Exception e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }

        ClientSwitchDepositNotificationService client = new ClientSwitchDepositNotificationService(Globals.auth);
        NotifyDepositRequest request = new NotifyDepositRequest(
                clientSwitchNotificationId,
                clientSwitchNotificationDate,
                amount,
                accountNumber,
                bankCode,
                reference);
        NotifyDepositResponse response = null;

        /* Read the initial time. 
        DateTime startTime = new DateTime();

        try {
            response = client.notifyDeposit(request);
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

        /* Compute the duration between the initial and the end time. 
        String elapsed = formatter.print(duration);

        System.out.print("\n               Response Time: " + elapsed);
        System.out.print("\n                  statusCode: " + response.getStatusCode());
        System.out.print("\n               statusMessage: " + response.getStatusmessage());

        if (response.getStatusCode() == 0) {
            System.out.print("\n-------------------------------------------------------------------------------");
            System.out.print("\n         ePagoNotificationId: " + response.getEPagoNotificationId());

            Globals.ClientSwitchNotificationId = clientSwitchNotificationId;   // for VoidNotification.cs
            Globals.EPagoNotificationId = response.getEPagoNotificationId();                 // idem
        }
    }*/
}
