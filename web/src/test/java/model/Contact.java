package model;

public record Contact(String id, String lastName, String middleName, String address, String email) {

    public Contact() {
        this("", "", "", "", "");
    }

    public Contact withLastName(String lastName) {
        return new Contact(this.id, lastName, this.middleName, this.address, this.email);
    }

    public Contact withMiddleName(String middleName) {
        return new Contact(this.id, this.lastName, middleName, this.address, this.email);
    }

    public Contact withAddress(String address) {
        return new Contact(this.id, this.lastName, this.middleName, address, this.email);
    }

    public Contact withEmail(String email) {
        return new Contact(this.id, this.lastName, this.middleName, this.address, email);
    }

    public Contact withId(String id) {
        return new Contact(id, this.lastName, this.middleName, this.address, this.email);
    }

}
