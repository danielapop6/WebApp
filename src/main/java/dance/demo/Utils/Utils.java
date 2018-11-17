package dance.demo.Utils;

import java.math.BigInteger;
import java.security.MessageDigest;

public class Utils {
    public static String getMD5Hash(String text){
        try {

            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(text.getBytes());
            BigInteger number = new BigInteger(1, messageDigest);
            return number.toString(16);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
