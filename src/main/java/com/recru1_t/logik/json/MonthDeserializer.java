package com.recru1_t.logik.json;

import java.io.IOException;
import java.util.ArrayList;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;
import com.recru1_t.logik.WorkDay;
import com.recru1_t.logik.WorkMonth;

public class MonthDeserializer extends StdDeserializer<WorkMonth> {

    public MonthDeserializer() {
        this(null);
    }

    public MonthDeserializer(Class<?> vc) {
        super(vc);
    }

    @Override
    public WorkMonth deserialize(JsonParser parser, DeserializationContext deserializer) {
        WorkMonth month = new WorkMonth(0, 0, new ArrayList<WorkDay>());
        ObjectCodec codec = parser.getCodec();
        try {
            JsonNode node = codec.readTree(parser);
            month.setMonth(node.get("month").asInt());
            month.setYear(node.get("year").asInt());
            JsonNode workDaysNode = node.get("workDays");
            if (workDaysNode.isArray()) {
                for (JsonNode workDayNode : workDaysNode) {
                    WorkDay workDay = codec.treeToValue(workDayNode, WorkDay.class);
                    month.addWorkDay(workDay);
                }
            }
           
        } catch (IOException e) {
            e.printStackTrace();
        }
        // try catch block

        return month;
    }
}
