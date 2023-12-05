package db;

import models.DataEntity;
import org.hibernate.Session;
import javax.enterprise.inject.Model;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Model
public class HibernateManager implements Serializable {
    public boolean addToDB(DataEntity dataEntity) {
        try (Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.merge(dataEntity);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public List<DataEntity> getAllFromDB() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()){
            session.beginTransaction();
            var res = session.createQuery("select Data from DataEntity Data", DataEntity.class)
                    .getResultList();
            session.getTransaction().commit();
            return res;
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public boolean clear() {
        try(Session session = HibernateSessionFactoryUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.createQuery("DELETE FROM DataEntity data")
                    .executeUpdate();
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
