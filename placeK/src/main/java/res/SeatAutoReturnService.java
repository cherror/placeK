package res;

import controller.SeatController;
import controller.UserController;
import model.User;

import javax.servlet.http.HttpSession;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class SeatAutoReturnService {
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private final SeatController seatController;
    private final UserController userController;

    public SeatAutoReturnService(SeatController seatController, UserController userController) {
        this.seatController = seatController;
        this.userController = userController;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(() -> {
            try {
                checkAndReturnSeats();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }, 0, 1, TimeUnit.MINUTES);
    }

    public void stop() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(60, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }

    private void checkAndReturnSeats() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH시 mm분");
        LocalTime currentTime = LocalTime.now();

        for (User user : userController.getAllUsersWithRentedSeats()) {
            try {
                LocalTime returnTime = LocalTime.parse(user.getReturnTime(), formatter);
                if (currentTime.isAfter(returnTime)) {
                    String seatID = user.getSeatNum();
                    int locationID = user.getLocationID();

                    // 세션 업데이트
                    HttpSession session = userController.getUserSession(user.getID());
                    if (session != null) {
                        user.setRented(false);
                        user.setLocationID(-1);
                        user.setSeatNum(null);
                        session.setAttribute("user", user);
                    }

                    userController.updateUserSeatInfo(user.getID(), false, -1, null, null, null);
                    seatController.updateSeatStatus(locationID, seatID, false, null, null);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}

