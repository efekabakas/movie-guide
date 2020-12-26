package com.appmovieguide.movieguide;



public class Movie implements IMovie {

	private String title;
	private String genre;
	private String ranking;
	private String budget;
	private String worldWideGross;
	private Date releaseDate;
	private Director director;
	private Cast cast;
	private int runTime;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getRanking() {
		return ranking;
	}

	public void setRanking(String ranking) {
		this.ranking = ranking;
	}

	public String getBudget() {
		return budget;
	}

	public void setBudget(String budget) {
		this.budget = budget;
	}

	public String getWorldWideGross() {
		return worldWideGross;
	}

	public void setWorldWideGross(String worldWideGross) {
		this.worldWideGross = worldWideGross;
	}

	public Date getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(Date releaseDate) {
		this.releaseDate = releaseDate;
	}

	public Director getDirector() {
		return director;
	}

	public void setDirector(Director director) {
		this.director = director;
	}

	public Cast getCast() {
		return cast;
	}

	public void setCast(Cast cast) {
		this.cast = cast;
	}

	public int getRunTime() {
		return runTime;
	}

	public void setRunTime(int runTime) {
		this.runTime = runTime;
	}

	

	@Override
	public String toString() {
		return title + ", genre=" + genre + ", ranking=" + ranking + ", budget=" + budget
				+ ", worldWideGross=" + worldWideGross + ", releaseDate=" + releaseDate + ", director=" + director.getName()
				+  ", runTime=" + runTime + ", cast=" + cast;
	}

	public Movie(String title, String genre, String ranking, String budget, String worldWideGross, Date releaseDate,
			Director director, Cast cast, int runTime) {
		
		this.title = title;
		this.genre = genre;
		this.ranking = ranking;
		this.budget = budget;
		this.worldWideGross = worldWideGross;
		this.releaseDate = releaseDate;
		this.director = director;
		this.cast = cast;
		this.runTime = runTime;
	}
	
	public void addPlayer(Player player) {
		cast.addPlayer(player);
	}
	
	

}
