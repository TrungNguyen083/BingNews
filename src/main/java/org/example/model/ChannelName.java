package org.example.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.io.IOException;

public enum ChannelName {
    GOOGLE_TREND, LAODONG, TIENPHONG, VN_EXPRESS;

    @JsonValue
    public String toValue() {
        switch (this) {
            case GOOGLE_TREND: return "GoogleTrend";
            case LAODONG: return "laodong";
            case TIENPHONG: return "tienphong";
            case VN_EXPRESS: return "VnExpress";
        }
        return null;
    }

    @JsonCreator
    public static ChannelName forValue(String value) throws IOException {
        if (value.equals("GoogleTrend")) return GOOGLE_TREND;
        if (value.equals("laodong")) return LAODONG;
        if (value.equals("tienphong")) return TIENPHONG;
        if (value.equals("VnExpress")) return VN_EXPRESS;
        throw new IOException("Cannot deserialize ChannelName");
    }
}
