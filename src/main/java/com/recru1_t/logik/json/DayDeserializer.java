package com.recru1_t.logik.json;

import java.io.IOException;
import java.time.LocalDate;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.recru1_t.logik.Absence;
import com.recru1_t.logik.WorkDay;

public class DayDeserializer extends StdDeserializer<WorkDay> {

    public DayDeserializer() {
        this(null);
    }

    public DayDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WorkDay deserialize(JsonParser parser, DeserializationContext deserializer) {
        ObjectCodec codec = parser.getCodec();
        try {
            JsonNode node = codec.readTree(parser);
            int startHour = node.get("startHour").asInt();
            int startMinute = node.get("startMinute").asInt();
            int endHour = node.get("endHour").asInt();
            int endMinute = node.get("endMinute").asInt();

            System.out.println("Deserializing WorkDay: " + node.toString());
            LocalDate date = codec.treeToValue(node.get("date"), LocalDate.class);
            Absence absence = Absence.valueOf(node.get("absence").asText());
            if (absence != Absence.NONE) {
                return new WorkDay(absence, date);
            } else {
                return new WorkDay(startHour, startMinute, endHour, endMinute, date);
            }

           
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

}
