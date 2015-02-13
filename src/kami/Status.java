package kami;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Status {
    private char[] arr;
    private List<Block> blockList = null;
    
    public Status(char[] arr) {
        this.arr = arr;
    }
    
    public Status exec(Move move) {
        Set<Pos> connected = getConnectedPos(move.getPos());
        //char[] newArr = Arrays.copyOf(arr, arr.length);
        char[] newArr = new char[Var.total()];
        for (int i = 0; i < Var.total(); i++) {
        	newArr[i] = arr[i];
        }
        for (Pos pos : connected) {
        	newArr[pos.getIndex()] = move.getColor();
        }
        return new Status(newArr);
    }
    
    
    public Set<Pos> getConnectedPos(Pos pos) {
        //dfs
    	Set<Pos> set = new HashSet<>();
        searchConnected(pos, set);
        return set;
    }
    
    public char getColorAt(Pos pos) {
    	return arr[pos.getIndex()];
    }
    
    private void searchConnected(Pos current, Set<Pos> pool) {
    	pool.add(current);
    	
    	for (Pos neigh : current.getNeighbours()) {
    		if (!pool.contains(neigh) && getColorAt(neigh) == getColorAt(current)) {
    			searchConnected(neigh, pool);
    		}

    	}

    }
    
    public String toString() {
    	StringBuilder sb = new StringBuilder();
    	sb.append(">>>> " + hashCode());
    	for (int i = 0; i < arr.length; i++) {
    		if (i % Var.cols == 0) {
    			sb.append("\n");
    		}
    		sb.append(arr[i]);
    		
    	}
    	return sb.toString() + "\n";
    }
    
    public Set<Character> getColors() {
    	Set<Character> set = new HashSet<>();
    	for (Block block : getBlockList()) {
    		set.add(block.getColor());
    	}
    	return set;
    }
    
    public List<Block> getBlockList() {
    	if (blockList == null) {
    		blockList = new ArrayList<>();
    		boolean[] checked = new boolean[Var.total()];
    		
    		for (int i = 0; i < Var.total(); i++) {
    			if (!checked[i]) {
    				Pos pos = new Pos(i);
    				Set<Pos> set = getConnectedPos(pos);
    				for (Pos p : set) {
    					checked[p.getIndex()] = true;
    				}
    				Block block = new Block(getColorAt(pos));
    				block.addAll(set);
    				blockList.add(block);
    			}
    		}
    		Collections.sort(blockList, new Comparator<Block>() {
    			
    			@Override
    			public int compare(Block o1, Block o2) {
    				return o2.size() - o1.size();
    			}
    		});
    		
    	}
    	return blockList;
    }
    
    public String arrayStr() {
    	return new String(arr);
    }
    
    public boolean equals(Object o) {
    	if (o instanceof Status) {
    		return ((Status) o).arrayStr().equals(arrayStr());
    	}
    	return false;
    }
    
    public int hashCode() {
    	return arrayStr().hashCode();
    }
}
