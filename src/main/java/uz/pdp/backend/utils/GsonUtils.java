package uz.pdp.backend.utils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public interface GsonUtils {
    Gson gson=buildGson();

    private static Gson buildGson() {
        return new GsonBuilder()
                .create();
    }
}
