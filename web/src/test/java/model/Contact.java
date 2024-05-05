package model;

public record Contact(String id, String lastName, String firstName, String address, String email) {

    public Contact() {
        this("", "", "", "", "");
    }

    public Contact withLastName(String lastName) {
        return new Contact(this.id, lastName, this.firstName, this.address, this.email);
    }

    public Contact withFirstName(String firstName) {
        return new Contact(this.id, this.lastName, firstName, this.address, this.email);
    }

    public Contact withAddress(String address) {
        return new Contact(this.id, this.lastName, this.firstName, address, this.email);
    }

    public Contact withEmail(String email) {
        return new Contact(this.id, this.lastName, this.firstName, this.address, email);
    }

    public Contact withId(String id) {
        return new Contact(id, this.lastName, this.firstName, this.address, this.email);
    }
}
