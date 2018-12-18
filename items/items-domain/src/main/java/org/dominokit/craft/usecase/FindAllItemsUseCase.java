package org.dominokit.craft.usecase;

import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.mapper.ItemsMapper;
import org.dominokit.craft.model.Item;
import org.dominokit.craft.repository.ItemsRepository;

import java.util.List;
import java.util.stream.Collectors;

public class FindAllItemsUseCase {

    private ItemsRepository itemsRepository;

    public FindAllItemsUseCase(ItemsRepository itemsRepository) {
        this.itemsRepository = itemsRepository;
    }

    public List<ItemResource> execute() {
        List<Item> items = itemsRepository.findAll();
        return items.stream()
                .map(item -> ItemsMapper.create().toResource(item))
                .collect(Collectors.toList());
    }
}
