package org.dominokit.craft.shared.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Filters {
    private Map<String, List<Filter>> filters = new HashMap<>();

    public Map<String, List<Filter>> getFilters() {
        return filters;
    }

    public void setFilters(Map<String, List<Filter>> filters) {
        this.filters = filters;
    }

    public void addFilter(String name, Filter filter) {
        if (!filters.containsKey(name)) {
            filters.put(name, new ArrayList<>());
        }
        filters.get(name).add(filter);
    }
}
