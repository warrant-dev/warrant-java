package dev.warrant.model.object;

import java.util.Map;

public class PricingTier extends BaseWarrantObject {
    public static final String OBJECT_TYPE = "pricing-tier";

    public PricingTier() {
        // For json serialization
        super();
    }

    public PricingTier(String pricingTierId) {
        super(OBJECT_TYPE, pricingTierId);
    }

    public PricingTier(String pricingTierId, Map<String, Object> meta) {
        super(OBJECT_TYPE, pricingTierId, meta);
    }

    public String getPricingTierId() {
        return objectId;
    }

    public void setPricingTierId(String pricingTierId) {
        this.objectId = pricingTierId;
    }

    @Override
    public String id() {
        return objectId;
    }

    @Override
    public String type() {
        return OBJECT_TYPE;
    }
}
