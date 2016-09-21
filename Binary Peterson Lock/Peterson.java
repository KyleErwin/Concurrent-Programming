import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/*
    u15015302
    Kyle Erwin
 */

public class Peterson implements Lock
{
    private AtomicBoolean[] flag = new AtomicBoolean[2];
    private int victim;

    final private ThreadID threadID = new ThreadID();

    public Peterson()
    {
        for (int j = 0; j < flag.length; ++j)
            flag[j] = new AtomicBoolean();
    }

    /*
    We modulo by two because a peterson lock works for 2 threads.
    So we get the id, and then modulo it by two to get either 1 or 0, which then corresponds to the position in the flag array
    */
    public void lock()
    {
        int i = threadID.get() % 2;              //So the position in the flag array
        int j = 1 - i;                           //The other position in the Flag array
        flag[i].set(true);                       //Sets its interest to true
        victim = i;                              //But defers to the other Thread
        while (flag[j].get() && victim == i)
        {
        }                                        //Wait until the other thread becomes the victim and its flag is set to false
    }

    public void lock(int id)
    {
        int i = id % 2;                          //We're given the id
        int j = 1 - i;
        flag[i].set(true);
        victim = i;
        while (flag[j].get() && victim == i)
        {
        }
    }

    public void unlock()
    {
        int i = threadID.get() % 2;
        flag[i].set(false);                      // I am not interested
    }

    public void unlock(int id)
    {
        id %= 2;
        flag[id].set(false);                     // I am not interested
    }

    public Condition newCondition()
    {
        throw new java.lang.UnsupportedOperationException();
    }

    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
    {
        throw new java.lang.UnsupportedOperationException();
    }

    public boolean tryLock()
    {
        throw new java.lang.UnsupportedOperationException();
    }

    public void lockInterruptibly() throws InterruptedException
    {
        throw new java.lang.UnsupportedOperationException();
    }
}
