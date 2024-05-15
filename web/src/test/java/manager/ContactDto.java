package manager;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "addressbook")
public class ContactDto {

    @Id
    public int id;
    public String lastName;
    public String firstName;
    public String address;
    public String email;

    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public List<GroupDto> groups;
}
