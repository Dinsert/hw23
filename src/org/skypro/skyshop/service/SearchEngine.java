package org.skypro.skyshop.service;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import org.skypro.skyshop.model.Searchable;

public class SearchEngine {

    private final Map<String, Searchable> elements;

    public SearchEngine() {
        elements = new HashMap<>();
    }

    public TreeMap<String, Searchable> search(String string) {
        TreeMap<String, Searchable> searchResult = new TreeMap<>();
        for (Searchable element : elements.values()) {
            if (nonNull(element) && element.searchTerm(string)) {
                searchResult.put(element.getName(), element);
            }
        }
        return searchResult;
    }

    public void add(Searchable searchable) {
        elements.put(searchable.getName(), searchable);
    }

    public Searchable findMostSuitableBySearchString(String search) throws BestResultNotFound {
        Searchable searchable = null;
        for (Searchable element : elements.values()) {
            if (nonNull(element) && element.searchTerm(search)) {
                searchable = element;
                break;
            }
        }
        if (isNull(searchable)) {
            throw new BestResultNotFound("Не удалось найти по запросу " + search + " наиболее подходящего результата");
        }
        return searchable;
    }

    private int getSearchTerm(String string, String substring) {
        int count = 0;
        int index = 0;
        int indexSubstring = string.indexOf(substring, index);
        while (indexSubstring != -1) {
            count++;
            index += substring.length();
            indexSubstring = string.indexOf(substring, index);
        }
        return count;
    }
}
