package observetrain;

public interface stockobs {
	
	public void register(observer o);
	public void remove(observer o);	
	public void notifyobs();
}
