package nextstep.subway.path.domain;

import java.util.Objects;

public class Fare {
    public static final Fare ZERO = new Fare(0);

    private final int fare;

    private Fare(int fare) {
        this.fare = fare;
    }

    public static Fare from(int fare) {
        return new Fare(fare);
    }

    public int getFare() {
        return fare;
    }

    public Fare add(Fare fare) {
        return new Fare(this.fare + fare.fare);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Fare)) {
            return false;
        }
        Fare fare1 = (Fare)o;
        return fare == fare1.fare;
    }

    @Override
    public int hashCode() {
        return Objects.hash(fare);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Fare{");
        sb.append("fare=").append(fare);
        sb.append('}');
        return sb.toString();
    }
}
