package dev.warrant.model.object;

public class PricingTier implements WarrantObject {
    private String pricingTierId;

    public PricingTier() {
        // For json serialization
    }

    public PricingTier(String pricingTierId) {
        this.pricingTierId = pricingTierId;
    }

    public String getPricingTierId() {
        return pricingTierId;
    }

    public void setPricingTierId(String pricingTierId) {
        this.pricingTierId = pricingTierId;
    }

    @Override
    public String id() {
        return pricingTierId;
    }
}
