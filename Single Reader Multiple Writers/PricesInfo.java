import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PricesInfo
{
	private double price1;
	private double price2;

	private ReadWriteLock readWriteLock = new ReentrantReadWriteLock();
	private Lock readLock;
	private Lock writeLock;

	public PricesInfo()
	{
		price1 = 1.0;
		price2 = 2.0;
		readLock = readWriteLock.readLock();
		writeLock = readWriteLock.writeLock();
	}
	
	public double getPrice1()
	{
		readLock.lock();
		try
		{
			return price1;
		}
        finally
		{
			readLock.unlock();
		}

	}
	
	public double getPrice2()
	{
		readLock.lock();
		try
		{
			return price2;
		}
		finally
		{
			readLock.unlock();
		}
	}
	
	public void setPrices(double price1, double price2)
	{
		writeLock.lock();
		try
		{
			this.price1 = price1;
			this.price2 = price2;
		}
		finally
		{
			writeLock.unlock();
		}
	}
	
	
}
