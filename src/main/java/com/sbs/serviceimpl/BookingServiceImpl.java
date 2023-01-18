package com.sbs.serviceimpl;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sbs.dto.BookingDto;
import com.sbs.dto.GetBookingInfoForAdminDto;
import com.sbs.dto.GetBookingInfoForUserDto;
import com.sbs.dto.PaymentDto;
import com.sbs.dto.SportsFieldDto;
import com.sbs.dto.TimeSlotDto;
import com.sbs.dto.UserDtowithOutPass;
import com.sbs.dto.get_all_info_for_admin.BookingStatusDtoAdmin;
import com.sbs.dto.get_all_info_for_admin.PaymentDtoAdmin;
import com.sbs.dto.get_all_info_for_admin.SportsFieldDtoAdmin;
import com.sbs.dto.get_all_info_for_admin.TimeSlotDtoAdmin;
import com.sbs.dto.get_all_info_for_admin.UserDtoAdmin;
import com.sbs.entity.Booking;
import com.sbs.entity.BookingStatus;
import com.sbs.entity.Payment;
import com.sbs.entity.SportsField;
import com.sbs.entity.TimeSlot;
import com.sbs.entity.User;
import com.sbs.repository.BookingRepository;
import com.sbs.repository.BookingStatusRepository;
import com.sbs.repository.PaymentRepository;
import com.sbs.repository.SportsFieldRepository;
import com.sbs.repository.TimeslotRepository;
import com.sbs.repository.UserRepository;
import com.sbs.service.BookingService;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SportsFieldRepository sportsFieldRepository;
	@Autowired
	private TimeslotRepository timeSlotRepository;
	@Autowired
	private PaymentRepository paymentRepository;
	@Autowired
	private BookingStatusRepository bookingStatusRepository;

	@Override
	public Integer booking(BookingDto bookingDto, Integer userId, Integer sportsFieldId) {
		Booking booking = new Booking();

		List<Booking> bookingList = bookingRepository.findBybookingDate(bookingDto.getBookingDate());
		boolean startTime = false;

		if (bookingList.isEmpty()) {
			User user = userRepository.findById(userId).get();
			SportsField field = sportsFieldRepository.findById(sportsFieldId).get();
			BeanUtils.copyProperties(bookingDto, booking);
			TimeSlot timeSlot = new TimeSlot();
			timeSlot.setStartTime(bookingDto.getTimeSlot().getStartTime());
			timeSlot.setEndTime(bookingDto.getTimeSlot().getEndTime());
			if (timeSlot != null) {
				timeSlotRepository.save(timeSlot);
			}

			Payment payment = new Payment();
			payment.setAmount(bookingDto.getPaymentDto().getAmount());
			payment.setPaymentDate(bookingDto.getPaymentDto().getPaymentDate());
			payment.setPaymentMethod(bookingDto.getPaymentDto().getPaymentMethod());
			if (payment != null) {
				paymentRepository.save(payment);
			}
			BookingStatus bookingStatus = new BookingStatus();
			bookingStatus.setStatus("Successfull");
			bookingStatusRepository.save(bookingStatus);
			booking.setStatus(bookingStatus);
			booking.setPayment(payment);
			booking.setSportsField(field);
			booking.setTimeSlot(timeSlot);
			booking.setUser(user);
			bookingRepository.save(booking);
			return booking.getBookingId();
		} else if (bookingList != null) {
			for (int i = 0; i < bookingList.size(); i++) {
				if (bookingDto.getTimeSlot().getStartTime().equals(bookingList.get(i).getTimeSlot().getStartTime())) {
					startTime = true;
				}
			}
		}
		if (startTime == false) {
			User user = userRepository.findById(userId).get();
			SportsField field = sportsFieldRepository.findById(sportsFieldId).get();
			BeanUtils.copyProperties(bookingDto, booking);
			TimeSlot timeSlot = new TimeSlot();
			BeanUtils.copyProperties(timeSlot, bookingDto.getTimeSlot());
			if (timeSlot != null) {
				timeSlotRepository.save(timeSlot);
			}
			Payment payment = new Payment();
			BeanUtils.copyProperties(payment, bookingDto.getPaymentDto());
			if (payment != null) {
				paymentRepository.save(payment);
			}
			BookingStatus bookingStatus = new BookingStatus();
			bookingStatus.setStatus("Successfull");
			bookingStatusRepository.save(bookingStatus);
			booking.setStatus(bookingStatus);
			booking.setPayment(payment);
			booking.setSportsField(field);
			booking.setTimeSlot(timeSlot);
			booking.setUser(user);
			bookingRepository.save(booking);
			return booking.getBookingId();

		}

		return booking.getBookingId();
	}

	@Override
	public GetBookingInfoForAdminDto getBookingInfo(Integer id) {
		Booking booking = bookingRepository.findById(id).get();

		// Booking Status
		BookingStatus admin = bookingStatusRepository.findById(booking.getStatus().getStatusId()).get();
		BookingStatusDtoAdmin dtoAdmin = new BookingStatusDtoAdmin();
		BeanUtils.copyProperties(admin, dtoAdmin);

		// Payment
		Payment payment = paymentRepository.findById(booking.getPayment().getPaymentId()).get();
		PaymentDtoAdmin paymentDtoAdmin = new PaymentDtoAdmin();
		BeanUtils.copyProperties(payment, paymentDtoAdmin);

		// Sportsfield
		SportsField sportsField = sportsFieldRepository.findById(booking.getSportsField().getSportsFieldId()).get();
		SportsFieldDtoAdmin fieldDtoAdmin = new SportsFieldDtoAdmin();
		BeanUtils.copyProperties(sportsField, fieldDtoAdmin);

		// TimeSolt
		TimeSlot timeSlot = timeSlotRepository.findById(booking.getTimeSlot().getTimeSlotId()).get();
		TimeSlotDtoAdmin slotDtoAdmin = new TimeSlotDtoAdmin();
		BeanUtils.copyProperties(timeSlot, slotDtoAdmin);

		// User
		User user = userRepository.findById(booking.getUser().getUserId()).get();
		UserDtoAdmin userDtoAdmin = new UserDtoAdmin();
		BeanUtils.copyProperties(user, userDtoAdmin);

		GetBookingInfoForAdminDto bookingInfoForAdminDto = new GetBookingInfoForAdminDto();
		bookingInfoForAdminDto.setBookingDate(booking.getBookingDate());
		bookingInfoForAdminDto.setBookingId(booking.getBookingId());
		bookingInfoForAdminDto.setPaymentDto(paymentDtoAdmin);
		bookingInfoForAdminDto.setSportsField(fieldDtoAdmin);
		bookingInfoForAdminDto.setTimeSlot(slotDtoAdmin);
		bookingInfoForAdminDto.setUser(userDtoAdmin);
		return bookingInfoForAdminDto;
	}

	@Override
	public GetBookingInfoForUserDto getBookingInfoUser(Integer id) {
		Booking booking = bookingRepository.findById(id).get();

		// Booking Status
		BookingStatus bookingStatus = bookingStatusRepository.findById(booking.getStatus().getStatusId()).get();
		BookingDto bookingDto = new BookingDto();
		BeanUtils.copyProperties(bookingStatus, bookingDto);

		// Payment
		Payment payment = paymentRepository.findById(booking.getPayment().getPaymentId()).get();
		PaymentDto paymentDto = new PaymentDto();
		BeanUtils.copyProperties(payment, paymentDto);

		// Sportsfield
		SportsField sportsField = sportsFieldRepository.findById(booking.getSportsField().getSportsFieldId()).get();
		SportsFieldDto sportsFieldDto = new SportsFieldDto();
		BeanUtils.copyProperties(sportsField, sportsFieldDto);

		// TimeSolt
		TimeSlot timeSlot = timeSlotRepository.findById(booking.getTimeSlot().getTimeSlotId()).get();
		TimeSlotDto timeSlotDto = new TimeSlotDto();
		BeanUtils.copyProperties(timeSlot, timeSlotDto);

		// User
		User user = userRepository.findById(booking.getUser().getUserId()).get();
		UserDtowithOutPass userDto = new UserDtowithOutPass();
		BeanUtils.copyProperties(user, userDto);

		GetBookingInfoForUserDto bookingInfoForUserDto = new GetBookingInfoForUserDto();
		bookingInfoForUserDto.setBookingDate(booking.getBookingDate());
		bookingInfoForUserDto.setBookingId(booking.getBookingId());
		bookingInfoForUserDto.setPaymentDto(paymentDto);
		bookingInfoForUserDto.setSportsField(sportsFieldDto);
		bookingInfoForUserDto.setTimeSlot(timeSlotDto);
		bookingInfoForUserDto.setUser(userDto);

		return bookingInfoForUserDto;
	}

}
