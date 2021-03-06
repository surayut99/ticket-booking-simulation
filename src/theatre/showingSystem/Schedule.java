package theatre.showingSystem;

import theatre.movies.Movies;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class Schedule {
    private Movies movies;
    private String startTime;
    private ArrayList<String> reservedPositionSeat;
    private int totalSeat;

    public Schedule(Movies movies, String startTime, int totalSeat) {
        this.movies = movies;
        this.startTime = startTime;

        this.totalSeat = totalSeat;
        this.reservedPositionSeat = new ArrayList<>();
    }

    public void addReservedSeat(String ...seatPositions) {
        reservedPositionSeat.addAll(Arrays.asList(seatPositions));
    }

    public void delReservedSeat(String ...seatPositions) {
        reservedPositionSeat.removeAll(Arrays.asList(seatPositions));
    }

    public Movies getMovies() {
        return movies;
    }

    public String getStartTime() {
        return startTime;
    }

    public int getTotalSeat() {
        return totalSeat;
    }

    public int getNumAvailableSeat() {
        return totalSeat - reservedPositionSeat.size();
    }

    public ArrayList<String> getReservedPositionSeat() {
        return reservedPositionSeat;
    }

}
