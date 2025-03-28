package com.automation.framework.core.mails;

import java.util.UUID;

import org.openqa.selenium.WebDriver;

import com.mailslurp.apis.InboxControllerApi;
import com.mailslurp.apis.WaitForControllerApi;
import com.mailslurp.clients.ApiClient;
import com.mailslurp.clients.ApiException;
import com.mailslurp.clients.Configuration;
import com.mailslurp.models.Email;
import com.mailslurp.models.InboxDto;

public class MailslurpClient {

    private static final Long TIMEOUT_MILLIS = 30000L;
    private ApiClient client;
    
    public MailslurpClient() {
        client = Configuration.getDefaultApiClient();
        client.setConnectTimeout(TIMEOUT_MILLIS.intValue());
    }

    public MailslurpClient(String apiKey) {
        client = Configuration.getDefaultApiClient();
        client.setConnectTimeout(TIMEOUT_MILLIS.intValue());
        setApiKey(apiKey);
    }

    public MailslurpClient setApiKey(String apiKey) {
        client.setApiKey(apiKey);
        return this;
    }

    public InboxDto createEmailAddresses() throws ApiException {
        InboxControllerApi inboxControllerApi = new InboxControllerApi(client);
        return inboxControllerApi.createInboxWithDefaults();
    }

    public Email getLastestEmail(String userEmailId, Long waitTime) throws ApiException {
        boolean unread = true;
        WaitForControllerApi waitForControllerApi = new WaitForControllerApi(client);
        Email email = waitForControllerApi.waitForLatestEmail(UUID.fromString(userEmailId), waitTime, unread, null, null, null, null);
        return email;
    }

    public void deleteAllEmails(String userEmailId) throws ApiException {
        InboxControllerApi inboxControllerApi = new InboxControllerApi(client);
        inboxControllerApi.deleteAllInboxEmails(UUID.fromString(userEmailId));
    }


}
