//u15015302
//Kyle Erwin
import java.util.concurrent.atomic.AtomicLong;
public class Account 
{
	private AtomicLong balance;
	public Account(){balance = new AtomicLong(0);}
	public long getBalance(){return balance.get();}
	public void setBalance(long balance){this.balance.set(balance);}
	public void addAmount(long amount){balance.getAndAdd(amount);}
	public void subtractAmount(long amount){balance.getAndAdd(-amount);}
}
