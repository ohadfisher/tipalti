package tipalti.model;

public class Address {
    private final String street;
    private final String city;

    public Address(String street, String city) {
        this.street = street;
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (!street.equals(address.street)) return false;
        return city.equals(address.city);
    }

    @Override
    public int hashCode() {
        int result = street.hashCode();
        result = 31 * result + city.hashCode();
        return result;
    }
}
