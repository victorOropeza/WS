package com.epagoinc.client;

import com.epagoinc.clientswitchdepositnotificationservice.AgentBalanceQueryRequest;
import com.epagoinc.clientswitchdepositnotificationservice.AgentBalanceQueryResponse;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.rmi.RemoteException;

class ExecuteAgentBalanceQuery {
    public void execute() {
        ClientSwitchDepositNotificationService client = new ClientSwitchDepositNotificationService(Globals.auth);
        AgentBalanceQueryRequest request = new AgentBalanceQueryRequest();
        AgentBalanceQueryResponse response = null;

        /* Read the initial time. */
        DateTime startTime = new DateTime();

        try {
            response = client.agentBalanceQuery(request);
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
        System.out.print("\n           statusCode: " + response.getStatusCode());
        System.out.print("\n        statusMessage: " + response.getStatusMessage());

        if (response.getStatusCode() == 0) {
            System.out.print("\n-------------------------------------------------------------------------------");
            System.out.print("\n       currentBalance: " + response.getCurrentBalance());
            System.out.print("\n           dueBalance: " + response.getDueBalance());
            System.out.print("\n           minPayment: " + response.getMinPayment());
            System.out.print("\npendingDepositsAmount: " + response.getPendingDepositsAmount());
        }
    }
}
