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
    public String email2;
    public String email3;
    public String home;
    public String mobile;
    public String work;
    public String phone2;



    @ManyToMany
    @JoinTable(name = "address_in_groups",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "group_id"))
    public List<GroupDto> groups;
}
