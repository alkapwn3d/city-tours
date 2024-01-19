package com.techelevator.model.searchlandmark;

import com.techelevator.model.searchlandmark.CurrentOpeningHours;

import java.util.List;

public class LandmarkResult {
    private String id;
    private DisplayName displayName;

    private List<String> types;

    private  double rating;

    private  String formattedAddress;

    private AccessibilityOptions accessibilityOptions;

    private Location location;

    private CurrentOpeningHours currentOpeningHours;
    private String priceLevel;

    public String getPriceLevel() {
        return priceLevel;
    }

    public void setPriceLevel(String priceLevel) {
        this.priceLevel = priceLevel;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public DisplayName getDisplayName() {
        return displayName;
    }

    public CurrentOpeningHours getCurrentOpeningHours() {
        return currentOpeningHours;
    }

    public void setCurrentOpeningHours(CurrentOpeningHours currentOpeningHours) {
        this.currentOpeningHours = currentOpeningHours;
    }

    public void setDisplayName(DisplayName displayName) {
        this.displayName = displayName;
    }

    public List<String> getTypes() {
        return types;
    }

    public void setTypes(List<String> types) {
        this.types = types;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getFormattedAddress() {
        return formattedAddress;
    }

    public void setFormattedAddress(String formattedAddress) {
        this.formattedAddress = formattedAddress;
    }

    public AccessibilityOptions getAccessibilityOptions() {
        return accessibilityOptions;
    }

    public void setAccessibilityOptions(AccessibilityOptions accessibilityOptions) {
        this.accessibilityOptions = accessibilityOptions;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}
