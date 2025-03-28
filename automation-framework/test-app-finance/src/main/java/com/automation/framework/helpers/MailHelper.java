package com.automation.framework.helpers;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.automation.framework.core.mails.MailslurpClient;
import com.automation.framework.core.utils.StringUtil;
import com.automation.framework.models.Account;
import com.mailslurp.clients.ApiException;
import com.mailslurp.models.Email;

public class MailHelper {

    private static final Long TIMEOUT_MILLIS = 60000L;

    private static MailslurpClient client;

    public MailHelper(MailslurpClient mailClient) {
        client = mailClient;
    }

    public String getRegisterEmailVerificationCode(Account account){
        try {
            Email email = client.getLastestEmail(account.getMailslurpInboxId(), TIMEOUT_MILLIS);
            String body = email.getBody();

            String fillterCode = StringUtil.findRegexMatches(body, ".*<div>\\d+").toString();
            String verificationCode = StringUtil.findRegexMatches(fillterCode, "\\d+").getFirst().toString();
            System.out.println("Get verificationCode code successfully: " + verificationCode );

            return verificationCode;
        } catch (ApiException e) {
            throw new RuntimeException("Not found the new lastest email of account " + account.getEmail() + " " +e);
        }
    }

    public String getLoginVerificationCode(Account account){
        try {
            Email email = client.getLastestEmail(account.getMailslurpInboxId(), TIMEOUT_MILLIS);
            String body = email.getBody();

            String fillterCode = StringUtil.findRegexMatches(body, "<strong>\\d+").toString();
            String verificationCode = StringUtil.findRegexMatches(fillterCode, "\\d+").getFirst().toString();
            System.out.println("Get verificationCode code successfully: " + verificationCode );

            return verificationCode;
        } catch (ApiException e) {
            throw new RuntimeException("Not found the new lastest email of account " + account.getEmail() + " " +e);
        }
    }

    public void deleteAllEmailOfAccount(Account account){
        try {
            client.deleteAllEmails(account.getMailslurpInboxId());
            System.out.println("Delete all mails of account successfully");
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }
    }

}
