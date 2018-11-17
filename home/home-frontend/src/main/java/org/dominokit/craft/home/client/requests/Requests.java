package org.dominokit.craft.home.client.requests;

import org.dominokit.craft.home.client.requests.news.*;

public class Requests {

    public static PreviewRequests previewRequests() {
        return new TestPreviewRequests();
    }

    public static FeatureRequests featuresRequests() {
        return new TestFeatureRequests();
    }

    public static RecentItemRequests recentItemsRequests() {
        return new TestRecentItemRequests();
    }

    public static GiftCategoryRequests giftCategoryRequests() {
        return new TestGiftCategoryRequests();
    }

    public static ReviewRequests reviewRequests() {
        return new TestReviewRequests();
    }
}
