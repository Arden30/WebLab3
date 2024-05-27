package utils;

import javax.management.*;
import javax.servlet.ServletContextListener;
import java.lang.management.ManagementFactory;
import java.util.HashMap;
import java.util.Map;


public final class MBeanRegistryUtil implements ServletContextListener {
    private MBeanRegistryUtil() {
    }
    private final static Map<Class<?>, ObjectName> beans = new HashMap<>();

    public static void registerBean(Object bean, String name) {
        try {
            String domain = bean.getClass().getPackageName();
            String type = bean.getClass().getSimpleName();
            MBeanServer mbs = ManagementFactory.getPlatformMBeanServer();
            ObjectName objectName = new ObjectName(String.format("%s:type=%s,name=%s", domain, type, name));
            mbs.registerMBean(bean, objectName);
            beans.put(bean.getClass(), objectName);
            NotificationListener listener = (notification, handback) -> System.out.println("Received notification: " + notification.getMessage());
            mbs.addNotificationListener(objectName, listener, null, null);
        } catch (InstanceAlreadyExistsException | MBeanRegistrationException | NotCompliantMBeanException |
                 MalformedObjectNameException | InstanceNotFoundException ex) {
            ex.printStackTrace();
        }
    }

    public static void unregisterBean(Object bean) {
        if (!beans.containsKey(bean.getClass())) {
            throw new IllegalArgumentException("Specified bean is not registered.");
        }
        try {
            ObjectName objectName = beans.get(bean.getClass());
            ManagementFactory.getPlatformMBeanServer().unregisterMBean(objectName);
        } catch (InstanceNotFoundException | MBeanRegistrationException ex) {
            ex.printStackTrace();
        }
    }
}
