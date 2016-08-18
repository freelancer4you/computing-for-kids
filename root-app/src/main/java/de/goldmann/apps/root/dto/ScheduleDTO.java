package de.goldmann.apps.root.dto;

import java.io.Serializable;

public class ScheduleDTO implements Serializable {

	private static final long	serialVersionUID	= 1L;

	private String				begin;

	private String				end;

	ScheduleDTO() {
		super();
	}

	public ScheduleDTO(final String begin, final String end) {
		this.begin = begin;
		this.end = end;
	}

	public String getBegin() {
		return begin;
	}

	public String getEnd() {
		return end;
	}

	@Override
	public String toString() {
		return "ScheduleDTO [" + (begin != null ? "begin=" + begin + ", " : "") + (end != null ? "end=" + end : "")
				+ "]";
	}

}
