package mvc;

import java.util.*;

public class Publisher {
	private Collection<Subscriber> subscribers = new HashSet<Subscriber>();
	public void subscribe(Subscriber s) { subscribers.add(s); }
	public void unsubscribe(Subscriber s) { subscribers.remove(s); }
	public void notifySubscribers() {
		for(Subscriber s: subscribers) {
			s.update();
		}
	}
}


