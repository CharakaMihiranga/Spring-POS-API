package lk.ijse.gdse.springboot.springposapi.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.UUID;

public class AppUtil {
    public static String generateId(String prefix) {
        return prefix + "-" + UUID.randomUUID();
    }

    public static String toBase64Pic(MultipartFile pic) {
        byte [] picBytes = null;
        try{
            picBytes = pic.getBytes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return Base64.getEncoder().encodeToString(picBytes);
    }
}
