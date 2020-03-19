package tmw.jdk_cglib;

/**
 * @author TMW
 * @since 2020/3/19 14:26
 */
public class IUserManagerImpl implements IUserManager {
    @Override
    public void addUser(String id, String password) {
        System.out.println("======调用了UserManagerImpl.addUser()方法======");
    }
}
