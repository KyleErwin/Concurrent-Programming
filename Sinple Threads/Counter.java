//Practical assignment 1
//Shared counter object
import java.util.concurrent.locks.ReentrantLock;
class Counter
{
	
	int value;
	private ReentrantLock lock = new ReentrantLock();
	
	Counter(int c) {
		value = c;
	}
	//Task 2
	/*synchronized int getAndIncrement()
	{
		return value++;
	}*/

	int getAndIncrement()
	{
		lock.lock();
		try
		{
			return value++;
		}
		finally
		{
			lock.unlock();
		}
	}
}