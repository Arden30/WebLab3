package mbeans;

import db.HibernateManager;
import models.DataEntity;
import utils.MBeanRegistryUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.management.AttributeChangeNotification;
import javax.management.MBeanNotificationInfo;
import javax.management.Notification;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.List;

@Named("dots")
@ApplicationScoped
public class Dots extends NotificationBroadcasterSupport implements DotsMBean, Serializable {
    private final HibernateManager hibernateManager = new HibernateManager();
    private List<DataEntity> data;
    private long sequenceNumber = 0;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        MBeanRegistryUtil.registerBean(this, "dots");
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object unused) {
        MBeanRegistryUtil.unregisterBean(this);
    }

    @Override
    public long getAllDots() {
        data = hibernateManager.getAllFromDB();

        return data.size();
    }

    @Override
    public long getMissedDots() {
        data = hibernateManager.getAllFromDB();

        return data.stream().filter(d -> !d.isResult()).count();
    }

    @Override
    public MBeanNotificationInfo[] getNotificationInfo() {
        String[] types = new String[]{AttributeChangeNotification.ATTRIBUTE_CHANGE};
        String name = AttributeChangeNotification.class.getName();
        String description = "Divides by 5 notification";
        MBeanNotificationInfo info = new MBeanNotificationInfo(types, name, description);
        return new MBeanNotificationInfo[]{info};
    }

    @Override
    public void check() {
        long dotsNumber = getAllDots();
        if (dotsNumber % 5 == 0) {
            Notification notification = new Notification(
                    "Multiple of 5",
                    getClass().getSimpleName(),
                    sequenceNumber++,
                    "The total count dots is now multiple of 5!"
            );
            sendNotification(notification);
        }
    }
}
