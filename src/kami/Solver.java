package kami;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solver {
	
	int maxDepth = 12;
	int subBlockSize = 3;
	Set<Status> visitedStatus = new HashSet<>();
	Set<Status> openStatus = new HashSet<Status>();
	
	public void solve(Status status) {
	    List<Move> moveHistory = new ArrayList<Move>();
	    boolean res = dfsWithDepth(0, status, moveHistory);
		System.out.println(res);
		System.out.println(moveHistory.size());
		
		System.out.println(status);
		for (Move move : moveHistory) {
		    System.out.println(move);
		    status = status.exec(move);
		    System.out.println(status);
		}
		
		
	}
	
	
	public boolean dfsWithDepth(int depth, Status start, List<Move> moveHistory) {
		//System.out.println(depth);
		if (depth >= maxDepth) {
			return false;
		}
		// hashcode is enough
		visitedStatus.add(start);
		
		
		List<Block> blockList = start.getBlockList();
		if (blockList.size() > subBlockSize) {
			blockList = blockList.subList(0, subBlockSize);
		}
		//Block largestBlock = start.getBlockList().get(0);
		for (Block bb : blockList) {
			for (char c : start.getColors()) {
				if (c != bb.getColor()) {
					Move move = new Move(bb.getOne(), c);
					Status newStatus = start.exec(move);
					moveHistory.add(move);
					
					if (newStatus.getColors().size() == 1) {
						return true;
					}
					if (!visitedStatus.contains(newStatus)) {
						boolean res = dfsWithDepth(depth + 1, newStatus, moveHistory);
						if (res) {
							return true;
						}
					}
					moveHistory.remove(moveHistory.size() - 1);
				}
			}
		}
		
		return false;
	}
	

    public void bfs(Status start) {
        if (visitedStatus.contains(start)) {
            return;
        }
        openStatus.add(start);
        
        List<Block> blockList = start.getBlockList();
        if (blockList.size() > subBlockSize) {
            blockList = blockList.subList(0, subBlockSize);
        }
        
        while (!openStatus.isEmpty()) {
            Set<Status> iterating = new HashSet<Status>();
            iterating.addAll(openStatus);
            for (Status status : iterating) {
                
                for (Block bb : blockList) {
                    for (char c : status.getColors()) {
                        if (c != bb.getColor()) {
                            Move move = new Move(bb.getOne(), c);
                            Status newStatus = start.exec(move);
                            if (newStatus.getColors().size() == 1) {
                                System.out.println("OK");
                                return;
                            }
                            if (!visitedStatus.contains(newStatus)) {
                                openStatus.add(newStatus);
                            }
                        }
                    }
                }
                openStatus.remove(status);
                visitedStatus.add(status);
            }
        }
        
        
    }
}
