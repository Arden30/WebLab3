package mbeans;

import db.HibernateManager;
import models.DataEntity;
import utils.MBeanRegistryUtil;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Destroyed;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import javax.inject.Named;
import javax.management.NotificationBroadcasterSupport;
import java.io.Serializable;
import java.util.List;

@Named("area")
@ApplicationScoped
public class Area extends NotificationBroadcasterSupport implements AreaMBean, Serializable {
    private final HibernateManager hibernateManager = new HibernateManager();
    private double area = 0;

    public void init(@Observes @Initialized(ApplicationScoped.class) Object unused) {
        MBeanRegistryUtil.registerBean(this, "area");
        List<DataEntity> list = hibernateManager.getAllFromDB();
        double r = 1;
        if (list.size() > 0) {
            r = list.get(list.size() - 1).getR();
        }
        computeArea(r);
    }

    public void destroy(@Observes @Destroyed(ApplicationScoped.class) Object unused) {
        MBeanRegistryUtil.unregisterBean(this);
    }

    @Override
    public double getArea() {
        return area;
    }

    @Override
    public double computeArea(double r) {
        double triangle = 0.5 * r * r;
        double rectangle = r * (r / 2);
        double sector = Math.PI * (r * r / 4) / 4;
        area = triangle + rectangle + sector;

        return area;
    }
}
