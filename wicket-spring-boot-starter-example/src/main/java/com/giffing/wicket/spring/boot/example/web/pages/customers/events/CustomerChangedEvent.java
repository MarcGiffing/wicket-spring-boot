package com.giffing.wicket.spring.boot.example.web.pages.customers.events;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

import com.giffing.wicket.spring.boot.example.model.Customer;

@Getter
@RequiredArgsConstructor
public class CustomerChangedEvent implements IWebSocketPushMessage {

	private final Customer customer;

}
