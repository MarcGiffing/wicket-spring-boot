package com.giffing.wicket.spring.boot.example.web.pages.websocket;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * The user can login to multiple browsers and tabs.
 * This class is used to identify a specific user with an open browser tab
 *
 * @author Marc Giffing
 */
@Data
@RequiredArgsConstructor
public class ChatParticipant implements Serializable {

    private final String username;

    /**
     * The user can login to multiple browsers and tabs
     * This identifier is used to identify a single browser tab.
     */
    private final String browserTabIdentifier;

}
