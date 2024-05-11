package manager;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "addressbook")
public class ContactDto {

    @Id
    public int id;
    public String lastName;
    public String firstName;
    public String address;
    public String email;
}
