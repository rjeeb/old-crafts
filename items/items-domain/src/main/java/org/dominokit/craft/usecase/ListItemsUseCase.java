package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.RequestCannotBeNullException;
import org.dominokit.craft.model.Item;
import org.dominokit.craft.repository.ItemsRepository;
import org.dominokit.craft.request.ListItemsRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static java.util.Objects.isNull;

public class ListItemsUseCase {
    private ItemsRepository itemsRepository;

    public ListItemsUseCase(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<Item> execute(ListItemsRequest request) {
        if (isNull(request)) {
            throw new RequestCannotBeNullException();
        }
        Map<String, String> filters = request.filters();
        List<Item> items = itemsRepository.getAll();
        return isNull(items) ? new ArrayList<>() : items;
    }
}
