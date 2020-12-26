package com.appmovieguide.movieguide;
import java.io.Serializable;
import java.util.ArrayList;

public class Player implements IPerson {
	
	private String name;
	private ArrayList<String> movieList;
	
	public Player(String name/*, ArrayList<Movie> movieList*/) {
		this.name = name;
		this.movieList = new ArrayList<String>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<String> getMovieList() {
		return movieList;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setMovieList(ArrayList<String> movieList) {
		this.movieList = movieList;
	}
	
	public void addMovie(String movie) {
		movieList.add(movie);
	}

	@Override
	public String toString() {
		return name + "," + movieList;
	}
	
	
	
	
	
	
	
	

	
}
