package com.techelevator.dao;

import com.techelevator.exception.DaoException;
import com.techelevator.model.Itinerary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class JdbcItineraryDao implements ItineraryDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int create(Itinerary itinerary) {
        String sql = "insert into itinerary (event_date, start_time, end_time)\n" +
                "values (?, ?, ?) returning itinerary_id";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, itinerary.getEventDate(), itinerary.getStartTime(), itinerary.getEndTime());
            if (result.next()) {
                return result.getInt("itinerary_id");
            } else {
                throw new DaoException("Itinerary not created");
            }
        } catch (Exception e) {
            throw new DaoException("Itinerary not created");
        }
    }

    @Override
    public Itinerary findById(int itineraryId) {
        String sql = "select * from itinerary where itinerary_id = ?;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql, itineraryId);
            if (result.next()) {
                return mapRowToItinerary(result);
            }
        } catch (Exception e) {
            String message = String.format("Itinerary not found: itinerary_id(%s)", itineraryId);
            throw new DaoException(message);
        }
        return null;
    }

    @Override
    public List<Itinerary> findAll() {
        List<Itinerary> itineraries = new ArrayList<>();
        String sql = "select * from itinerary;";

        try {
            SqlRowSet result = jdbcTemplate.queryForRowSet(sql);
            while (result.next()) {
                itineraries.add(mapRowToItinerary(result));
            }
        } catch (Exception e) {
            throw new DaoException("Itinerary not found");
        }
        return itineraries;
    }

    @Override
    public Itinerary update(Itinerary itinerary) {
        Itinerary updatedItinerary = null;
        String sql = "update itinerary set event_date = ?, start_time = ?, end_time = ? where itinerary_id = ?";

        try {
            int itineraryId = itinerary.getItineraryId();

            if (itinerary.getItineraryId() == 0)
                throw new DaoException("Missing required value: itineraryId");

//            if (itinerary.getEventDate() != null)
//                itinerary.setEventDate(findById(itineraryId).getEventDate());
//
//            if (itinerary.getEventDate() != null)
//                itinerary.setStartTime(findById(itineraryId).getStartTime());
//
//            if (itinerary.getEventDate() != null)
//                itinerary.setEndTime(findById(itineraryId).getEndTime());

//            jdbcTemplate.update(sql,
//                    itinerary.getEventDate(),
//                    itinerary.getStartTime(),
//                    itinerary.getEndTime(),
//                    itinerary.getItineraryId());

//            updatedItinerary = findById(itineraryId);
        } catch (Exception e) {
            String message = String.format("Itinerary not found: itinerary_id(%s)", itinerary.getItineraryId());
            throw new DaoException(message);
        }
        updatedItinerary = itinerary;
        return updatedItinerary;
    }

//    @Override
//    public void delete(int itineraryId) {
//        String sql = "  begin;delete FROM itinerary_landmarks WHERE itinerary_id=?;delete FROM itinerary WHERE itinerary_id=?;commit;";
//
//        try {
//            jdbcTemplate.update(sql, itineraryId);
//        } catch (Exception ex) {
//            System.out.println("something went wrong: unable to delete");
//        }
//    }

//    @Override
//    public void addLandmark(int itineraryId, String placeId) {
//        String sql = "INSERT INTO itinerary(place_id)\n" +
//                "WHERE itinerary_id equals ?\n" +
//                "VALUES (?);";
//
//        try {
//            jdbcTemplate.update(sql, itineraryId, placeId);
//        } catch (Exception ex) {
//            System.out.println("Something went wrong: add landmark to itinerary");
//        }
//    }

    private Itinerary mapRowToItinerary(SqlRowSet result) {
        Itinerary itinerary = new Itinerary();

        itinerary.setItineraryId(result.getInt("itinerary_id"));

        if (result.getDate("event_date") != null)
            itinerary.setEventDate(result.getDate("event_date").toLocalDate());

        if (result.getTime("start_time") != null)
            itinerary.setStartTime(result.getTime("start_time").toLocalTime());

        if (result.getTime("end_time") != null)
            itinerary.setEndTime(result.getTime("end_time").toLocalTime());

        return itinerary;
    }
}
