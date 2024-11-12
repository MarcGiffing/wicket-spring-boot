package com.giffing.wicket.spring.boot.example.web.pages.customers.events;

import com.giffing.wicket.spring.boot.example.model.Customer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.wicket.protocol.ws.api.message.IWebSocketPushMessage;

@Getter
@RequiredArgsConstructor
public class CustomerDeletedEvent implements IWebSocketPushMessage {

	private final Customer customer;

}
