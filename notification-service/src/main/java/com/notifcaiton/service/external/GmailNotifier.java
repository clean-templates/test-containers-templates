package com.notifcaiton.service.external;

import com.notifcaiton.service.service.INotifier;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class GmailNotifier implements INotifier {

    @Override
    public void sendEmail(String customerEmail) {
        log.info("Sending Email To: " + customerEmail);
    }
}
