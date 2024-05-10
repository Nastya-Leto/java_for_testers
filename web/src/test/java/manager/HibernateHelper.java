package manager;

import model.Group;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.schema.Action;

import java.util.ArrayList;
import java.util.List;

public class HibernateHelper extends HelperBase {

    private SessionFactory sessionFactory;

    public HibernateHelper(ApplicationManager manager) {
        super(manager);
        sessionFactory = new Configuration()
                        //.addAnnotatedClass(Book.class)
                        .addAnnotatedClass(GroupDto.class)
                        .setProperty(AvailableSettings.URL, "jdbc:mysql://localhost/addressbook")
                        .setProperty(AvailableSettings.USER, "root")
                        .setProperty(AvailableSettings.PASS, "")
                        .buildSessionFactory();
    }

    static List<Group> convertList(List<GroupDto> dtos){
        List<Group> result = new ArrayList<>();
        for (var dto: dtos){
            result.add(convert(dto));
        }
        return result;
    }

    private static Group convert(GroupDto dto) {
        return new Group("" + dto.id, dto.name, dto.header, dto.footer);
    }

    public List<Group> getGroupListFromDb(){
       return convertList(sessionFactory.fromSession(session -> {
            return session.createQuery("from GroupDto", GroupDto.class).list();
        }));
    }
}
