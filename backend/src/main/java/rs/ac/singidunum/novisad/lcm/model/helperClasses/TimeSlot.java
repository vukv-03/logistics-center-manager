package rs.ac.singidunum.novisad.lcm.model.helperClasses;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class TimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime arrivalTime;
	private LocalDateTime leavingTime;
	
	public TimeSlot() {
		super();
	}

	public TimeSlot(Long id, LocalDateTime arrivalTime, LocalDateTime leavingTime) {
		super();
		this.id = id;
		this.arrivalTime = arrivalTime;
		this.leavingTime = leavingTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getArrivalTime() {
		return arrivalTime;
	}

	public void setArrivalTime(LocalDateTime arrivalTime) {
		this.arrivalTime = arrivalTime;
	}

	public LocalDateTime getLeavingTime() {
		return leavingTime;
	}

	public void setLeavingTime(LocalDateTime leavingTime) {
		this.leavingTime = leavingTime;
	}
}
