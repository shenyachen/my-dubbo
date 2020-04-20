package refrence;

import java.lang.ref.PhantomReference;
import java.lang.ref.WeakReference;

/**
 * @author: yachen.shen
 * @Date 2020/3/17 13:12
 */
public class MainTest {

    public static void main(String[] args) {
        WeakReference<Object> weakReference = new WeakReference<>(new Object());
        System.out.println(weakReference.get());
        System.gc();
        System.out.println(weakReference.get());

    }
}
