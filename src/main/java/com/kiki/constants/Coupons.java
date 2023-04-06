package main.java.com.kiki.constants;

public class Coupons {

    public enum OfferCode {
        OFR001,
        OFR002,
        OFR003,
    }

    private final OfferCode offerCode;
    private final double discount;
    private final int minDistance;
    private final int maxDistance;
    private final int minWeight;
    private final int maxWeight;

    public OfferCode getOfferCode() {
        return offerCode;
    }

    public double getDiscount() {
        return discount;
    }

    public int getMinDistance() {
        return minDistance;
    }

    public int getMaxDistance() {
        return maxDistance;
    }

    public int getMinWeight() {
        return minWeight;
    }

    public int getMaxWeight() {
        return maxWeight;
    }

    private Coupons(OfferCode offerCode, double discount, int minDistance, int maxDistance, int minWeight, int maxWeight) {
        this.offerCode = offerCode;
        this.discount = discount;
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
    }

    public static final Coupons COUPON_OFR001 = new Coupons(OfferCode.OFR001, 0.1, 0, 200, 70, 200);
    public static final Coupons COUPON_OFR002 = new Coupons(OfferCode.OFR002, 0.07, 50, 150, 100, 250);
    public static final Coupons COUPON_OFR003 = new Coupons(OfferCode.OFR003, 0.05, 50, 250, 10, 150);

//    public boolean isCouponApplicable(PackageDetailsInput packageDetailsInput){
//        return (packageDetailsInput.getDistance() >= minDistance &&
//                packageDetailsInput.getDistance() <= maxDistance &&
//                packageDetailsInput.getPackageWeight() >=minWeight &&
//                packageDetailsInput.getPackageWeight() <= maxWeight
//        );
//    }

    public static boolean couponOfferExists(String offerCodeString) {
        boolean exists = false;
        for (OfferCode offerCode : OfferCode.values()) {
            if (offerCode.name().equalsIgnoreCase(offerCodeString)) {
                exists = true;
                break;
            }
        }
        return exists;
    }

    public static Coupons getCouponCriteria(OfferCode offerCode) {
        switch (offerCode) {
            case OFR001: {
                return Coupons.COUPON_OFR001;
            }
            case OFR002: {
                return Coupons.COUPON_OFR002;

            }
            case OFR003: {
                return Coupons.COUPON_OFR003;

            }
            default:
                return null;
        }
    }

}
