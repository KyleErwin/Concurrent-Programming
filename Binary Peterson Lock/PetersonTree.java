import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/*
    u15015302
    Kyle Erwin
 */
public class PetersonTree
{
    final private ThreadID threadID = new ThreadID();

    private int height;
    private Peterson[][] locks;


    public PetersonTree(int threads)
    {
        this.height = (int) Math.ceil(((Math.log(threads)) / Math.log(2))); //First we find the maximum height of the the
                                                                            //tree given the amount of threads.
        this.locks = new Peterson[(this.height) + 1][threads + 1];          //We make a 2d array that's going to point to all the locks.
        for (int j = 0; j <= this.height; j++)                              //Rows
        {
            for (int i = 0; i <= threads; i++)                              //Columns
            {
                locks[j][i] = new Peterson();
            }
        }

    }

    public void PTreeLock()
    {
        int i = threadID.get();                                             //We get the thread ID
        int start = this.height - 1;                                        //Set the starting
        int me;                                                             //The thread being used

        //We will keep trying to obtain the lock until we reach the root
        while (start >= 0)
        {
            me = i;
            i = (int) Math.floor(i / 2);
            this.locks[start][i].lock(me);
            start--;
        }
    }


    public void PTreeUnlock()
    {
        int i = threadID.get();

        //ArrayList is non-synchronized which means multiple threads can work on ArrayList at the same time.
        ArrayList<Integer> path = new ArrayList(this.height + 1);
        int start = 0;
        path.add(i);

        for (int j = 0; j < this.height; j++)
        {
            i = (int) Math.floor(i / 2);
            path.add(i);
        }

        for (int j = path.size() - 1; j >= 1; j--)
        {
            this.locks[start][path.get(j)].unlock(path.get(j - 1));
            start++;
        }

    }
}
