import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
//Kyle Erwin
//15015302
public class PrintQueue {

	private final Lock queueLock = new ReentrantLock(true);
	//private final Filter lock = new Filter(10);
	
	public void printJob(Object document)
	{


		try
		{
			queueLock.lock();
			//lock.lock();
			int sleepTime = (int) (Math.random() * 5 + 1);
			System.out.println(Thread.currentThread().getName()+ ": Printing a job for " + sleepTime + " seconds");
			Thread.sleep(sleepTime * 1000);
		}
		catch (InterruptedException e)
		{

		}
		finally
		{
			queueLock.unlock();
			//lock.unlock();
		}
	}
}
