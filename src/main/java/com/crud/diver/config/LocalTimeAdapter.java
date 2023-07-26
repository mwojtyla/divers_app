package com.crud.diver.config;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class LocalTimeAdapter extends TypeAdapter<LocalTime> {

    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_TIME;

    @Override
    public void write(JsonWriter out, LocalTime time) throws IOException {
        out.value(formatter.format(time));
    }

    @Override
    public LocalTime read(JsonReader in) throws IOException {
        String timeString = in.nextString();
        return LocalTime.parse(timeString, formatter);
    }
}
