package com.sbs.service;

import com.sbs.dto.PaymentDto;

public interface PaymentService {

	PaymentDto updatePaymentInfo(PaymentDto paymentDto, Integer id);

}
