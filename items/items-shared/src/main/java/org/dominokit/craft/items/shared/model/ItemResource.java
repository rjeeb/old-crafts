package org.dominokit.craft.items.shared.model;

import org.dominokit.domino.api.shared.request.ResponseBean;
import org.dominokit.jacksonapt.annotation.JSONMapper;

import java.util.Objects;

@JSONMapper
public class ItemResource implements ResponseBean {
    private String imageUrl;
    private String title;
    private String description;
    private double amount;

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ItemResource that = (ItemResource) o;
        return Double.compare(that.amount, amount) == 0 &&
                Objects.equals(imageUrl, that.imageUrl) &&
                Objects.equals(title, that.title) &&
                Objects.equals(description, that.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(imageUrl, title, description, amount);
    }
}
