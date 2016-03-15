package hw6;

import java.util.HashMap;
import java.util.Set;

public class TwoWayHashMap<K, V> {
	private HashMap<K, V> forward;
	private HashMap<V, K> backward;

	TwoWayHashMap() {
		forward = new HashMap<K, V>();
		backward = new HashMap<V, K>();
	}

	public void clear() {
		forward.clear();
		backward.clear();
	}

	@Override
	public Object clone() {
		TwoWayHashMap<K, V> clone = new TwoWayHashMap<K, V>();
		clone.forward = new HashMap<K, V>(this.forward);
		clone.backward = new HashMap<V, K>(this.backward);
		return (Object) clone;
	}

	public V getObjByKey(K key) {
		return forward.get(key);
	}

	public K getKeyByObj(V value) {
		return backward.get(value);
	}

	public boolean containsKey(K key) {
		return forward.containsKey(key);
	}

	public boolean containsValue(V value) {
		return backward.containsKey(value);
	}

	public V put(K key, V value){
		backward.put(value, key);
		return forward.put(key, value);
	}
	
	public V removeByKey(K key){
		V toRemove = forward.remove(key);
		backward.remove(toRemove);
		return toRemove;
	}
	
	public K removeByValue(V value){
		K toRemove = backward.remove(value);
		forward.remove(toRemove);
		return toRemove;
	}

	public int size(){
		return forward.size();
	}
	
	public boolean isEmpty(){
		return forward.isEmpty() && backward.isEmpty();
	}
	
	public Set<V> values(){
		return backward.keySet() ;
	}
	
	public Set<K> keys(){
		return  forward.keySet();
	}
}
