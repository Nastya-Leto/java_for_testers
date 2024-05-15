package manager;

import model.Contact;
import model.Group;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                .addAnnotatedClass(GroupDto.class)
                .addAnnotatedClass(ContactDto.class)
                .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook?zeroDateTimeBehavior=convertToNull")
                .setProperty(AvailableSettings.USER, "root")
                .setProperty(AvailableSettings.PASS, "")
                .buildSessionFactory();
    }

    static List<Group> convertListGroup(List<GroupDto> dtos) {
        List<Group> result = new ArrayList<>();
        for (var dto : dtos) {
            result.add(convertGroup(dto));
        }
        return result;
    }

    static List<Contact> convertListContact(List<ContactDto> dtos) {
        List<Contact> result = new ArrayList<>();
        for (var dto : dtos) {
            result.add(convertContact(dto));
        }
        return result;
    }

    private static Group convertGroup(GroupDto dto) {
        return new Group("" + dto.id, dto.name, dto.header, dto.footer);
    }

    private static Contact convertContact(ContactDto dto) {
        return new Contact("" + dto.id, dto.lastName, dto.firstName, dto.address, dto.email);
    }

    public List<Group> getGroupListFromDb() {
        return convertListGroup(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupDto", GroupDto.class).list();
        }));
    }

    public List<Contact> getContactFromDb() {
        return convertListContact(sessionFactory.fromSession(session -> {
            return session.createQuery("from ContactDto", ContactDto.class).list();
        }));
    }

    public List<Contact> getContactsInGroup(Group group) {
        return sessionFactory.fromSession(session -> {
            return convertListContact(session.get(GroupDto.class, group.id()).contacts);
        });
    }

    public List<Group> getGroupsInContact(Contact contact) {
        return sessionFactory.fromSession(session -> {
            return convertListGroup(session.get(ContactDto.class, contact.id()).groups);
        });
    }
}
