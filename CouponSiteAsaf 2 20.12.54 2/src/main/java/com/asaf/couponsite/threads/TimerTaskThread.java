package com.asaf.couponsite.threads;


import com.asaf.couponsite.enums.ErrorType;
import com.asaf.couponsite.exceptions.ApplicationException;
import com.asaf.couponsite.logic.CouponController;


import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Timer;
import java.util.TimerTask;

public class TimerTaskThread extends TimerTask implements Runnable {

	private CouponController couponController;

	public TimerTaskThread() {
		this.couponController = new CouponController();
	}

	@Override
	public void run() {

		try {
			System.out.println("TimerTask is running");
			couponController.deleteAllExpiredCoupons(new java.util.Date());

			/* All the rows in all the tables that hold these coupons have been deleted with: Cascade delete */

			System.out.println("Coupons deleted successfully");

		} catch (ApplicationException e) {
			e.printStackTrace();
			throw new ApplicationException(ErrorType.GENERAL_ERROR, "ff");
		}

		finally {
			System.out.println("\n Task finished at: " + new java.util.Date() + "\n");
		}
	}

	public static void startDeletingCoupons() {

		GregorianCalendar gc = new GregorianCalendar();

		gc.set(Calendar.HOUR, 00);
		gc.set(Calendar.MINUTE, 00);
		gc.set(Calendar.SECOND, 00);

		gc.add(Calendar.DAY_OF_MONTH, 1);

		Timer timer = new Timer();

		timer.schedule(new TimerTaskThread(), gc.getTime(), 1000 * 60 * 60 * 24);

	}
}



