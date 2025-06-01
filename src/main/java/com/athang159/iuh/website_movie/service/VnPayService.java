package com.athang159.iuh.website_movie.service;

import java.io.UnsupportedEncodingException;

public interface VnPayService {
    String createPaymentUrl(long amount, String orderInfo) throws UnsupportedEncodingException;

    String hmacSHA512(String data);
}
