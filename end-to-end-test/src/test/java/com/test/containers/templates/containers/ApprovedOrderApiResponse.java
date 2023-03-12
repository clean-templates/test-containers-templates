package com.test.containers.templates.containers;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ApprovedOrderApiResponse {
    private String orderId;
    private String driverId;
    private Timestamp timestamp;

}
