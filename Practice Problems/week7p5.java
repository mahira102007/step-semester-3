
abstract class DeliveryNote {
    public abstract String confirmDelivery();

    public String confirmDelivery(String signature) {
        if (signature == null || signature.trim().isEmpty())
            throw new IllegalArgumentException("Signature cannot be blank");

        return confirmDelivery() + ", signed by " + signature;
    }
}

class ParcelNote extends DeliveryNote {
    private String trackingId;

    public ParcelNote(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty())
            throw new IllegalArgumentException("Tracking ID cannot be blank");
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Parcel " + trackingId + " delivered";
    }
}

class LetterNote extends DeliveryNote {
    private String trackingId;

    public LetterNote(String trackingId) {
        if (trackingId == null || trackingId.trim().isEmpty())
            throw new IllegalArgumentException("Tracking ID cannot be blank");
        this.trackingId = trackingId;
    }

    @Override
    public String confirmDelivery() {
        return "Letter " + trackingId + " delivered";
    }
}

public class week7p5 {
    static void logAll(DeliveryNote[] notes) {
        for (DeliveryNote note : notes) {
            System.out.println(note.confirmDelivery());
        }
    }

    public static void main(String[] args) {
        ParcelNote p = new ParcelNote("TRK-1");

        System.out.println(p.confirmDelivery());
        System.out.println(p.confirmDelivery("J. Smith"));

        DeliveryNote ref = p;
        logAll(new DeliveryNote[]{
            ref, new LetterNote("TRK-2")
        });
    }
}
