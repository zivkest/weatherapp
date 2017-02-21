package com.ziv.weatherapp;

public final class Constants {
    private Constants() {}

    public static final class Injection {
        private Injection() {}

        public static final class Named {
            private Named() {}
            public static final String FORECAST_API_KEY = "forecast_api_key";
            public static final String HEBREW_CAL_ENDPOINT = "hebrew_cal_end_point";
            public static final String FORECAST_IO_ENDPOINT = "forecast_io_endpoint";

            public static final String HEBREW_CAL_REST = "hebrew_cal_rest";
            public static final String FORECAST_IO_REST = "forecast_io_rest";
        }
    }
}
