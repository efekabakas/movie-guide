package com.appmovieguide.movieguide;
import java.io.Serializable;
import java.util.ArrayList;

public class Cast {
	
	private ArrayList<Player> playerList;

	public Cast(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}

	public ArrayList<Player> getPlayerList() {
		return playerList;
	}

	public void setPlayerList(ArrayList<Player> playerList) {
		this.playerList = playerList;
	}
	
	public void addPlayer(Player player) {
		playerList.add(player);
	}

	//@Override
	/*public String toString() {
		return "Cast [playerList=" + playerList + "]";
	}*/
	@Override
	public String toString() {
		String rtn = "";
		for (int i = 0; i < playerList.size(); i++){
			if(i < playerList.size() -1 ) {
				rtn += playerList.get(i).getName() + ",";
			}
			else{
				rtn+= playerList.get(i).getName();
			}
		}
		return rtn;
	};

	
	
	
	
	

}
