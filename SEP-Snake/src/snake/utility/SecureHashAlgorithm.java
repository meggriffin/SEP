package snake.utility;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * Class with static methods to generate secure 512-bits Strings based on the
 * "Secure Hash Algorithm".
 */
public class SecureHashAlgorithm {

	/**
	 * Hashes the password combined with the salt to a determine String.
	 * 
	 * @param passwordToHash
	 * @param salt
	 * @return
	 */
	public static String getSHA256Password(String passwordToHash, String salt)
	{
		String generatedPassword = null;
		try
		{
			MessageDigest md = MessageDigest.getInstance("SHA-512");
			md.update(salt.getBytes());
			byte[] bytes = md.digest(passwordToHash.getBytes());
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < bytes.length; i++)
			{
				sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
			}
			generatedPassword = sb.toString();
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return generatedPassword;
	}

	/**
	 * Generates a salt based on the "Pseudo Random Number Generator" algorithm.
	 * 
	 * @return
	 */
	public static String generateSalt()
	{
		byte[] salt = new byte[16];
		try
		{
			SecureRandom sr = new SecureRandom().getInstance("SHA1PRNG");
			sr.nextBytes(salt);
		} catch (NoSuchAlgorithmException e)
		{
			e.printStackTrace();
		}
		return salt.toString();
	}
}
