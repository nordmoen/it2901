package no.ntnu.qos.client.credentials.impl;

import no.ntnu.qos.client.credentials.Token;

import java.net.URI;

public class TokenImpl implements Token {
	//TODO: Change to accomodate an actual OpenSAML object?
	String token;
	long validUntil;
	URI destination;
	
	public TokenImpl(String token, long validUntil, URI destination) {
		this.token = token;
		this.destination = destination;
		this.validUntil = validUntil;
	}

    @Override
	public String getXML() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isValid() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public URI getDestination() {
		return this.destination;
	}

    @Override
    public int getDiffServ() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public int getPriority() {
        return 0;  //To change body of implemented methods use File | Settings | File Templates.
    }

}
