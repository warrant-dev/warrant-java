package dev.warrant.model.object;

public class PricingTier implements WarrantObject {
    public static final String OBJECT_TYPE = "pricing-tier";

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

    @Override
    public String type() {
        return "pricing-tier";
    }
}
