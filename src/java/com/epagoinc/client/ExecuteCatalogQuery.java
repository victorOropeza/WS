package com.epagoinc.client;

import com.epagoinc.clientcatalogqueryservice.*;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.format.PeriodFormatter;
import org.joda.time.format.PeriodFormatterBuilder;

import java.rmi.RemoteException;

class ExecuteCatalogQuery {

    public void execute() {

        ClientCatalogQuery client = new ClientCatalogQuery(Globals.auth);
        ExecuteCatalogQueryRequest request = new ExecuteCatalogQueryRequest();
        ExecuteCatalogQueryResponse response = null;

        /* Read the initial time. */
        DateTime startTime = new DateTime();

        try {
            response = client.executeCatalogQuery(request);
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

        //Globals.JustDidSuccessfulQuery = false;

        if (response.getStatusCode() == 0) {

            System.out.print("\n-------------------------------------------------------------------------------");

            // concepts
            System.out.print("\n\n<concept>");

            Globals.MaximumNumberOfAvailableConcepts = 0;
            Globals.AvailableConceptCodes.clear();
            Globals.AvailableConceptShortNames.clear();
            Globals.AvailableConceptsThatHaveQuery.clear();

            for (ConceptData concept : response.getConcepts()) {
                System.out.print("\n\n   conceptCategory: " + concept.getConceptCategory());
                System.out.print("\n       conceptCode: " + concept.getConceptCode());
                System.out.print("\n       conceptName: " + concept.getConceptName());
                System.out.print("\n  conceptShortName: " + concept.getConceptShortName());
                System.out.print("\n     defaultAmount: " + concept.getDefaultAmount());
                System.out.print("\n        hasBarCode: " + concept.isHasBarCode());
                System.out.print("\n          hasQuery: " + concept.isHasQuery());
                System.out.print("\n   providerMessage: " + concept.getProviderMessage());
                System.out.print("\n    readOnlyAmount: " + concept.isReadOnlyAmount());
                System.out.print("\n   requireQuantity: " + concept.isRequireQuantity());

                Globals.AvailableConceptCodes.add(concept.getConceptCode());
                Globals.AvailableConceptShortNames.add(concept.getConceptShortName());
                Globals.AvailableConceptsThatHaveQuery.put(concept.getConceptCode(), concept.isHasQuery());
                Globals.MaximumNumberOfAvailableConcepts++;
            }

            // agentCategories

            System.out.print("\n\n<agentCategory>");
            for (AgentCategory agentCategory : response.getAgentCategories()) {
                System.out.print("\n\n  code: " + agentCategory.getCode());
                System.out.print("\n  name: " + agentCategory.getName());

                System.out.print("\n\n  <availableConcept>\n");

                for (AgentCategoryConcept availableConcept : agentCategory.getAvailableConcepts()) {
                    System.out.print("\n     conceptCode: " + availableConcept.getConceptCode());
                }
            }

            // agents

            System.out.print("\n\n<agent>");
            for (Agent agent : response.getAgents()) {
                System.out.print("\n\n          categoryCode: " + agent.getCategoryCode());
                System.out.print("\n                  code: " + agent.getCode());
                System.out.print("\n  geoPoliticalLocation: " + agent.getGeoPoliticalLocation());
                System.out.print("\n          categoryCode: " + agent.getName());
                System.out.print("\n          neighborhood: " + agent.getNeighborhood());
                System.out.print("\n                street: " + agent.getStreet());
            }

            // agentBranches

            System.out.print("\n\n<agentBranch>");

            for (AgentBranch agentBranch : response.getAgentBranches()) {
                System.out.print("\n\n               address: " + agentBranch.getAddress());
                System.out.print("\n             agentCode: " + agentBranch.getAgentCode());
                System.out.print("\n                  code: " + agentBranch.getCode());
                System.out.print("\n  geoPoliticalLocation: " + agentBranch.getGeoPoliticalLocation());
                System.out.print("\n                  name: " + agentBranch.getName());

            }

            // banks

            System.out.print("\n\n<bank>");

            for (Bank bank : response.getBanks()) {
                System.out.print("\n\n         code: " + bank.getCode());
                System.out.print("\n         name: " + bank.getName());
                System.out.print("\n  routeNumber: " + bank.getRouteNumber());
            }

            // bankAccounts

            System.out.print("\n\n<bankAccount>");

            Globals.MaximumNumberOfBankAccounts = 0;
            for (BankAccount bankAccount : response.getBankAccounts()) {
                System.out.print("\n\n  accountNumber: " + bankAccount.getAccountNumber());
                System.out.print("\n       bankCode: " + bankAccount.getBankCode());

                Globals.AccountNumbers.add(bankAccount.getAccountNumber());
                Globals.BankCodes.add(bankAccount.getBankCode());

                Globals.MaximumNumberOfBankAccounts++;
            }

        }
    }
}

