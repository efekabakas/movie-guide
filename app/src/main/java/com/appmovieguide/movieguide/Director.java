package com.appmovieguide.movieguide;
import java.io.Serializable;
import java.util.ArrayList;

public class Director implements IPerson {

	private String name;
	private ArrayList<String> movieList;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<String> getMovieList() {
		return movieList;
	}

	public void setMovieList(ArrayList<String> movieList) {
		this.movieList = movieList;
	}

	public Director(String name) {
		this.name = name;
		this.movieList = new ArrayList<String>();
	}

	

	@Override
	public String toString() {
		return name + "," + movieList;
	}

	public void addMovie(String movie) {
		movieList.add(movie);
	}
}
