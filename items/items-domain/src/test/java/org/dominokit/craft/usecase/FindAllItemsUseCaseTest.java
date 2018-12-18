package org.dominokit.craft.usecase;

import org.dominokit.craft.items.shared.model.ItemResource;
import org.dominokit.craft.model.ImmutableItem;
import org.dominokit.craft.model.Item;
import org.dominokit.craft.repository.InMemoryItemsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class FindAllItemsUseCaseTest {

    private InMemoryItemsRepository itemsRepository;
    private FindAllItemsUseCase findAllItemsUseCase;

    @BeforeEach
    void init() {
        itemsRepository = new InMemoryItemsRepository();
        findAllItemsUseCase = new FindAllItemsUseCase(itemsRepository);
    }

    @Test
    void givenEmptyRepositoryWhenExecuteThenReturnNoItems() {
        assertThat(findAllItemsUseCase.execute()).isEmpty();
    }

    @Test
    void given1ItemInRepositoryWhenExecuteThenReturn1Item() {
        itemsRepository.save(testItem("item1"));
        assertThat(findAllItemsUseCase.execute()).containsExactly(testItemResource("item1"));
    }

    @Test
    void given2ItemInRepositoryWhenExecuteThenReturn2Item() {
        itemsRepository.save(testItem("item1"));
        itemsRepository.save(testItem("item2"));
        assertThat(findAllItemsUseCase.execute())
                .containsExactlyInAnyOrder(testItemResource("item1"), testItemResource("item2"));
    }

    private Item testItem(String title) {
        return ImmutableItem.builder()
                .title(title)
                .description("item of title " + title)
                .price(22.36)
                .build();
    }

    private ItemResource testItemResource(String title) {
        ItemResource itemResource = new ItemResource();
        itemResource.setTitle(title);
        itemResource.setAmount(22.36);
        itemResource.setDescription("item of title " + title);
        return itemResource;
    }
}
