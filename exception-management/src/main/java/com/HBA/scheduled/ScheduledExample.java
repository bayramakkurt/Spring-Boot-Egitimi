package com.HBA.scheduled;

import java.util.Iterator;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component //Bean nesnesi oluşması için
public class ScheduledExample {

	//
	@Scheduled(cron = "0 48 21 * * *" )//Burada belirtilen saatte spring projesi çalıştığı taktirde burası otomatik çalışacaktır.
	public void writeOneToTen() {
		for (int i = 1; i <= 10; i++) {
			System.out.print(i + " ");
		}
	}
}
