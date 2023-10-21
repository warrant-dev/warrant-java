package dev.warrant.model.object;

import java.util.Map;

public interface WarrantObject {
    public String id();

    public String type();

    public Map<String, Object> meta();
}
