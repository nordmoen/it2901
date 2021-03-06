package no.ntnu.qos.client.credentials;

import no.ntnu.qos.client.DataObject;


/**
 * The main interface to get tokens from the token storage system.
 * @author Stig Tore
 *
 */
public interface TokenManager {

    /**
     * Set the credentials of the client
     * @param username username of the client
     * @param role role of the client
     * @param password password of the client
     */
    public void setCredentials(String username, String role, String password);

    /**
     *
      * @param dataObject the dataObject that should contain the destination you need to get a token.
     *  @return token - the token of the dataObject
     */
    public Runnable getToken(DataObject dataObject);

    /**
     *
     * @return String[] - the credentials array that is stored in the CredentialStorage
     */
    public String[] getCredentials();
}
