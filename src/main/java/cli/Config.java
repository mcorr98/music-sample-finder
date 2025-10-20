/**
 * 
 */
package cli;

/**
 * CA small utility class that handles configuration
 * for the CLI application. 
 */
public class Config {
	
	/**
     * Reads the Discogs API token from the environment variable DISCOGS_TOKEN.
     * This method checks that the token exists and is not blank.
     * If the token is missing, it throws an IllegalStateException
     * @return the trimmed API token string, ready to use in API requests
     * @throws IllegalStateException if the environment variable is not set or is blank
     */
    public static String getDiscogsToken() {
        String token = System.getenv("DISCOGS_TOKEN");
        if (token == null || token.isBlank()) {
            throw new IllegalStateException("DISCOGS_TOKEN not set");
        }
        return token.trim();
    }
}
