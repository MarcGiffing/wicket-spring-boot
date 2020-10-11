package com.giffing.wicket.spring.boot.example.web.pages.websocket;

import java.io.Serializable;

/**
 * The user can login to multiple browsers and tabs.
 * This class is used to identify a specific user with an open browser tab
 * 
 * @author Marc Giffing
 *
 */
public class ChatParticipant implements Serializable {

	private String username;

	/**
	 * The user can login to multiple browsers and tabs
	 * This identifier is used to identify a single browser tab.
	 */
	private String browserTabIdentifier;
	
	public ChatParticipant(String browserTabIdentifier, String username) {
		this.browserTabIdentifier = browserTabIdentifier;
		this.username = username;
	}

	public String getBrowserTabIdentifier() {
		return browserTabIdentifier;
	}

	public void setBrowserTabIdentifier(String browserTabIdentifier) {
		this.browserTabIdentifier = browserTabIdentifier;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((browserTabIdentifier == null) ? 0 : browserTabIdentifier.hashCode());
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ChatParticipant other = (ChatParticipant) obj;
		if (browserTabIdentifier == null) {
			if (other.browserTabIdentifier != null)
				return false;
		} else if (!browserTabIdentifier.equals(other.browserTabIdentifier))
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		return true;
	}

	
}
