package org.dominokit.craft.home.client.requests.news;

import org.dominokit.craft.home.client.requests.TestResponse;
import org.dominokit.craft.home.shared.model.RecentItem;
import org.dominokit.craft.home.shared.model.RecentItems;
import org.dominokit.domino.api.client.request.Response;

import java.util.Arrays;

public class TestRecentItemRequests implements RecentItemRequests {

    @Override
    public Response<RecentItems> list() {
        RecentItem recentItem = new RecentItem();
        recentItem.setImageUrl("https://picsum.photos/250/200/?image=998");
        recentItem.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        recentItem.setDescription("MignonandMignon");
        recentItem.setAmount("USD 14.50");

        RecentItem recentItem1 = new RecentItem();
        recentItem1.setImageUrl("https://picsum.photos/250/200/?image=942");
        recentItem1.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        recentItem1.setDescription("MignonandMignon");
        recentItem1.setAmount("USD 14.50");

        RecentItem recentItem2 = new RecentItem();
        recentItem2.setImageUrl("https://picsum.photos/250/200/?image=885");
        recentItem2.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        recentItem2.setDescription("MignonandMignon");
        recentItem2.setAmount("USD 14.50");

        RecentItem recentItem3 = new RecentItem();
        recentItem3.setImageUrl("https://picsum.photos/250/200/?image=882");
        recentItem3.setTitle("Personalized Gift for Mom Custom Coordinates Bracelet Engraved Bracelets For Women Personalized bracelet - 12 BR");
        recentItem3.setDescription("MignonandMignon");
        recentItem3.setAmount("USD 14.50");

        RecentItems recentItems = new RecentItems();
        recentItems.getRecentItems().addAll(Arrays.asList(recentItem, recentItem1, recentItem2, recentItem3));
        return new TestResponse<>(recentItems);
    }
}
