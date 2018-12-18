package org.dominokit.craft.items.server;

import org.dominokit.craft.repository.InMemoryItemsRepository;
import org.dominokit.craft.repository.ItemsRepository;
import org.dominokit.craft.usecase.FindAllItemsUseCase;

public class UseCaseFactory {
    private ItemsRepository itemsRepository = new InMemoryItemsRepository();

    private static final UseCaseFactory INSTANCE = new UseCaseFactory();

    public static UseCaseFactory getInstance() {
        return INSTANCE;
    }

    public FindAllItemsUseCase findAllItemsUseCase() {
        return new FindAllItemsUseCase(itemsRepository);
    }
}
