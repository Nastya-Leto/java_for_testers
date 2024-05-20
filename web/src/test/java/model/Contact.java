package model;

public record Contact(String id, String lastName, String firstName, String address, String email, String home,
                      String mobile, String work, String secondary, String email2, String email3) {

    public Contact() {
        this("", "", "", "", "", "", "", "", "", "", "");
    }

    public Contact withLastName(String lastName) {
        return new Contact(this.id, lastName, this.firstName, this.address, this.email, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3);
    }

    public Contact withFirstName(String firstName) {
        return new Contact(this.id, this.lastName, firstName, this.address, this.email, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3);
    }

    public Contact withAddress(String address) {
        return new Contact(this.id, this.lastName, this.firstName, address, this.email, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3);
    }

    public Contact withEmail(String email) {
        return new Contact(this.id, this.lastName, this.firstName, this.address, email, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3);
    }

    public Contact withId(String id) {
        return new Contact(id, this.lastName, this.firstName, this.address, this.email, this.home, this.mobile, this.work, this.secondary, this.email2, this.email3);
    }

    public Contact withHome(String home) {
        return new Contact(id, this.lastName, this.firstName, this.address, this.email, home, this.mobile, this.work, this.secondary, this.email2, this.email3);
    }
    public Contact withMobile(String mobile) {
        return new Contact(id, this.lastName, this.firstName, this.address, this.email, this.home, mobile, this.work, this.secondary, this.email2, this.email3);
    }
    public Contact withWork(String work) {
        return new Contact(id, this.lastName, this.firstName, this.address, this.email, this.home, this.mobile, work, this.secondary, this.email2, this.email3);
    }
    public Contact withSecondary(String secondary) {
        return new Contact(id, this.lastName, this.firstName, this.address, this.email, this.home, this.mobile, this.work, secondary, this.email2, this.email3);
    }
}
