package lk.ijse.gdse.springboot.springposapi.util;

import java.util.UUID;

public class AppUtil {
    public static String createCustomerId() {
        return "CUS-"+ UUID.randomUUID();
    }
}
