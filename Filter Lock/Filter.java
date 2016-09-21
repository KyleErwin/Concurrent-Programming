//Kyle Erwin
//15015302
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

public class Filter implements Lock
{
    private AtomicInteger[] level;
    private AtomicInteger[] victim;

    private int n;

    public Filter(int n)
    {
        this.n = n;
	    level = new AtomicInteger[n];
        victim = new AtomicInteger[n];

        for(int j = 0; j < n; j++)
        {
            level[j] = new AtomicInteger();
            victim[j] = new AtomicInteger();
        }
    }

    @Override
    public void lock()
    {
        int me = Integer.parseInt(Thread.currentThread().getName().substring(7));

        for(int j = 1; j < n; j++)
        {
            level[me].set(j);
            victim[j].set(me);
            for (int k = 0; k < n; k++)
            {
                while ((k != me) && (level[k].get() >= j && victim[j].get() == me))
                {
                    //spin wait
                }
            }

        }
    }
    @Override
    public void unlock()
    {
        int me = Integer.parseInt(Thread.currentThread().getName().substring(7));
        level[me].set(0);
    }

    /////////////////////////////////////////////////////////////////////////////

    @Override
    public void lockInterruptibly() throws InterruptedException
    {

    }

    @Override
    public boolean tryLock()
    {
        return false;
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException
    {
        return false;
    }

    @Override
    public Condition newCondition()
    {
        return null;
    }

}

