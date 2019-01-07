package org.dominokit.craft.repository.entity;

import org.dominokit.jacksonapt.annotation.JSONMapper;

import java.util.List;

@JSONMapper
public class ItemEntity {

    private String reference;
    private String title;
    private String whoMadeIt;
    private String whatIsIt;
    private String whenWasItMade;
    private String category;
    private String renewalOption;
    private String type;
    private String description;
    private String section;
    private double amount;
    private int quantity;
    private List<String> imageReferences;

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWhoMadeIt() {
        return whoMadeIt;
    }

    public void setWhoMadeIt(String whoMadeIt) {
        this.whoMadeIt = whoMadeIt;
    }

    public String getWhatIsIt() {
        return whatIsIt;
    }

    public void setWhatIsIt(String whatIsIt) {
        this.whatIsIt = whatIsIt;
    }

    public String getWhenWasItMade() {
        return whenWasItMade;
    }

    public void setWhenWasItMade(String whenWasItMade) {
        this.whenWasItMade = whenWasItMade;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getRenewalOption() {
        return renewalOption;
    }

    public void setRenewalOption(String renewalOption) {
        this.renewalOption = renewalOption;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public List<String> getImageReferences() {
        return imageReferences;
    }

    public void setImageReferences(List<String> imageReferences) {
        this.imageReferences = imageReferences;
    }
}