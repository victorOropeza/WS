package com.epagoinc.client;

import com.epagoinc.clientswitchdepositnotificationservice.AgentDeposit;
import com.epagoinc.clientswitchdepositnotificationservice.AgentPendingDepositsRequest;
import com.epagoinc.clientswitchdepositnotificationservice.AgentPendingDepositsResponse;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.rmi.RemoteException;

class ExecuteAgentPendingDeposits {
    public void execute() {

        ClientSwitchDepositNotificationService client = new ClientSwitchDepositNotificationService(Globals.auth);
        AgentPendingDepositsRequest request = new AgentPendingDepositsRequest();
        AgentPendingDepositsResponse response = null;

        /* Read the start time. */
        DateTime startTime = new DateTime();

        try {
            response = client.agentPendingDeposits(request);
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

        System.out.print("\n              Response Time: " + elapsed);
        System.out.print("\n                 statusCode: " + response.getStatusCode());
        System.out.print("\n              statusMessage: " + response.getStatusMessage());

        if (response.getStatusCode() == 0) {

            System.out.print("\n-------------------------------------------------------------------------------");

            // pendingDeposits
            System.out.print("\n<pendingDeposits>");
            for (AgentDeposit pendingDeposit : response.getPendingDeposits()) {
                System.out.print("\n\n    <agentDeposit>");
                System.out.print("\n       clientNotificationId: " + pendingDeposit.getClientNotificationId());
                System.out.print("\n        ePagoNotificationId: " + pendingDeposit.getEPagoNotificationId());
                System.out.print("\n                  bank code: " + pendingDeposit.getBank().getCode());
                System.out.print("\n                  bank name: " + pendingDeposit.getBank().getName());
                System.out.print("\n           bank routeNumber: " + pendingDeposit.getBank().getRouteNumber());
                System.out.print("\n                     amount: " + pendingDeposit.getAmount());
                System.out.print("\n                  reference: " + pendingDeposit.getReference());
            }
        }
    }
}

