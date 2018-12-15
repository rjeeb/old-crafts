package org.dominokit.craft.usecase;

import org.dominokit.craft.exception.RequestCannotBeNullException;
import org.dominokit.craft.model.ImmutableItem;
import org.dominokit.craft.model.Item;
import org.dominokit.craft.repository.ItemsRepository;
import org.dominokit.craft.repository.TestItemsRepository;
import org.dominokit.craft.request.ImmutableListItemsRequest;
import org.dominokit.craft.request.ListItemsRequest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ListItemsUseCaseTest {

    private ListItemsUseCase useCase;
    private ItemsRepository itemsRepository;

    @BeforeEach
    void init() {
        itemsRepository = new TestItemsRepository();
        useCase = new ListItemsUseCase(itemsRepository);
    }

    @Test
    void givenCreatedUseCaseWhenLoadThenReturnEmptyList() {
        assertThat(execute()).isEmpty();
    }

    @Test
    void givenRepositoryContainsOneItemWhenLoadThenReturnOneItem() {
        itemsRepository.save(testItem("item1"));
        assertThat(execute().size()).isEqualTo(1);
    }

    @Test
    void givenRepositoryContainsMoreThanOneItemWhenLoadThenReturnAllItems() {
        itemsRepository.save(testItem("item1"));
        itemsRepository.save(testItem("item2"));
        assertThat(execute().size()).isEqualTo(2);
    }

    @Test
    void givenUseCaseWhenExecuteWithNullRequestThenShouldThrowException() {
        assertThatThrownBy(() -> execute(null))
                .isInstanceOf(RequestCannotBeNullException.class);
    }

    @Test
    void givenRequestWithEmptyFiltersWhenExecuteThenShouldReturnAllItems() {
        itemsRepository.save(testItem("item1"));
        itemsRepository.save(testItem("item2"));
        assertThat(execute().size()).isEqualTo(2);
    }

    @Test
    void givenRequestWithFilterDoesNotMatchItemWhenExecuteThenShouldReturnEmptyList() throws IOException {
        itemsRepository.save(testItem("item1"));
        itemsRepository.save(testItem("item2"));
        ImmutableListItemsRequest request = ImmutableListItemsRequest
                .builder()
//                .putFilters("name", "not exist item")
                .build();
        assertThat(execute(request).size()).isEqualTo(0);
    }

    private List<Item> execute() {
        return execute(testRequest());
    }

    private List<Item> execute(ListItemsRequest request) {
        return useCase.execute(request);
    }

    private ListItemsRequest testRequest() {
        return ImmutableListItemsRequest.builder().build();
    }

    private Item testItem(String itemName) {
        return ImmutableItem.builder().name(itemName).build();
    }
}
