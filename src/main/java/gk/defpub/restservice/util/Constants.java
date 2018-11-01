package gk.defpub.restservice.util;

/**
 * Constants class.
 * <p>
 * Date: Nov 2, 2018
 * <p>
 *
 * @author Gleb Kosteiko
 */
public class Constants {
    public static final long ACCESS_TOKEN_VALIDITY_SECONDS = 5*60*60;
    public static final String SIGNING_KEY = "defPub0923Check";
    public static final String TOKEN_PREFIX = "Bearer ";
    public static final String HEADER_STRING = "Authorization";
    public static final String AUTHORITIES_KEY = "scopes";
}
