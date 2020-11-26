package tipalti.model;

public class Person {
    private final Name fullName;
    private final Address address;

    public Person(Name fullName, Address address) {
        this.fullName = fullName;
        this.address = address;
    }

    public Name getFullName() {
        return fullName;
    }

    public Address getAddress() {
        return address;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        if (!fullName.equals(person.fullName)) return false;
        return address.equals(person.address);
    }

    @Override
    public int hashCode() {
        int result = fullName.hashCode();
        result = 31 * result + address.hashCode();
        return result;
    }

}
