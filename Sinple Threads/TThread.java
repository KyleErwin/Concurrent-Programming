//Practical assignment 1
//Thread created by extending the Thread class

class TThread extends Thread
{
	private Counter counter;

	public TThread(Counter _counter)
	{
		counter = _counter;
	}

	public void run()
	{
		for (int j = 0; j < 4; j++)
		{
			try
			{
				sleep(400);
				int x = counter.getAndIncrement();
				System.out.println(this.getName() + " " + x);
			}
			catch (InterruptedException e)
			{}

		}
	}
}